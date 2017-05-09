package com.zackyzhang.geolocationphotos.mvp;

import com.zackyzhang.geolocationphotos.data.SharedPreferencesManager;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;

import java.util.List;

/**
 * Created by lei on 4/29/17.
 */

public interface SearchContract {
    interface View extends MvpContract.MvpView {

        void loadPhotos(ReorgPhoto data);

        void setLoadingStatusFalse();

        void loadRecentSearches(List<SharedPreferencesManager.RecentQuery> recentQueryList);

        void clickRecentQuery(String location, String latitude, String longitude);
    }

    interface Presenter extends MvpContract.MvpPresenter<View> {
        void loadPhotos(String lat, String lng);

        void loadMorePhotos(String lat, String lng);

        void getRecentQuery();

        void setRecentQuery(String location, String lat, String lng);

        void clearRecentSearch();
    }

}
