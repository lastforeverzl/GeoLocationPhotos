package com.zackyzhang.geolocationphotos.mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zackyzhang.geolocationphotos.R;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lei on 5/9/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Holder> {
    private static final String TAG = "SearchAdapter";

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<ReorgPhoto> mPhotos;

    public SearchAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mPhotos = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.search_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ReorgPhoto photo = mPhotos.get(position);
        Glide.with(mContext)
                .load(photo.getUrlZ())
                .centerCrop()
                .into(holder.photo);
    }

    public void clearAdapter() {
        mPhotos.clear();
        notifyDataSetChanged();
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
    public int getItemCount() {
        return mPhotos.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.photo)
        ImageView photo;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}