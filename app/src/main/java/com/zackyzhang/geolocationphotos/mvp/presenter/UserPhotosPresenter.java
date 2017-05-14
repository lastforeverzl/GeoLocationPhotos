package com.zackyzhang.geolocationphotos.mvp.presenter;

import com.zackyzhang.geolocationphotos.data.UserPhotosDataManager;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;
import com.zackyzhang.geolocationphotos.mvp.UserPhotosContract;

/**
 * Created by lei on 5/10/17.
 */

public class UserPhotosPresenter extends MvpPresenter<UserPhotosContract.View> implements UserPhotosContract.Presenter {

    UserPhotosDataManager mUserPhotosDataManager;

    public UserPhotosPresenter() {
        setupDataManager();
    }

    @Override
    public void loadPhotos(String userId) {
        mUserPhotosDataManager.provideData(userId);
    }

    @Override
    public void loadMorePhotos(String userId) {
        mUserPhotosDataManager.provideData(userId);
    }

    private void setupDataManager() {
        mUserPhotosDataManager = new UserPhotosDataManager() {
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
