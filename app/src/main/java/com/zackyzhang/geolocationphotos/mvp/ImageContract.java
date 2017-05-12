package com.zackyzhang.geolocationphotos.mvp;

/**
 * Created by lei on 5/11/17.
 */

public interface ImageContract {

    interface View extends MvpContract.MvpView {

    }

    interface Presenter extends MvpContract.MvpPresenter<View> {

    }
}
