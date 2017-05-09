package com.zackyzhang.geolocationphotos.mvp.presenter;

import com.zackyzhang.geolocationphotos.data.SearchDataManager;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;
import com.zackyzhang.geolocationphotos.mvp.GeoPhotosContract;

/**
 * Created by lei on 5/8/17.
 */

public class GeoPhotosPresenter extends MvpPresenter<GeoPhotosContract.View> implements GeoPhotosContract.Presenter {

    private SearchDataManager mSearchDataManager;

    public GeoPhotosPresenter() {
        setupDataManager();
    }

    @Override
    public void loadPhotos(String lat, String lng) {
        mSearchDataManager.resetPage();
        mSearchDataManager.provideData(lat, lng);
    }

    @Override
    public void loadMorePhotos(String lat, String lng) {
        mSearchDataManager.provideData(lat, lng);
    }

    private void setupDataManager() {
        mSearchDataManager = new SearchDataManager() {
            @Override
            public void onDataLoading(ReorgPhoto data) {
                getView().loadPhotos(data);
            }

            @Override
            public void loadedData() {
                getView().setLoadingStatusFalse();
            }
        };
    }
}
