package com.zackyzhang.geolocationphotos.data;

import com.zackyzhang.geolocationphotos.data.model.PhotoInfoResponse;
import com.zackyzhang.geolocationphotos.data.model.PhotosResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lei on 4/27/17.
 */

public interface FlickrService {

    String ENDPOINT = "https://api.flickr.com/services/rest/";

    @GET("?method=flickr.photos.search&has_geo=1&format=json&nojsoncallback=1")
    Observable<PhotosResponse> getRecent(@Query("api_key") String api_key,
                                         @Query("extras") String extras,
                                         @Query("page") String page,
                                         @Query("per_page") String perPage);

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    Observable<PhotosResponse> getSearch(@Query("api_key") String api_key,
                                         @Query("extras") String extras,
                                         @Query("page") String page,
                                         @Query("lat") String latitude,
                                         @Query("lon") String longitude);

    @GET("?method=flickr.photos.getInfo&format=json&nojsoncallback=1")
    Observable<PhotoInfoResponse> getPhotoInfo(@Query("api_key") String api_key,
                                               @Query("photo_id") String photo_id);
}
