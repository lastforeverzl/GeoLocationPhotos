package com.zackyzhang.geolocationphotos.mvp;

/**
 * Created by lei on 4/28/17.
 */

public interface MvpContract {

    interface MvpView {
    }

    interface MvpPresenter<V extends MvpView> {
        void attachView(V view);

        void detachView();
    }
}
