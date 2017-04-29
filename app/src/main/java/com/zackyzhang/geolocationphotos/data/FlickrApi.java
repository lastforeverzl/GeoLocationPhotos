package com.zackyzhang.geolocationphotos.data;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lei on 4/27/17.
 */

public class FlickrApi {
    private static final String TAG = "FlickrApi";

    private static FlickrApi sInstance;
    private FlickrService api;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;

    public static FlickrApi instance() {
        if (sInstance == null) {
            sInstance = new FlickrApi();
        }
        return sInstance;
    }

    private FlickrApi() {
        mOkHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(logInterceptor())
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(FlickrService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static HttpLoggingInterceptor logInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Log.d("OkHttp", message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }

    public FlickrService getApi() {
        if (api == null) createApi();
        return api;
    }

    private void createApi() {
        Log.d(TAG, "in create APi");
        api = mRetrofit.create(FlickrService.class);
    }
}
