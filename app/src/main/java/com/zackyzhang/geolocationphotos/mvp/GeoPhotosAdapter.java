package com.zackyzhang.geolocationphotos.mvp;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zackyzhang.geolocationphotos.BuildConfig;
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
    public static final int ZOOM = 12;
    public static final String HORIZONTAL_VALUE = "640";
    public static final String VERTICAL_VALUE = "300";
    public static final String KEY = BuildConfig.GOOGLE_API_KEY;
    public static final String SCALE = "2";

    public interface OnImageClickListener {
        void onPhotoClick(String photoUrl);

        void onMapClick(double lat, double lng);
    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<ReorgPhoto> mPhotos;
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
            View v = mLayoutInflater.inflate(R.layout.map_header, parent, false);
            holder = new HeaderHolder(v);
        } else {
            View v = mLayoutInflater.inflate(R.layout.search_list_item, parent, false);
            holder = new Holder(v);
        }
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            ((HeaderHolder) holder).bind();
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

    public class HeaderHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.map_view)
        ImageView mMapView;

        public HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind() {
            Glide.with(mContext)
                    .load(getMapUrl())
                    .centerCrop()
                    .into(mMapView);
        }

        @OnClick(R.id.map_view)
        public void click() {
            mOnImageClickListener.onMapClick(latitude, longitude);
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

    private String getMapUrl() {
        String center = String.valueOf(latitude) + "," + String.valueOf(longitude);
        String size = HORIZONTAL_VALUE + "x" + VERTICAL_VALUE;
        String url = Uri.parse("https://maps.googleapis.com/maps/api/staticmap")
                .buildUpon()
                .appendQueryParameter("center", center)
                .appendQueryParameter("zoom", String.valueOf(ZOOM))
                .appendQueryParameter("size", size)
                .appendQueryParameter("key", KEY)
                .appendQueryParameter("scale", SCALE)
                .appendQueryParameter("maptype", "terrain")
                .build().toString();
        Log.d(TAG, url);
        return url;
    }
}
