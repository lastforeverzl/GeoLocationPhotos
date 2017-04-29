package com.zackyzhang.geolocationphotos.mvp.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zackyzhang.geolocationphotos.R;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;
import com.zackyzhang.geolocationphotos.mvp.MainAdapter;
import com.zackyzhang.geolocationphotos.mvp.MainContract;
import com.zackyzhang.geolocationphotos.mvp.presenter.MainPresenter;

import butterknife.BindView;

public class MainActivity extends MvpActivity<MainContract.View, MainContract.Presenter> implements MainContract.View {

    private static final String TAG = "MainActivity";

    private MainAdapter mMainAdapter;
    private boolean isLoading = false;
    private boolean isRefreshing = false;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.photo_list)
    RecyclerView recyclerView;
    @BindView(R.id.id_swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupRecyclerView();
        getPhotos();
    }

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                View searchMenuView = mToolbar.findViewById(R.id.menu_search);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void loadPhotos(ReorgPhoto data) {
        mMainAdapter.setPhotoList(data);
    }

    @Override
    public void setLoadingStatusFalse() {
        this.isLoading = false;
        Log.d(TAG, "isLoading = false");
    }

    public void setLoadingStatusTrue() {
        this.isLoading = true;
        Log.d(TAG, "isLoading = true");
    }

    private void getPhotos() {
        setLoadingStatusTrue();
        presenter.loadPhotos();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        mMainAdapter = new MainAdapter(this);
        recyclerView.setAdapter(mMainAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                super.onScrolled(rv, dx, dy);

                int lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0 && !isLoading) {
                    Log.d(TAG, "loading more photos");
                    setLoadingStatusTrue();
                    presenter.loadPhotos();
                }
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isRefreshing) {
                    isRefreshing = true;
                    setLoadingStatusTrue();
                    mMainAdapter.clearAdapter();
                    presenter.refreshPhotos();
                    mSwipeRefreshLayout.setRefreshing(false);
                    isRefreshing = false;
                }
            }
        });
    }
}
