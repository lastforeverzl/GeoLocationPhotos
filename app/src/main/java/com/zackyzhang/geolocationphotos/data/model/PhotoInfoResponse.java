package com.zackyzhang.geolocationphotos.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lei on 4/27/17.
 */

public class PhotoInfoResponse {

    /**
     * photo : {"id":"34155506782","secret":"8b93c9b6fd","server":"2888","farm":3,"dateuploaded":"1493351156","isfavorite":0,"license":0,"safety_level":0,"rotation":90,"originalsecret":"3191139bf5","originalformat":"jpg","owner":{"nsid":"79022225@N02","username":"mhkose","realname":"Muhammed Köse","location":"Istanbul, Türkiye","iconserver":"3940","iconfarm":4,"path_alias":"mhkose"},"title":{"_content":"Urfa"},"description":{"_content":""},"visibility":{"ispublic":1,"isfriend":0,"isfamily":0},"dates":{"posted":"1493351156","taken":"2017-04-22 11:02:07","takengranularity":0,"takenunknown":0,"lastupdate":"1493351158"},"views":0,"editability":{"cancomment":0,"canaddmeta":0},"publiceditability":{"cancomment":1,"canaddmeta":0},"usage":{"candownload":1,"canblog":0,"canprint":0,"canshare":1},"comments":{"_content":0},"notes":{"note":[]},"people":{"haspeople":0},"tags":{"tag":[]},"location":{"latitude":37.147797,"longitude":38.785244,"accuracy":16,"context":0,"locality":{"_content":"Sanliurfa","place_id":"..sw5elTUb7eGTm.","woeid":"2344354"},"county":{"_content":"Merkez","place_id":"U8VbsLFTW7l0D30ZAg","woeid":"29390954"},"region":{"_content":"Sanliurfa","place_id":"P_SJHytTUb6Q6vdH","woeid":"2347314"},"country":{"_content":"Turkey","place_id":"aaobVhlTUb58c3KqSA","woeid":"23424969"},"place_id":"..sw5elTUb7eGTm.","woeid":"2344354"},"geoperms":{"ispublic":1,"iscontact":0,"isfriend":0,"isfamily":0},"urls":{"url":[{"type":"photopage","_content":"https://www.flickr.com/photos/mhkose/34155506782/"}]},"media":"photo"}
     * stat : ok
     */

    @SerializedName("photo")
    private PhotoInfo photo;
    @SerializedName("stat")
    private String stat;

    public PhotoInfo getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoInfo photo) {
        this.photo = photo;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}
