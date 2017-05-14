package com.zackyzhang.geolocationphotos.data;

import com.zackyzhang.geolocationphotos.BuildConfig;

import java.util.ArrayList;
import java.util.HashSet;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by lei on 4/28/17.
 */

public abstract class PhotoDataManager extends BaseDataManager {

    public PhotoDataManager() {
        super();
        rawPhotos = new ArrayList<>();
        photoIdSet = new HashSet<>();
    }

    public void provideData() {
        loadRecentPhotos(String.valueOf(photoPage));
        photoPage++;
    }

    private void loadRecentPhotos(String page) {
        getFlickrApi().getRecent(BuildConfig.FLICKR_API_KEY, EXTRAS, page, String.valueOf(30))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(recentPhotos -> recentPhotos.getPhotos().getPhoto())
                .subscribe(
                        photos -> rawPhotos = photos,
                        error -> Timber.e(error.getMessage()),
                        () -> combineWithPhotoInfo());
    }

}
