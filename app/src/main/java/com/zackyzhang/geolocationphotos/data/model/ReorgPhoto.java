package com.zackyzhang.geolocationphotos.data.model;

/**
 * Created by lei on 4/28/17.
 */

public class ReorgPhoto {
    private String id;
    private String nsid;
    private String username;
    private String description;
    private String latitude;
    private String longitude;
    private String location;
    private String avatar_url;
    private String url_c;
    private String url_z;
    private String url_l;

    public ReorgPhoto(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNsid() {
        return nsid;
    }

    public void setNsid(String nsid) {
        this.nsid = nsid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUrlC() {
        return url_c;
    }

    public void setUrlC(String url_c) {
        this.url_c = url_c;
    }

    public String getUrlZ() {
        return url_z;
    }

    public void setUrlZ(String url_z) {
        this.url_z = url_z;
    }

    public String getUrlL() {
        return url_l;
    }

    public void setUrlL(String url_l) {
        this.url_l = url_l;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.id);
    }

    @Override
    public boolean equals(Object other) {
        if( this == other ) { return true; }
        if( other instanceof ReorgPhoto ) {
            ReorgPhoto t = (ReorgPhoto) other;
            return this.id == t.id;
        }
        return false;
    }
}
