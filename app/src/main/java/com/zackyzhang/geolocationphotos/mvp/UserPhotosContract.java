package com.zackyzhang.geolocationphotos.mvp;

import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;

/**
 * Created by lei on 5/10/17.
 */

public interface UserPhotosContract {

    interface View extends MvpContract.MvpView {
        void setLoadingStatusFalse();

        void loadPhotos(ReorgPhoto data);
    }

    interface Presenter extends MvpContract.MvpPresenter<View> {
        void loadPhotos(String userId);

        void loadMorePhotos(String userId);
    }

}
