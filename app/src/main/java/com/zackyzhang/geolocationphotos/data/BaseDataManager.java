package com.zackyzhang.geolocationphotos.data;

/**
 * Created by lei on 4/27/17.
 */

public abstract class BaseDataManager<T> {

    protected static final String EXTRAS = "geo,url_c,url_q";
    private FlickrApi mFlickrApi;

    public BaseDataManager() {
        mFlickrApi = FlickrApi.instance();
    }

    public abstract void onDataLoading(T data);

    public abstract void loadedData();

    protected FlickrService getFlickrApi() {
        return mFlickrApi.getApi();
    }

}
