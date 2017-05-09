package com.zackyzhang.geolocationphotos.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.zackyzhang.geolocationphotos.data.SearchDataManager;
import com.zackyzhang.geolocationphotos.data.SharedPreferencesManager;
import com.zackyzhang.geolocationphotos.data.SharedPreferencesManager.RecentQuery;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;
import com.zackyzhang.geolocationphotos.mvp.SearchContract;

import java.util.List;

/**
 * Created by lei on 4/29/17.
 */

public class SearchPresenter extends MvpPresenter<SearchContract.View> implements SearchContract.Presenter {
    private static final String TAG = "SearchPresenter";

    private SearchDataManager mSearchDataManager;
    private SharedPreferencesManager mSharedPreferencesManager;

    public SearchPresenter(Context context) {
        mSharedPreferencesManager = SharedPreferencesManager.getInstance(context);
        setupDataManager();
    }

    @Override
    public void loadPhotos(String lat, String lng) {
        mSearchDataManager.resetPage();
        mSearchDataManager.provideData(lat, lng);
    }

    @Override
    public void loadMorePhotos(String lat, String lng) {
        mSearchDataManager.provideData(lat, lng);
    }

    @Override
    public void getRecentQuery() {
        List<RecentQuery> queryList = mSharedPreferencesManager.getRecentQuery();
        for (RecentQuery item : queryList) {
            Log.d(TAG, item.getLocation() + ": " + item.getLatitude() + ", " + item.getLongitude());
        }
        getView().loadRecentSearches(queryList);
    }

    @Override
    public void setRecentQuery(String location, String lat, String lng) {
        mSharedPreferencesManager.saveRecentQuery(location, lat, lng);
    }

    @Override
    public void clearRecentSearch() {
        mSharedPreferencesManager.clearRecentQueryList();
    }

    private void setupDataManager() {
        mSearchDataManager = new SearchDataManager() {
            @Override
            public void onDataLoading(ReorgPhoto data) {
                getView().loadPhotos(data);
            }

            @Override
            public void loadedData() {
                getView().setLoadingStatusFalse();
            }
        };
    }
}
