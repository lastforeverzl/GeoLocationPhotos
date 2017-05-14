package com.zackyzhang.geolocationphotos.data;


import com.zackyzhang.geolocationphotos.BuildConfig;

import java.util.ArrayList;
import java.util.HashSet;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by lei on 4/27/17.
 */

public abstract class SearchDataManager extends BaseDataManager {

    private String lat;
    private String lng;

    public SearchDataManager() {
        super();
        rawPhotos = new ArrayList<>();
        photoIdSet = new HashSet<>();
    }

    public void provideData(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
        loadRecentPhotos(String.valueOf(photoPage));
        photoPage++;
    }

    private void loadRecentPhotos(String page) {
        getFlickrApi().getSearch(BuildConfig.FLICKR_API_KEY, EXTRAS, page, lat, lng, PER_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(recentPhotos -> recentPhotos.getPhotos().getPhoto())
                .subscribe(
                        photos -> rawPhotos = photos,
                        error -> Timber.e(error.getMessage()),
                        () -> combineWithPhotoInfo());
    }

}
