package com.zackyzhang.geolocationphotos.data;

import com.zackyzhang.geolocationphotos.BuildConfig;
import com.zackyzhang.geolocationphotos.data.model.Photo;
import com.zackyzhang.geolocationphotos.data.model.PhotoInfo;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;

import java.util.List;
import java.util.Set;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by lei on 4/27/17.
 */

public abstract class BaseDataManager {

    protected static final String PER_PAGE = "30";
    protected static final String EXTRAS = "geo,url_c,url_z,url_l";
    private FlickrApi mFlickrApi;

    protected int photoCount = 0;
    protected int photoPage = 1;
    protected List<Photo> rawPhotos;
    protected Set<String> photoIdSet;

    public BaseDataManager() {
        mFlickrApi = FlickrApi.instance();
    }

    public abstract void onDataLoading(ReorgPhoto data);

    public abstract void loadedData();

    protected FlickrService getFlickrApi() {
        return mFlickrApi.getApi();
    }

    protected void increasePhotoCount(String id) {
        photoCount++;
//        Log.d(TAG, this.toString() + " increase to: " + photoCount + ". " + id);
        Timber.d(this.toString() + " increase to: " + photoCount + ". " + id);
    }

    protected void decreasePhotoCount(String id) {
        photoCount--;
        Timber.d(this.toString() + "decrease to: " + photoCount + ". " + id);
    }

    protected void resetPhotoCount() {
        photoCount = 0;
        Timber.d(this.toString() +" PhotoCount: " + photoCount);
    }

    public void resetPage() {
        Timber.d("page: " + photoPage + ", rawPhotos: " + rawPhotos.size() + ", photoIdSet: " + photoIdSet.size());
        photoPage = 1;
        rawPhotos.clear();
        photoIdSet.clear();
    }

    protected void combineWithPhotoInfo() {
        Flowable<Photo> flowable = Flowable.fromIterable(rawPhotos);
        flowable.filter(photo -> photo.getUrlC() != null)
                .filter(photo -> photo.getLatitude() != 0 || photo.getLongitude() != 0)
                .filter(photo -> !photoIdSet.contains(photo.getId()))
                .subscribe(
                        photo -> {
                            ReorgPhoto newPhoto = new ReorgPhoto(photo.getId());
                            newPhoto.setUrlC(photo.getUrlC());
                            newPhoto.setUrlZ(photo.getUrlZ());
                            newPhoto.setUrlL(photo.getUrlL());
                            increasePhotoCount(photo.getId());
                            photoIdSet.add(newPhoto.getId());
                            loadPhotoInfo(newPhoto);
                        });
    }

    protected void loadPhotoInfo(ReorgPhoto photo) {
        getFlickrApi().getPhotoInfo(BuildConfig.FLICKR_API_KEY, photo.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(photoInfoResponse -> photoInfoResponse.getPhoto())
                .subscribe(
                        photoInfo -> {
                            generateReorgPhoto(photoInfo, photo);
                            Timber.d(photo.getId());
                            onDataLoading(photo);
                            decreasePhotoCount(photo.getId());
                        },
                        error -> Timber.e(error.getMessage()),
                        () -> {
                            if (photoCount == 0) {
                                resetPhotoCount();
                                loadedData();
                                Timber.d("load ReorgPhoto finished");
                            }
                        });
    }

    protected void generateReorgPhoto(PhotoInfo photoInfo, ReorgPhoto photo) {
        String country, region;
        photo.setLatitude(String.valueOf(photoInfo.getLocation().getLatitude()));
        photo.setLongitude(String.valueOf(photoInfo.getLocation().getLongitude()));
        photo.setDescription(photoInfo.getDescription().getContent());
        photo.setUsername(photoInfo.getOwner().getUsername());
        if (photoInfo.getLocation().getCountry() != null) {
            country = photoInfo.getLocation().getCountry().getContent();
        } else {
            country = "";
        }
        if (photoInfo.getLocation().getRegion() != null) {
            region = photoInfo.getLocation().getRegion().getContent();
        } else if (photoInfo.getLocation().getCounty() != null) {
            region = photoInfo.getLocation().getCounty().getContent();
        } else if (photoInfo.getLocation().getRegion() != null) {
            region = photoInfo.getLocation().getRegion().getContent();
        } else {
            region = "";
        }
        String location = region + ", " + country;
        photo.setLocation(location);
        String iconFarm = String.valueOf(photoInfo.getOwner().getIconfarm());
        String iconServer = photoInfo.getOwner().getIconserver();
        String nsid = photoInfo.getOwner().getNsid();
        String avatarUrl = "http://farm" + iconFarm + ".staticflickr.com/" + iconServer + "/buddyicons/" + nsid + ".jpg";
        photo.setNsid(nsid);
        photo.setAvatar_url(avatarUrl);
        photo.setUrlPhotoPage(photoInfo.getUrls().getUrl().get(0).getContent());
    }

}
