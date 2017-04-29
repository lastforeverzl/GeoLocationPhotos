package com.zackyzhang.geolocationphotos.mvp;

/**
 * Created by lei on 4/29/17.
 */

public interface SearchContract {
    interface View extends MvpContract.MvpView {

    }

    interface Presenter extends MvpContract.MvpPresenter<View> {
    }

}
