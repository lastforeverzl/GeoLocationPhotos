package com.zackyzhang.geolocationphotos.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.zackyzhang.geolocationphotos.R;
import com.zackyzhang.geolocationphotos.mvp.ImageContract;
import com.zackyzhang.geolocationphotos.mvp.presenter.ImagePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lei on 5/11/17.
 */

public class ImageActivity extends MvpActivity<ImageContract.View, ImageContract.Presenter> implements ImageContract.View {

    public static final String INTENT_EXTRA_PHOTO_URL = "com.zackyzhang.geolocationphotos.INTENT_EXTRA_PHOTO_URL";

    String photoUrl;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.photoview_image)
    PhotoView mPhotoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        init();
        loadImage();
    }

    @Override
    protected ImageContract.Presenter createPresenter() {
        return new ImagePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_image;
    }

    private void init() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.photoUrl = getIntent().getStringExtra(INTENT_EXTRA_PHOTO_URL);
    }

    private void loadImage() {
        Glide.with(this)
                .load(photoUrl)
                .fitCenter()
                .into(mPhotoView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.image_menu:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @OnClick(R.id.image_scrim)
    public void dismiss() {
        finish();
    }
}
