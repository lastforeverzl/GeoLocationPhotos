package com.zackyzhang.geolocationphotos.mvp.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.zackyzhang.geolocationphotos.R;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;
import com.zackyzhang.geolocationphotos.mvp.UserPhotosAdapter;
import com.zackyzhang.geolocationphotos.mvp.UserPhotosContract;
import com.zackyzhang.geolocationphotos.mvp.presenter.UserPhotosPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import timber.log.Timber;

/**
 * Created by lei on 5/10/17.
 */

public class UserPhotosActivity extends MvpActivity<UserPhotosContract.View, UserPhotosContract.Presenter>
        implements UserPhotosContract.View, UserPhotosAdapter.OnImageClickListener{

    public static final String INTENT_EXTRA_USER_ID = "com.zackyzhang.geolocationphotos.INTENT_EXTRA_USER_ID";
    public static final String INTENT_EXTRA_AVATAR_URL = "com.zackyzhang.geolocationphotos.INTENT_EXTRA_AVATAR_URL";
    public static final String INTENT_EXTRA_USERNAME = "com.zackyzhang.geolocationphotos.INTENT_EXTRA_USERNAME";

    private UserPhotosAdapter mUserPhotosAdapter;
    private boolean isLoading = false;
    String userId;
    String avatarUrl;
    String username;

    @BindView(R.id.photo_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.id_avatar)
    ImageView avatar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setSupportActionBar(mToolbar);
        setupRecyclerView();
        presenter.loadPhotos(userId);
    }

    @Override
    public void setLoadingStatusFalse() {
        this.isLoading = false;
    }

    public void setLoadingStatusTrue() {
        this.isLoading = true;
    }

    @Override
    public void loadPhotos(ReorgPhoto data) {
        mUserPhotosAdapter.setPhotoList(data);

        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected UserPhotosContract.Presenter createPresenter() {
        return new UserPhotosPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_userphotos;
    }

    private void init() {
        this.userId = getIntent().getStringExtra(INTENT_EXTRA_USER_ID);
        this.avatarUrl = getIntent().getStringExtra(INTENT_EXTRA_AVATAR_URL);
        this.username = getIntent().getStringExtra(INTENT_EXTRA_USERNAME);
        Glide.with(this)
                .load(avatarUrl)
                .bitmapTransform(new CropCircleTransformation(this))
                .placeholder(R.drawable.avatar_placeholder)
                .into(avatar);
        mToolbar.setTitle(username);
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mUserPhotosAdapter = new UserPhotosAdapter(this);
        mUserPhotosAdapter.setOnImageClickListener(this);
        mRecyclerView.setAdapter(mUserPhotosAdapter);
        // TODO: 5/10/17 recyclerview load more pages.
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0 && !isLoading) {
                    Timber.d("loading more photos");
                    setLoadingStatusTrue();
                    presenter.loadMorePhotos(userId);
                }
            }
        });
    }

    @OnClick(R.id.id_userphotosback)
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
}
