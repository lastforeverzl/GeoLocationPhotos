package com.zackyzhang.geolocationphotos.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lei on 4/27/17.
 */

public class PhotosResponse {


    /**
     * photos : {"page":1,"pages":"40671","perpage":5,"total":"203353","photo":[{"id":"33471701264","owner":"37996646802@N01","secret":"6ac98141bf","server":"2833","farm":3,"title":"The DS106 Room","ispublic":1,"isfriend":0,"isfamily":0,"latitude":43.119513,"longitude":79.249205,"accuracy":16,"context":0,"place_id":"5PQ0LbxTUrpiJTkRjA","woeid":"20070180","geo_is_family":0,"geo_is_friend":0,"geo_is_contact":0,"geo_is_public":1,"url_c":"https://farm3.staticflickr.com/2833/33471701264_6ac98141bf_c.jpg","height_c":"600","width_c":"800","url_q":"https://farm3.staticflickr.com/2833/33471701264_6ac98141bf_q.jpg","height_q":"150","width_q":"150"},{"id":"34272501106","owner":"20846638@N04","secret":"f97e3218ac","server":"2930","farm":3,"title":"We marvel at the #artifact created by someone else... #handmade #building  #architecture #rain #ct #street #connecticut #stamford #perspective #hipsta #hipstamatic #hipstaconnect #hipstography #hipsta_crazy #hipsta_junky #remages #shotoniphone #shotonipho","ispublic":1,"isfriend":0,"isfamily":0,"latitude":41.0744,"longitude":-73.5413,"accuracy":16,"context":0,"place_id":"DaOejC1TVr7KXOhc","woeid":"2441621","geo_is_family":0,"geo_is_friend":0,"geo_is_contact":0,"geo_is_public":1,"url_c":"https://farm3.staticflickr.com/2930/34272501106_f97e3218ac_c.jpg","height_c":"800","width_c":"641","url_q":"https://farm3.staticflickr.com/2930/34272501106_f97e3218ac_q.jpg","height_q":"150","width_q":"150"},{"id":"34155398902","owner":"59154793@N05","secret":"565c813912","server":"2817","farm":3,"title":"[20170428.오늘의 순간] 충무궁 이순신 장군 탄신일에 참배를 하고 우리나라의 내일을 생각해 본다. #현충사 #충무공이순신","ispublic":1,"isfriend":0,"isfamily":0,"latitude":36.805338,"longitude":"127.030901","accuracy":16,"context":0,"place_id":"jxx6oPZTWrilRzKR2g","woeid":"28289111","geo_is_family":0,"geo_is_friend":0,"geo_is_contact":0,"geo_is_public":1,"url_c":"https://farm3.staticflickr.com/2817/34155398902_565c813912_c.jpg","height_c":"800","width_c":"800","url_q":"https://farm3.staticflickr.com/2817/34155398902_565c813912_q.jpg","height_q":"150","width_q":"150"},{"id":"34313227795","owner":"69572043@N00","secret":"2b4f7f42e4","server":"2877","farm":3,"title":"R570_20161011_16.jpg","ispublic":1,"isfriend":0,"isfamily":0,"latitude":-8.507348,"longitude":"115.263233","accuracy":16,"context":0,"place_id":"pofIr4ZUVLqaK14mdQ","woeid":"56013230","geo_is_family":0,"geo_is_friend":0,"geo_is_contact":0,"geo_is_public":1,"url_c":"https://farm3.staticflickr.com/2877/34313227795_2b4f7f42e4_c.jpg","height_c":"536","width_c":"800","url_q":"https://farm3.staticflickr.com/2877/34313227795_2b4f7f42e4_q.jpg","height_q":"150","width_q":"150"},{"id":"34272495936","owner":"12024263@N00","secret":"5a950d6a6b","server":"2890","farm":3,"title":"SI and I.","ispublic":1,"isfriend":0,"isfamily":0,"latitude":13.731425,"longitude":100.56951,"accuracy":16,"context":0,"place_id":"DhT7cNtTWrk.uOI5yA","woeid":"28349143","geo_is_family":0,"geo_is_friend":0,"geo_is_contact":0,"geo_is_public":1,"url_c":"https://farm3.staticflickr.com/2890/34272495936_5a950d6a6b_c.jpg","height_c":"800","width_c":"800","url_q":"https://farm3.staticflickr.com/2890/34272495936_5a950d6a6b_q.jpg","height_q":"150","width_q":"150"}]}
     * stat : ok
     */

    @SerializedName("photos")
    private Photos photos;
    @SerializedName("stat")
    private String stat;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }


}
