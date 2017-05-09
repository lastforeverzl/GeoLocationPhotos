package com.zackyzhang.geolocationphotos.mvp;

import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;

/**
 * Created by lei on 5/8/17.
 */

public interface GeoPhotosContract {
    interface View extends MvpContract.MvpView {
        void setLoadingStatusFalse();

        void loadPhotos(ReorgPhoto data);
    }

    interface Presenter extends MvpContract.MvpPresenter<View> {
        void loadPhotos(String lat, String lng);

        void loadMorePhotos(String lat, String lng);
    }

}
