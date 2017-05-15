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
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import timber.log.Timber;

/**
 * Created by lei on 4/28/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.Holder> {

    public interface OnItemClickListener {
        void onLocationClick(String location, String lat, String lng);

        void onAvatarClick(View avatar, String nsid, String avatarUrl, String username);

        void onPhotoClick(String photoUrl, String photoPageUrl);
    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<ReorgPhoto> mPhotos;
    private OnItemClickListener mOnItemClickListener;

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
        holder.bind(mPhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public void clearAdapter() {
        mPhotos.clear();
        notifyDataSetChanged();
    }

    public void setPhotoList(ReorgPhoto photo) {
        for (ReorgPhoto item : mPhotos) {
            if (item.getId().equals(photo.getId())) {
                Timber.d("duplicate id: " + photo.getId());
//                mPhotos.remove(photo.getId());
            }
        }
        this.mPhotos.add(photo);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ReorgPhoto mReorgPhoto;

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
        @BindView(R.id.location_container)
        LinearLayout locationContainer;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ReorgPhoto reorgPhoto) {
            mReorgPhoto = reorgPhoto;
            Glide.with(mContext)
                    .load(mReorgPhoto.getUrlC())
                    .centerCrop()
                    .into(photo);
            if (mReorgPhoto.getDescription() == "") {
                description.setVisibility(View.GONE);
            } else {
                description.setText(mReorgPhoto.getDescription());
            }

            location.setText(mReorgPhoto.getLocation());
            userName.setText(mReorgPhoto.getUsername());
            Glide.with(mContext)
                    .load(mReorgPhoto.getAvatar_url())
                    .bitmapTransform(new CropCircleTransformation(mContext))
                    .placeholder(R.drawable.avatar_placeholder)
                    .into(avatar);
            locationContainer.setOnClickListener(this);
            avatar.setOnClickListener(this);
            photo.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.location_container:
                    mOnItemClickListener.onLocationClick(mReorgPhoto.getLocation(), mReorgPhoto.getLatitude(), mReorgPhoto.getLongitude());
                    break;
                case R.id.id_avatar:
                    mOnItemClickListener.onAvatarClick(avatar, mReorgPhoto.getNsid(), mReorgPhoto.getAvatar_url(), mReorgPhoto.getUsername());
                    break;
                case R.id.id_photo:
                    mOnItemClickListener.onPhotoClick(mReorgPhoto.getUrlL(), mReorgPhoto.getUrlPhotoPage());
                default:
                    break;
            }

        }
    }

}
