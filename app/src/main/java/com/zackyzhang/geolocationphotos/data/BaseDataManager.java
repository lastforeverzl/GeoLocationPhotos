package com.zackyzhang.geolocationphotos.data;

import android.util.Log;

import com.zackyzhang.geolocationphotos.BuildConfig;
import com.zackyzhang.geolocationphotos.data.model.Photo;
import com.zackyzhang.geolocationphotos.data.model.PhotoInfo;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;

import java.util.List;
import java.util.Set;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lei on 4/27/17.
 */

public abstract class BaseDataManager {

    private static final String TAG = "BaseDataManager";

    protected static final String EXTRAS = "geo,url_c,url_z";
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
        Log.d(TAG, this.toString() + " increase to: " + photoCount + ". " + id);
    }

    protected void decreasePhotoCount(String id) {
        photoCount--;
        Log.d(TAG, this.toString() + "decrease to: " + photoCount + ". " + id);
    }

    protected void resetPhotoCount() {
        photoCount = 0;
        Log.d(TAG, this.toString() +" PhotoCount: " + photoCount);
    }

    public void resetPage() {
        photoPage = 1;
        rawPhotos.clear();
        photoIdSet.clear();
    }

    protected void combineWithPhotoInfo() {
        Flowable<Photo> flowable = Flowable.fromIterable(rawPhotos);
        flowable.filter(photo -> photo.getUrlC() != null)
                .filter(photo -> !photoIdSet.contains(photo.getId()))
                .subscribe(
                        photo -> {
                            ReorgPhoto newPhoto = new ReorgPhoto(photo.getId());
                            newPhoto.setUrlC(photo.getUrlC());
                            newPhoto.setUrlZ(photo.getUrlZ());
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
                            Log.d(TAG, photo.getId());
                            onDataLoading(photo);
                            decreasePhotoCount(photo.getId());
                        },
                        error -> Log.d(TAG, error.getMessage()),
                        () -> {
                            if (photoCount == 0) {
                                resetPhotoCount();
                                loadedData();
                                Log.d(TAG, "load ReorgPhoto finished");
                            }
                        });
    }

    protected void generateReorgPhoto(PhotoInfo photoInfo, ReorgPhoto photo) {
        photo.setLatitude(String.valueOf(photoInfo.getLocation().getLatitude()));
        photo.setLongitude(String.valueOf(photoInfo.getLocation().getLongitude()));
        photo.setDescription(photoInfo.getDescription().getContent());
        photo.setUsername(photoInfo.getOwner().getUsername());
        String location = photoInfo.getLocation().getRegion().getContent() + " " + photoInfo.getLocation().getCountry().getContent();
        photo.setLocation(location);
        String iconFarm = String.valueOf(photoInfo.getOwner().getIconfarm());
        String iconServer = photoInfo.getOwner().getIconserver();
        String nsid = photoInfo.getOwner().getNsid();
        String avatarUrl = "http://farm" + iconFarm + ".staticflickr.com/" + iconServer + "/buddyicons/" + nsid + ".jpg";
        photo.setAvatar_url(avatarUrl);
    }

}
