package com.zackyzhang.geolocationphotos.mvp.view;

import com.zackyzhang.geolocationphotos.mvp.SearchContract;
import com.zackyzhang.geolocationphotos.mvp.presenter.SearchPresenter;

/**
 * Created by lei on 4/29/17.
 */

public class SearchActivity extends MvpActivity<SearchContract.View, SearchContract.Presenter> implements SearchContract.View{

    @Override
    protected SearchContract.Presenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected int getLayout() {
        return 0;
    }
}
