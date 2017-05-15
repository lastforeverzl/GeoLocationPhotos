package com.zackyzhang.geolocationphotos.mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zackyzhang.geolocationphotos.R;
import com.zackyzhang.geolocationphotos.data.model.ReorgPhoto;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lei on 5/10/17.
 */

public class UserPhotosAdapter extends RecyclerView.Adapter<UserPhotosAdapter.Holder> {

    public interface OnImageClickListener {
        void onPhotoClick(String photoUrl, String photoPageUrl);
    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<ReorgPhoto> mPhotos;
    private OnImageClickListener mOnImageClickListener;

    public UserPhotosAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mPhotos = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.user_photo_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mPhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public void setPhotoList(ReorgPhoto photo) {
        this.mPhotos.add(photo);
        notifyDataSetChanged();
    }

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.mOnImageClickListener = onImageClickListener;
    }

    public class Holder extends RecyclerView.ViewHolder {

        ReorgPhoto mReorgPhoto;

        @BindView(R.id.id_location)
        TextView location;
        @BindView(R.id.id_photo)
        ImageView photo;
        @BindView(R.id.photo_description)
        TextView description;
        @BindView(R.id.location_container)
        LinearLayout locationContainer;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ReorgPhoto reorgPhoto) {
            mReorgPhoto = reorgPhoto;
            if (reorgPhoto.getDescription() == "") {
                description.setVisibility(View.GONE);
            } else {
                description.setText(reorgPhoto.getDescription());
            }

            location.setText(reorgPhoto.getLocation());
            Glide.with(mContext)
                    .load(reorgPhoto.getUrlC())
                    .centerCrop()
                    .into(photo);
        }

        @OnClick(R.id.id_photo)
        public void click() {
            mOnImageClickListener.onPhotoClick(mReorgPhoto.getUrlL(), mReorgPhoto.getUrlPhotoPage());
        }
    }

}
