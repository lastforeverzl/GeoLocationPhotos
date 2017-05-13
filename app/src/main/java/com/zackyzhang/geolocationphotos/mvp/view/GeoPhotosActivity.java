package com.zackyzhang.geolocationphotos.mvp.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
import com.zackyzhang.geolocationphotos.mvp.GeoPhotosAdapter;
import com.zackyzhang.geolocationphotos.mvp.GeoPhotosContract;
import com.zackyzhang.geolocationphotos.mvp.presenter.GeoPhotosPresenter;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lei on 5/8/17.
 */

public class GeoPhotosActivity extends MvpActivity<GeoPhotosContract.View, GeoPhotosContract.Presenter>
        implements GeoPhotosContract.View, GeoPhotosAdapter.OnImageClickListener {

    public static final String INTENT_EXTRA_LATITUDE = "com.zackyzhang.geolocationphotos.INTENT_EXTRA_LATITUDE";
    public static final String INTENT_EXTRA_LONGITUDE = "com.zackyzhang.geolocationphotos.INTENT_EXTRA_LONGITUDE";
    public static final String INTENT_EXTRA_LOCATION = "com.zackyzhang.geolocationphotos.INTENT_EXTRA_LOCATION";

    private static final String TAG = "GeoPhotosActivity";

    private GeoPhotosAdapter mGeoPhotosAdapter;
    private boolean isLoading = false;
    String lat;
    String lng;
    String location;

    @BindView(R.id.rv_geo_photos)
    RecyclerView mRecyclerView;
    @BindView(R.id.geo_photos_title)
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
        init();
        setupRecyclerView();
        presenter.loadPhotos(lat, lng);
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, columns));
        mGeoPhotosAdapter = new GeoPhotosAdapter(this);
        mGeoPhotosAdapter.setOnImageClickListener(this);
        mGeoPhotosAdapter.setLocation(this.lat, this.lng);
        mRecyclerView.setAdapter(mGeoPhotosAdapter);
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
        Log.d(TAG, "isLoading = false");
    }

    @Override
    public void loadPhotos(ReorgPhoto data) {
        mGeoPhotosAdapter.setPhotoList(data);
        mProgressBar.setVisibility(View.GONE);
    }

    public void setLoadingStatusTrue() {
        this.isLoading = true;
        Log.d(TAG, "isLoading = true");
    }

    private void init() {
        this.lat = getIntent().getStringExtra(INTENT_EXTRA_LATITUDE);
        this.lng = getIntent().getStringExtra(INTENT_EXTRA_LONGITUDE);
        this.location = getIntent().getStringExtra(INTENT_EXTRA_LOCATION);
        title.setText(location);
    }

    @Override
    protected GeoPhotosContract.Presenter createPresenter() {
        return new GeoPhotosPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_geophotos;
    }

    @OnClick(R.id.id_geophotosback)
    protected void dismiss() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    @Override
    public void onPhotoClick(String photoUrl) {
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra(ImageActivity.INTENT_EXTRA_PHOTO_URL, photoUrl);
        startActivity(intent);
    }

    @Override
    public void onMapClick(double lat, double lng) {
        createAlertDialog();
    }

    private void createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setMessage("Open in Google Map?");
        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText, (dialog, which) -> toGoogleMap());
        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText, (dialog, which) -> {});
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void toGoogleMap() {
        Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + lng + "?z=12");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

}
