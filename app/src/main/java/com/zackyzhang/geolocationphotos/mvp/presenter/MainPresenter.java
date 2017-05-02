package com.zackyzhang.geolocationphotos.mvp.presenter;

import com.zackyzhang.geolocationphotos.data.PhotoDataManager;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;
import com.zackyzhang.geolocationphotos.mvp.MainContract;

/**
 * Created by lei on 4/28/17.
 */

public class MainPresenter extends MvpPresenter<MainContract.View> implements MainContract.Presenter {
    private static final String TAG = "MainPresenter";

    private PhotoDataManager mPhotoDataManager;

    public MainPresenter() {
        setupDataManager();
    }

    @Override
    public void loadPhotos() {
        mPhotoDataManager.provideData();
    }

    @Override
    public void refreshPhotos() {
        mPhotoDataManager.resetPage();
        mPhotoDataManager.provideData();
    }

    private void setupDataManager() {
        mPhotoDataManager = new PhotoDataManager() {
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
