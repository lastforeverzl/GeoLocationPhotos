package com.zackyzhang.geolocationphotos.mvp;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.zackyzhang.geolocationphotos.R;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lei on 4/29/17.
 */

public class GeoPhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "GeoPhotosAdapter";

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    public interface OnImageClickListener {
        void onPhotoClick(String photoUrl);
    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<ReorgPhoto> mPhotos;
    private MapView mapView;
    private double latitude;
    private double longitude;
    private OnImageClickListener mOnImageClickListener;

    public GeoPhotosAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mPhotos = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == TYPE_HEADER) {
            Log.d(TAG, "onCreateViewHolder TYPE_HEADER");
            View v = mLayoutInflater.inflate(R.layout.map_header, parent, false);
            holder = new HeaderHolder(v);
            if (mapView == null) {
                Log.d(TAG, "mapView is null, will create a new one.");
                mapView = ((HeaderHolder) holder).mMapView;
            }
        } else {
            View v = mLayoutInflater.inflate(R.layout.search_list_item, parent, false);
            holder = new Holder(v);
        }
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
//            ((HeaderHolder) holder).mTextView.setText("This is Header");
        } else {
            ((Holder) holder).bind(mPhotos.get(position));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    public void clearAdapter() {
        mPhotos.clear();
        notifyDataSetChanged();
    }

    public void setLocation(String lat, String lng) {
        this.latitude = Double.valueOf(lat);
        this.longitude = Double.valueOf(lng);
    }

    public void setPhotoList(ReorgPhoto photo) {
        for (ReorgPhoto item : mPhotos) {
            if (item.getId().equals(photo.getId())) {
                Log.d(TAG, "duplicate id: " + photo.getId());
//                mPhotos.remove(photo.getId());
            }
        }
        this.mPhotos.add(photo);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public MapView getMapView() {
        return this.mapView;
    }

    public class HeaderHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {

        protected GoogleMap mGoogleMap;

        @BindView(R.id.map_view)
        MapView mMapView;

        public HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mGoogleMap = googleMap;

            MapsInitializer.initialize(mContext);
            mGoogleMap.getUiSettings().setMapToolbarEnabled(false);
            mGoogleMap.clear();

            LatLng latLng = new LatLng(latitude, longitude);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 13f);
            mGoogleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_map_marker)));
            mGoogleMap.moveCamera(cameraUpdate);
            mGoogleMap.setOnMapClickListener(latLng1 -> Toast.makeText(mContext, "onMapClick", Toast.LENGTH_SHORT).show());
        }
    }

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.mOnImageClickListener = onImageClickListener;
    }

    public class Holder extends RecyclerView.ViewHolder {

        ReorgPhoto mReorgPhoto;

        @BindView(R.id.photo)
        ImageView photo;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ReorgPhoto reorgPhoto) {
            mReorgPhoto = reorgPhoto;
            Glide.with(mContext)
                    .load(reorgPhoto.getUrlZ())
                    .centerCrop()
                    .into(photo);
        }

        @OnClick(R.id.photo)
        public void click() {
            mOnImageClickListener.onPhotoClick(mReorgPhoto.getUrlL());
        }
    }

}
