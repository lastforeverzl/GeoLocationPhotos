package com.zackyzhang.geolocationphotos.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lei on 4/27/17.
 */

public class Photo extends BasePhoto{
    /**
     * id : 33471701264
     * owner : 37996646802@N01
     * secret : 6ac98141bf
     * server : 2833
     * farm : 3
     * title : The DS106 Room
     * ispublic : 1
     * isfriend : 0
     * isfamily : 0
     * latitude : 43.119513
     * longitude : 79.249205
     * accuracy : 16
     * context : 0
     * place_id : 5PQ0LbxTUrpiJTkRjA
     * woeid : 20070180
     * geo_is_family : 0
     * geo_is_friend : 0
     * geo_is_contact : 0
     * geo_is_public : 1
     * url_c : https://farm3.staticflickr.com/2833/33471701264_6ac98141bf_c.jpg
     * height_c : 600
     * width_c : 800
     * url_q : https://farm3.staticflickr.com/2833/33471701264_6ac98141bf_q.jpg
     * height_q : 150
     * width_q : 150
     */

    @SerializedName("id")
    private String id;
    @SerializedName("owner")
    private String owner;
    @SerializedName("secret")
    private String secret;
    @SerializedName("server")
    private String server;
    @SerializedName("farm")
    private int farm;
    @SerializedName("title")
    private String title;
    @SerializedName("ispublic")
    private int ispublic;
    @SerializedName("isfriend")
    private int isfriend;
    @SerializedName("isfamily")
    private int isfamily;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("accuracy")
    private int accuracy;
    @SerializedName("context")
    private int context;
    @SerializedName("place_id")
    private String placeId;
    @SerializedName("woeid")
    private String woeid;
    @SerializedName("geo_is_family")
    private int geoIsFamily;
    @SerializedName("geo_is_friend")
    private int geoIsFriend;
    @SerializedName("geo_is_contact")
    private int geoIsContact;
    @SerializedName("geo_is_public")
    private int geoIsPublic;
    @SerializedName("url_c")
    private String urlC;
    @SerializedName("height_c")
    private String heightC;
    @SerializedName("width_c")
    private String widthC;
    @SerializedName("url_z")
    private String urlZ;
    @SerializedName("height_z")
    private String heightZ;
    @SerializedName("width_z")
    private String widthZ;
    @SerializedName("url_l")
    private String urlL;
    @SerializedName("height_l")
    private String heightL;
    @SerializedName("width_l")
    private String widthL;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIspublic() {
        return ispublic;
    }

    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }

    public int getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(int isfriend) {
        this.isfriend = isfriend;
    }

    public int getIsfamily() {
        return isfamily;
    }

    public void setIsfamily(int isfamily) {
        this.isfamily = isfamily;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getContext() {
        return context;
    }

    public void setContext(int context) {
        this.context = context;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getWoeid() {
        return woeid;
    }

    public void setWoeid(String woeid) {
        this.woeid = woeid;
    }

    public int getGeoIsFamily() {
        return geoIsFamily;
    }

    public void setGeoIsFamily(int geoIsFamily) {
        this.geoIsFamily = geoIsFamily;
    }

    public int getGeoIsFriend() {
        return geoIsFriend;
    }

    public void setGeoIsFriend(int geoIsFriend) {
        this.geoIsFriend = geoIsFriend;
    }

    public int getGeoIsContact() {
        return geoIsContact;
    }

    public void setGeoIsContact(int geoIsContact) {
        this.geoIsContact = geoIsContact;
    }

    public int getGeoIsPublic() {
        return geoIsPublic;
    }

    public void setGeoIsPublic(int geoIsPublic) {
        this.geoIsPublic = geoIsPublic;
    }

    public String getUrlC() {
        return urlC;
    }

    public void setUrlC(String urlC) {
        this.urlC = urlC;
    }

    public String getHeightC() {
        return heightC;
    }

    public void setHeightC(String heightC) {
        this.heightC = heightC;
    }

    public String getWidthC() {
        return widthC;
    }

    public void setWidthC(String widthC) {
        this.widthC = widthC;
    }

    public String getUrlZ() {
        return urlZ;
    }

    public void setUrlZ(String urlQ) {
        this.urlZ = urlZ;
    }

    public String getHeightZ() {
        return heightZ;
    }

    public void setHeightZ(String heightZ) {
        this.heightZ = heightZ;
    }

    public String getwidthZ() {
        return widthZ;
    }

    public void setwidthZ(String widthQ) {
        this.widthZ = widthZ;
    }

    public String getUrlL() {
        return urlL;
    }

    public void setUrlL(String urlL) {
        this.urlL = urlL;
    }

    public String getHeightL() {
        return heightL;
    }

    public void setHeightL(String heightL) {
        this.heightL = heightL;
    }

    public String getwidthL() {
        return widthL;
    }

    public void setwidthL(String widthL) {
        this.widthL = widthL;
    }
}