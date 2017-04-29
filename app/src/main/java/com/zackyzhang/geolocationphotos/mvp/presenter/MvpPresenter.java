package com.zackyzhang.geolocationphotos.mvp.presenter;

import com.zackyzhang.geolocationphotos.mvp.MvpContract;

import java.lang.ref.SoftReference;

/**
 * Created by lei on 4/28/17.
 */

public class MvpPresenter<V extends MvpContract.MvpView> implements MvpContract.MvpPresenter<V> {

    private SoftReference<V> viewReference;

    @Override
    public void attachView(V view) {
        viewReference = new SoftReference(view);
    }

    public V getView() {
        return viewReference == null ? null : viewReference.get();
    }

    @Override
    public void detachView() {
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }
}
