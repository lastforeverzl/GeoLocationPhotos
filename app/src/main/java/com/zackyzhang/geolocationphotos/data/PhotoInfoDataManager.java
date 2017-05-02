package com.zackyzhang.geolocationphotos.data;

/**
 * Created by lei on 4/27/17.
 */

public abstract class PhotoInfoDataManager extends BaseDataManager {

    public PhotoInfoDataManager() {
        super();
    }

//    private void loadPhotoInfo(String photoId) {
//        getFlickrApi().getPhotoInfo(BuildConfig.FLICKR_API_KEY, photoId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(photoInfoResponse -> photoInfoResponse.getPhoto())
//                .subscribe(photoInfo -> onDataLoading(photoInfo),
//                        error -> Log.d("MainActivity", error.getMessage()),
//                        () -> Log.d("MainActivity", "load photoInfo finished"));
//    }

//    public void loadPhoto(String photoId) {
//        loadPhotoInfo(photoId);
//    }
}
