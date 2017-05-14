package com.zackyzhang.geolocationphotos.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;

import timber.log.Timber;

/**
 * Created by lei on 5/1/17.
 */

public class SharedPreferencesManager {

    private static final String MY_APP_PREFERENCES = "MY_APP_PREFERENCES";
    private static final String RESENT_QUERY = "RESENT_QUERY";

    private SharedPreferences sharedPrefs;
    private static SharedPreferencesManager instance;
    private LinkedList<RecentQuery> mRecentQueryList;

    private SharedPreferencesManager(Context context){
        //using application context just to make sure we don't leak any activities
        sharedPrefs = context.getApplicationContext().getSharedPreferences(MY_APP_PREFERENCES, Context.MODE_PRIVATE);
        if (sharedPrefs.contains(RESENT_QUERY))
            mRecentQueryList = getRecentQuery();
        else
            mRecentQueryList = new LinkedList<>();
    }

    public static synchronized SharedPreferencesManager getInstance(Context context){
        if(instance == null)
            instance = new SharedPreferencesManager(context);

        return instance;
    }

    public void saveRecentQuery(String location, String lat, String lng) {
        if (location != null && lat != null && lng != null){
            RecentQuery rs = new RecentQuery(location);
            rs.setLatitude(lat);
            rs.setLongitude(lng);

            if (mRecentQueryList.contains(rs)) {
                Timber.d("contain one: " + rs.getLocation());
                mRecentQueryList.remove(rs);
            }
            mRecentQueryList.addFirst(rs);
        }

        Gson gson = new Gson();
        String json = gson.toJson(mRecentQueryList);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(RESENT_QUERY, json);
        editor.commit();
    }

    public void clearRecentQueryList() {
        mRecentQueryList.clear();
        saveRecentQuery(null, null, null);
    }

    public LinkedList<RecentQuery> getRecentQuery() {
        String json = sharedPrefs.getString(RESENT_QUERY, "");
        Gson gson = new Gson();
        Type type = new TypeToken<LinkedList<RecentQuery>>(){}.getType();
        LinkedList<RecentQuery> queryList = gson.fromJson(json, type);
        return queryList;
    }

    public class RecentQuery {
        String location;
        String latitude;
        String longitude;

        public RecentQuery(String location) {
            this.location = location;
        }

        public String getLocation() {
            return location;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        @Override
        public boolean equals(Object obj) {
            if( this == obj ) { return true; }
            if( obj instanceof RecentQuery ) {
                RecentQuery t = (RecentQuery) obj;
                return this.getLocation().equals(t.getLocation());
            }
            return false;
        }
    }

}
