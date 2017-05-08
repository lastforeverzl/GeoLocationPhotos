package com.zackyzhang.geolocationphotos.mvp.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.zackyzhang.geolocationphotos.R;
import com.zackyzhang.geolocationphotos.data.SharedPreferencesManager;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;
import com.zackyzhang.geolocationphotos.mvp.QueryListAdapter;
import com.zackyzhang.geolocationphotos.mvp.SearchAdapter;
import com.zackyzhang.geolocationphotos.mvp.SearchContract;
import com.zackyzhang.geolocationphotos.mvp.presenter.SearchPresenter;

import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lei on 4/29/17.
 */

public class SearchActivity extends MvpActivity<SearchContract.View, SearchContract.Presenter>
        implements SearchContract.View, PlaceSelectionListener{
    private static final String TAG = "SearchActivity";

    private PlaceAutocompleteFragment placeAutocompleteFragment;
    private SearchAdapter mSearchAdapter;
    private QueryListAdapter mQueryListAdapter;
    private boolean isLoading = false;
    String lat;
    String lng;
    String location;

    @BindView(R.id.id_searchback)
    ImageButton searchBack;
    @BindView(R.id.rv_container)
    ViewGroup resultsContainer;
    @BindView(R.id.photo_list)
    RecyclerView recyclerView;
    @BindView(R.id.rv_recent_search)
    RecyclerView recentSearchList;
    @BindView(R.id.place_autocomplete_search_input)
    EditText searchInput;
    @BindView(R.id.place_autocomplete_clear_button)
    View clearButton;
    @BindView(R.id.place_autocomplete_search_button)
    View searchButton;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindInt(R.integer.num_columns)
    int columns;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupPlaceAutoComplete();
        setupRecyclerView();
        resultsContainer.setVisibility(View.GONE);
        presenter.getRecentQuery();
    }

    @Override
    protected SearchContract.Presenter createPresenter() {
        return new SearchPresenter(getApplicationContext());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onPlaceSelected(Place place) {
        if (place == null || place.getLatLng() == null) {
            Toast.makeText(this, "no result from search!", Toast.LENGTH_SHORT).show();
        }
        LatLng latLng = place.getLatLng();
        this.location = String.valueOf(place.getName());
        this.lat = String.valueOf(latLng.latitude);
        this.lng = String.valueOf(latLng.longitude);

        mSearchAdapter.clearAdapter();
        loadingPhotos(this.location);
    }

    @Override
    public void onError(Status status) {
        Log.e(TAG, "onError: Status = " + status.toString());
        Toast.makeText(this, "Place selection failed: " + status.getStatusMessage(), Toast.LENGTH_SHORT).show();
    }

    private void getPhotos(String location, String lat, String lng) {
        setLoadingStatusTrue();
        presenter.loadPhotos(lat, lng);
        presenter.setRecentQuery(location, lat, lng);
    }

    private void loadingPhotos(String place) {
        resultsContainer.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        getPhotos(place, this.lat, this.lng);
    }

    @Override
    public void loadPhotos(ReorgPhoto data) {
        mSearchAdapter.setPhotoList(data);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setLoadingStatusFalse() {
        this.isLoading = false;
        Log.d(TAG, "isLoading = false");
    }

    @Override
    public void loadRecentSearches(List<SharedPreferencesManager.RecentQuery> recentQueryList) {
        mQueryListAdapter.setQueryList(recentQueryList);
    }

    @Override
    public void clickRecentQuery(String location, String latitude, String longitude) {
        this.lat = latitude;
        this.lng = longitude;
        loadingPhotos(location);
        clearButton.setVisibility(View.VISIBLE);
        searchInput.setText(location);
    }

    public void setLoadingStatusTrue() {
        this.isLoading = true;
        Log.d(TAG, "isLoading = true");
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, columns));
        mSearchAdapter = new SearchAdapter(this);
        recyclerView.setAdapter(mSearchAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0 && !isLoading) {
                    Log.d(TAG, "loading more photos");
                    setLoadingStatusTrue();
                    presenter.loadPhotos(lat, lng);
                }
            }
        });

        ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(this)
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                .setRowStrategy(ChipsLayoutManager.STRATEGY_DEFAULT)
                .build();
        recentSearchList.setLayoutManager(chipsLayoutManager);
        recentSearchList.addItemDecoration(new SpacingItemDecoration(getResources().getDimensionPixelOffset(R.dimen.query_item_space),
                getResources().getDimensionPixelOffset(R.dimen.query_item_space)));
//        recentSearchList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mQueryListAdapter = new QueryListAdapter(this);
        recentSearchList.setAdapter(mQueryListAdapter);
    }

    private void setupPlaceAutoComplete() {
        placeAutocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        placeAutocompleteFragment.setOnPlaceSelectedListener(this);
        searchButton.setVisibility(View.GONE);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchAdapter.clearAdapter();
                placeAutocompleteFragment.setText("");
                clearButton.setVisibility(View.GONE);
                resultsContainer.setVisibility(View.GONE);
                presenter.getRecentQuery();
            }
        });
    }

    @OnClick({ R.id.id_searchback, R.id.scrim })
    protected void dismiss() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    @OnClick(R.id.clear_recent_query)
    protected void clearRecentSearch() {
        presenter.clearRecentSearch();
        mQueryListAdapter.clearQueryList();
    }

}
