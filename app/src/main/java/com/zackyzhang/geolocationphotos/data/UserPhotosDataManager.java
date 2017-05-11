package com.zackyzhang.geolocationphotos.data;

import android.util.Log;

import com.zackyzhang.geolocationphotos.BuildConfig;

import java.util.ArrayList;
import java.util.HashSet;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lei on 5/10/17.
 */

public abstract class UserPhotosDataManager extends BaseDataManager {
    private static final String TAG = "UserPhotosDataManager";

    private String userId;

    public UserPhotosDataManager() {
        super();
        rawPhotos = new ArrayList<>();
        photoIdSet = new HashSet<>();
    }

    public void provideData(String userId) {
        this.userId = userId;
        loadUserPhotos(String.valueOf(photoPage));
        photoPage++;
    }

    private void loadUserPhotos(String page) {
        getFlickrApi().getUserPublicPhotos(BuildConfig.FLICKR_API_KEY, EXTRAS, page, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(photosResponse -> photosResponse.getPhotos().getPhoto())
                .subscribe(
                        photos -> rawPhotos = photos,
                        error -> Log.d(TAG, error.getMessage()),
                        () -> combineWithPhotoInfo());
    }
}
