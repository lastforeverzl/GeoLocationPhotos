package com.zackyzhang.geolocationphotos.data;

import android.util.Log;

import com.zackyzhang.geolocationphotos.BuildConfig;
import com.zackyzhang.geolocationphotos.data.model.PhotoInfo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lei on 4/27/17.
 */

public abstract class PhotoInfoDataManager extends BaseDataManager<PhotoInfo> {

    public PhotoInfoDataManager() {
        super();
    }

    private void loadPhotoInfo(String photoId) {
        getFlickrApi().getPhotoInfo(BuildConfig.FLICKR_API_KEY, photoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(photoInfoResponse -> photoInfoResponse.getPhoto())
                .subscribe(photoInfo -> onDataLoading(photoInfo),
                        error -> Log.d("MainActivity", error.getMessage()),
                        () -> Log.d("MainActivity", "load photoInfo finished"));
    }

    public void loadPhoto(String photoId) {
        loadPhotoInfo(photoId);
    }
}
