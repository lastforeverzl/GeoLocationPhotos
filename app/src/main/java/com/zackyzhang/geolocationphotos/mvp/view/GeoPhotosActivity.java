package com.zackyzhang.geolocationphotos.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zackyzhang.geolocationphotos.R;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;
import com.zackyzhang.geolocationphotos.mvp.GeoPhotosContract;
import com.zackyzhang.geolocationphotos.mvp.SearchAdapter;
import com.zackyzhang.geolocationphotos.mvp.presenter.GeoPhotosPresenter;

import butterknife.BindInt;
import butterknife.BindView;

/**
 * Created by lei on 5/8/17.
 */

public class GeoPhotosActivity extends MvpActivity<GeoPhotosContract.View, GeoPhotosContract.Presenter>
        implements GeoPhotosContract.View {

    public static final String INTENT_EXTRA_LATITUDE = "com.zackyzhang.geolocationphotos.INTENT_EXTRA_LATITUDE";
    public static final String INTENT_EXTRA_LONGITUDE = "com.zackyzhang.geolocationphotos.INTENT_EXTRA_LONGITUDE";

    private static final String TAG = "GeoPhotosActivity";

    private SearchAdapter mSearchAdapter;
    private boolean isLoading = false;
    String lat;
    String lng;

    @BindView(R.id.rv_geo_photos)
    RecyclerView mRecyclerView;
    @BindView(R.id.title_geo_photos)
    TextView title;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindInt(R.integer.num_columns)
    int columns;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        setupRecyclerView();
        init();
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, columns));
        mSearchAdapter = new SearchAdapter(this);
        mRecyclerView.setAdapter(mSearchAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0 && !isLoading) {
                    Log.d(TAG, "loading more photos");
                    setLoadingStatusTrue();
                    presenter.loadMorePhotos(lat, lng);
                }
            }
        });
    }

    @Override
    public void setLoadingStatusFalse() {
        this.isLoading = false;
    }

    @Override
    public void loadPhotos(ReorgPhoto data) {
        mSearchAdapter.setPhotoList(data);
        mProgressBar.setVisibility(View.GONE);
    }

    public void setLoadingStatusTrue() {
        this.isLoading = true;
    }

    private void init() {
        this.lat = getIntent().getStringExtra(GeoPhotosActivity.INTENT_EXTRA_LATITUDE);
        this.lng = getIntent().getStringExtra(GeoPhotosActivity.INTENT_EXTRA_LONGITUDE);
        presenter.loadPhotos(lat, lng);
    }

    @Override
    protected GeoPhotosContract.Presenter createPresenter() {
        return new GeoPhotosPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_geophotos;
    }
}
