package com.zackyzhang.geolocationphotos.mvp.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zackyzhang.geolocationphotos.R;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;
import com.zackyzhang.geolocationphotos.mvp.MainAdapter;
import com.zackyzhang.geolocationphotos.mvp.MainContract;
import com.zackyzhang.geolocationphotos.mvp.presenter.MainPresenter;

import butterknife.BindView;

public class MainActivity extends MvpActivity<MainContract.View, MainContract.Presenter> implements MainContract.View, MainAdapter.OnItemClickListener {

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
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
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
                Intent intent = new Intent(this, SearchActivity.class);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Bundle options = ActivityOptions.makeSceneTransitionAnimation(this, searchMenuView,
                            getString(R.string.transition_search_back)).toBundle();
                    startActivity(intent, options);
                } else startActivity(intent);

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
        mProgressBar.setVisibility(View.GONE);
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
        mProgressBar.setVisibility(View.VISIBLE);
        setLoadingStatusTrue();
        presenter.loadPhotos();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        mMainAdapter = new MainAdapter(this);
        mMainAdapter.setOnItemClickListener(this);
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

    @Override
    public void onLocationClick(String location, String lat, String lng) {
        Toast.makeText(this, "lat: " + lat + ", lng: " + lng, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, GeoPhotosActivity.class);
        intent.putExtra(GeoPhotosActivity.INTENT_EXTRA_LOCATION, location);
        intent.putExtra(GeoPhotosActivity.INTENT_EXTRA_LATITUDE, lat);
        intent.putExtra(GeoPhotosActivity.INTENT_EXTRA_LONGITUDE, lng);
        startActivity(intent);
    }

    @Override
    public void onAvatarClick(View avatar, String nsid, String avatarUrl, String username) {
        Toast.makeText(this, nsid, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, UserPhotosActivity.class);
        intent.putExtra(UserPhotosActivity.INTENT_EXTRA_USER_ID, nsid);
        intent.putExtra(UserPhotosActivity.INTENT_EXTRA_AVATAR_URL, avatarUrl);
        intent.putExtra(UserPhotosActivity.INTENT_EXTRA_USERNAME, username);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions transitionActivityOptions = ActivityOptions.
                    makeSceneTransitionAnimation(MainActivity.this, avatar, getString(R.string.avatar_transition));
            startActivity(intent, transitionActivityOptions.toBundle());
        } else {
            startActivity(intent);
        }

    }

    @Override
    public void onPhotoClick(String photoUrl) {
        Intent intent = new Intent(this, ImageActivity.class);
        Log.d(TAG, photoUrl);
        intent.putExtra(ImageActivity.INTENT_EXTRA_PHOTO_URL, photoUrl);
        startActivity(intent);
    }

}
