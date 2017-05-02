package com.zackyzhang.geolocationphotos.mvp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zackyzhang.geolocationphotos.R;
import com.zackyzhang.geolocationphotos.data.SharedPreferencesManager;
import com.zackyzhang.geolocationphotos.mvp.view.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lei on 5/1/17.
 */

public class QueryListAdapter extends RecyclerView.Adapter<QueryListAdapter.Holder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<SharedPreferencesManager.RecentQuery> queryList;

    public QueryListAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.queryList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.query_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return queryList.size();
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String location = queryList.get(position).getLocation();
        String lat = queryList.get(position).getLatitude();
        String lng = queryList.get(position).getLongitude();
        holder.queryText.setText(location);
        holder.queryContainer.setOnClickListener(
                v -> ((SearchActivity) mContext).clickRecentQuery(location, lat, lng));
    }

    public void setQueryList(List<SharedPreferencesManager.RecentQuery> list) {
        queryList = list;
        notifyDataSetChanged();
    }

    public void clearQueryList() {
        queryList.clear();
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.id_recent_search)
        TextView queryText;
        @BindView(R.id.query_container)
        CardView queryContainer;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
