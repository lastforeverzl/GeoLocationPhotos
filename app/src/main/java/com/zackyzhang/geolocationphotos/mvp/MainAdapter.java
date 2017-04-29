package com.zackyzhang.geolocationphotos.mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zackyzhang.geolocationphotos.R;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by lei on 4/28/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.Holder> {
    private static final String TAG = "MainAdapter";

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<ReorgPhoto> mPhotos;

    public MainAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mPhotos = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.photo_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ReorgPhoto photo = mPhotos.get(position);
        Glide.with(mContext)
                .load(photo.getUrlC())
                .centerCrop()
                .into(holder.photo);
        holder.description.setText(photo.getDescription());
        holder.location.setText(photo.getLocation());
        holder.userName.setText(photo.getUsername());
        Glide.with(mContext)
                .load(photo.getAvatar_url())
                .bitmapTransform(new CropCircleTransformation(mContext))
                .placeholder(R.drawable.avatar_placeholder)
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public void clearAdapter() {
        mPhotos.clear();
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

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.id_avatar)
        ImageView avatar;
        @BindView(R.id.id_user)
        TextView userName;
        @BindView(R.id.id_location)
        TextView location;
        @BindView(R.id.id_photo)
        ImageView photo;
        @BindView(R.id.photo_description)
        TextView description;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
