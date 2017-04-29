package com.zackyzhang.geolocationphotos.data.model;

import android.util.Log;

import com.zackyzhang.geolocationphotos.BuildConfig;
import com.zackyzhang.geolocationphotos.data.BaseDataManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lei on 4/28/17.
 */

public abstract class PhotoDataManager extends BaseDataManager<ReorgPhoto> {
    private static final String TAG = "PhotoDataManager";

    private int recentPhotoPage = 1;
    private int photoCount = 0;
    private List<Photo> rawPhotos;
    private Set<String> photoIdSet;

    public PhotoDataManager() {
        super();
        rawPhotos = new ArrayList<>();
        photoIdSet = new HashSet<>();
    }

    public void provideData() {
        loadRecentPhotos(String.valueOf(recentPhotoPage));
        recentPhotoPage++;
    }

    private void increasePhotoCount(String id) {
        photoCount++;
        Log.d(TAG, "increase to: " + photoCount + ". " + id);
    }

    private void decreasePhotoCount(String id) {
        photoCount--;
        Log.d(TAG, "decrease to: " + photoCount + ". " + id);
    }

    private void resetPhotoCount() {
        photoCount = 0;
        Log.d(TAG, "resetPhotoCount: " + photoCount);
    }

    public void resetPage() {
        recentPhotoPage = 1;
        rawPhotos.clear();
        photoIdSet.clear();
    }

    private void loadRecentPhotos(String page) {
        getFlickrApi().getRecent(BuildConfig.FLICKR_API_KEY, EXTRAS, page, String.valueOf(30))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(recentPhotos -> recentPhotos.getPhotos().getPhoto())
                .subscribe(
                        photos -> rawPhotos = photos,
                        error -> Log.d(TAG, error.getMessage()),
                        () -> combineWithPhotoInfo());
    }

    private void loadPhotoInfo(ReorgPhoto photo) {
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

    private void combineWithPhotoInfo() {
        Flowable<Photo> flowable = Flowable.fromIterable(rawPhotos);
        flowable.filter(photo -> photo.getUrlC() != null)
                .filter(photo -> !photoIdSet.contains(photo.getId()))
                .subscribe(
                        photo -> {
                            ReorgPhoto newPhoto = new ReorgPhoto(photo.getId());
                            newPhoto.setUrlC(photo.getUrlC());
                            newPhoto.setUrlQ(photo.getUrlQ());
                            increasePhotoCount(photo.getId());
                            photoIdSet.add(newPhoto.getId());
                            loadPhotoInfo(newPhoto);
                        });
    }

    private void generateReorgPhoto(PhotoInfo photoInfo, ReorgPhoto photo) {
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
