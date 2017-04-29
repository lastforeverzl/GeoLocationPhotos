package com.zackyzhang.geolocationphotos.data;

import android.util.Log;

import com.zackyzhang.geolocationphotos.BuildConfig;
import com.zackyzhang.geolocationphotos.data.model.Photo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lei on 4/27/17.
 */

public abstract class SearchDataManager extends BaseDataManager<List<Photo>> {
    private static final String TAG = "SearchDataManager";

    private int searchPhotoPage = 1;
    private String placeName = "";

    public SearchDataManager() {
        super();
    }

    private void loadSearchPhotos(String page, String lat, String lon) {
        getFlickrApi().getSearch(BuildConfig.FLICKR_API_KEY, EXTRAS, page, lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(searchPhotos -> searchPhotos.getPhotos().getPhoto())
                .subscribe(photos -> onDataLoading(photos),
                        error -> Log.d("MainActivity", error.getMessage()),
                        () -> Log.d("MainActivity", "load search finished"));
    }

    public void loadAllData() {
        // TODO: 4/27/17 init page number when new query.
//        if (!placeName.equals("<Place name from google autocomplete>")) {
//            clear();
//            this.placeName = "<Place>";
//        } else {
//            searchPhotoPage++;
//        }
        String page = String.valueOf(searchPhotoPage);
        loadSearchPhotos(page, "39.898523", "116.360352");
    }

    private void clear() {
        placeName = "";
        searchPhotoPage = 1;
    }
}
