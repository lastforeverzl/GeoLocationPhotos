package com.zackyzhang.geolocationphotos.mvp;

import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;

/**
 * Created by lei on 4/28/17.
 */

public interface MainContract {

    interface View extends MvpContract.MvpView {
        void loadPhotos(ReorgPhoto data);

        void setLoadingStatusFalse();
    }

    interface Presenter extends MvpContract.MvpPresenter<View> {

        void loadPhotos();

        void refreshPhotos();
    }

}
