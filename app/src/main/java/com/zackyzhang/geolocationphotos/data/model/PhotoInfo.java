package com.zackyzhang.geolocationphotos.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lei on 4/27/17.
 */

public class PhotoInfo {
    /**
     * id : 34155506782
     * secret : 8b93c9b6fd
     * server : 2888
     * farm : 3
     * dateuploaded : 1493351156
     * isfavorite : 0
     * license : 0
     * safety_level : 0
     * rotation : 90
     * originalsecret : 3191139bf5
     * originalformat : jpg
     * owner : {"nsid":"79022225@N02","username":"mhkose","realname":"Muhammed Köse","location":"Istanbul, Türkiye","iconserver":"3940","iconfarm":4,"path_alias":"mhkose"}
     * title : {"_content":"Urfa"}
     * description : {"_content":""}
     * visibility : {"ispublic":1,"isfriend":0,"isfamily":0}
     * dates : {"posted":"1493351156","taken":"2017-04-22 11:02:07","takengranularity":0,"takenunknown":0,"lastupdate":"1493351158"}
     * views : 0
     * editability : {"cancomment":0,"canaddmeta":0}
     * publiceditability : {"cancomment":1,"canaddmeta":0}
     * usage : {"candownload":1,"canblog":0,"canprint":0,"canshare":1}
     * comments : {"_content":0}
     * notes : {"note":[]}
     * people : {"haspeople":0}
     * tags : {"tag":[]}
     * location : {"latitude":37.147797,"longitude":38.785244,"accuracy":16,"context":0,"locality":{"_content":"Sanliurfa","place_id":"..sw5elTUb7eGTm.","woeid":"2344354"},"county":{"_content":"Merkez","place_id":"U8VbsLFTW7l0D30ZAg","woeid":"29390954"},"region":{"_content":"Sanliurfa","place_id":"P_SJHytTUb6Q6vdH","woeid":"2347314"},"country":{"_content":"Turkey","place_id":"aaobVhlTUb58c3KqSA","woeid":"23424969"},"place_id":"..sw5elTUb7eGTm.","woeid":"2344354"}
     * geoperms : {"ispublic":1,"iscontact":0,"isfriend":0,"isfamily":0}
     * urls : {"url":[{"type":"photopage","_content":"https://www.flickr.com/photos/mhkose/34155506782/"}]}
     * media : photo
     */

    @SerializedName("id")
    private String id;
    @SerializedName("secret")
    private String secret;
    @SerializedName("server")
    private String server;
    @SerializedName("farm")
    private int farm;
    @SerializedName("dateuploaded")
    private String dateuploaded;
    @SerializedName("isfavorite")
    private int isfavorite;
    @SerializedName("license")
    private int license;
    @SerializedName("safety_level")
    private int safetyLevel;
    @SerializedName("rotation")
    private int rotation;
    @SerializedName("originalsecret")
    private String originalsecret;
    @SerializedName("originalformat")
    private String originalformat;
    @SerializedName("owner")
    private Owner owner;
    @SerializedName("title")
    private Title title;
    @SerializedName("description")
    private Description description;
    @SerializedName("visibility")
    private Visibility visibility;
    @SerializedName("dates")
    private Dates dates;
    @SerializedName("views")
    private int views;
    @SerializedName("editability")
    private Editability editability;
    @SerializedName("publiceditability")
    private Publiceditability publiceditability;
    @SerializedName("usage")
    private Usage usage;
    @SerializedName("comments")
    private Comments comments;
    @SerializedName("notes")
    private Notes notes;
    @SerializedName("people")
    private People people;
    @SerializedName("tags")
    private Tags tags;
    @SerializedName("location")
    private Location location;
    @SerializedName("geoperms")
    private Geoperms geoperms;
    @SerializedName("urls")
    private Urls urls;
    @SerializedName("media")
    private String media;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDateuploaded() {
        return dateuploaded;
    }

    public void setDateuploaded(String dateuploaded) {
        this.dateuploaded = dateuploaded;
    }

    public int getIsfavorite() {
        return isfavorite;
    }

    public void setIsfavorite(int isfavorite) {
        this.isfavorite = isfavorite;
    }

    public int getLicense() {
        return license;
    }

    public void setLicense(int license) {
        this.license = license;
    }

    public int getSafetyLevel() {
        return safetyLevel;
    }

    public void setSafetyLevel(int safetyLevel) {
        this.safetyLevel = safetyLevel;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public String getOriginalsecret() {
        return originalsecret;
    }

    public void setOriginalsecret(String originalsecret) {
        this.originalsecret = originalsecret;
    }

    public String getOriginalformat() {
        return originalformat;
    }

    public void setOriginalformat(String originalformat) {
        this.originalformat = originalformat;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Editability getEditability() {
        return editability;
    }

    public void setEditability(Editability editability) {
        this.editability = editability;
    }

    public Publiceditability getPubliceditability() {
        return publiceditability;
    }

    public void setPubliceditability(Publiceditability publiceditability) {
        this.publiceditability = publiceditability;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Geoperms getGeoperms() {
        return geoperms;
    }

    public void setGeoperms(Geoperms geoperms) {
        this.geoperms = geoperms;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public static class Owner {
        /**
         * nsid : 79022225@N02
         * username : mhkose
         * realname : Muhammed Köse
         * location : Istanbul, Türkiye
         * iconserver : 3940
         * iconfarm : 4
         * path_alias : mhkose
         */

        @SerializedName("nsid")
        private String nsid;
        @SerializedName("username")
        private String username;
        @SerializedName("realname")
        private String realname;
        @SerializedName("location")
        private String location;
        @SerializedName("iconserver")
        private String iconserver;
        @SerializedName("iconfarm")
        private int iconfarm;
        @SerializedName("path_alias")
        private String pathAlias;

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

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getIconserver() {
            return iconserver;
        }

        public void setIconserver(String iconserver) {
            this.iconserver = iconserver;
        }

        public int getIconfarm() {
            return iconfarm;
        }

        public void setIconfarm(int iconfarm) {
            this.iconfarm = iconfarm;
        }

        public String getPathAlias() {
            return pathAlias;
        }

        public void setPathAlias(String pathAlias) {
            this.pathAlias = pathAlias;
        }
    }

    public static class Title {
        /**
         * _content : Urfa
         */

        @SerializedName("_content")
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class Description {
        /**
         * _content :
         */

        @SerializedName("_content")
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class Visibility {
        /**
         * ispublic : 1
         * isfriend : 0
         * isfamily : 0
         */

        @SerializedName("ispublic")
        private int ispublic;
        @SerializedName("isfriend")
        private int isfriend;
        @SerializedName("isfamily")
        private int isfamily;

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
    }

    public static class Dates {
        /**
         * posted : 1493351156
         * taken : 2017-04-22 11:02:07
         * takengranularity : 0
         * takenunknown : 0
         * lastupdate : 1493351158
         */

        @SerializedName("posted")
        private String posted;
        @SerializedName("taken")
        private String taken;
        @SerializedName("takengranularity")
        private int takengranularity;
        @SerializedName("takenunknown")
        private int takenunknown;
        @SerializedName("lastupdate")
        private String lastupdate;

        public String getPosted() {
            return posted;
        }

        public void setPosted(String posted) {
            this.posted = posted;
        }

        public String getTaken() {
            return taken;
        }

        public void setTaken(String taken) {
            this.taken = taken;
        }

        public int getTakengranularity() {
            return takengranularity;
        }

        public void setTakengranularity(int takengranularity) {
            this.takengranularity = takengranularity;
        }

        public int getTakenunknown() {
            return takenunknown;
        }

        public void setTakenunknown(int takenunknown) {
            this.takenunknown = takenunknown;
        }

        public String getLastupdate() {
            return lastupdate;
        }

        public void setLastupdate(String lastupdate) {
            this.lastupdate = lastupdate;
        }
    }

    public static class Editability {
        /**
         * cancomment : 0
         * canaddmeta : 0
         */

        @SerializedName("cancomment")
        private int cancomment;
        @SerializedName("canaddmeta")
        private int canaddmeta;

        public int getCancomment() {
            return cancomment;
        }

        public void setCancomment(int cancomment) {
            this.cancomment = cancomment;
        }

        public int getCanaddmeta() {
            return canaddmeta;
        }

        public void setCanaddmeta(int canaddmeta) {
            this.canaddmeta = canaddmeta;
        }
    }

    public static class Publiceditability {
        /**
         * cancomment : 1
         * canaddmeta : 0
         */

        @SerializedName("cancomment")
        private int cancomment;
        @SerializedName("canaddmeta")
        private int canaddmeta;

        public int getCancomment() {
            return cancomment;
        }

        public void setCancomment(int cancomment) {
            this.cancomment = cancomment;
        }

        public int getCanaddmeta() {
            return canaddmeta;
        }

        public void setCanaddmeta(int canaddmeta) {
            this.canaddmeta = canaddmeta;
        }
    }

    public static class Usage {
        /**
         * candownload : 1
         * canblog : 0
         * canprint : 0
         * canshare : 1
         */

        @SerializedName("candownload")
        private int candownload;
        @SerializedName("canblog")
        private int canblog;
        @SerializedName("canprint")
        private int canprint;
        @SerializedName("canshare")
        private int canshare;

        public int getCandownload() {
            return candownload;
        }

        public void setCandownload(int candownload) {
            this.candownload = candownload;
        }

        public int getCanblog() {
            return canblog;
        }

        public void setCanblog(int canblog) {
            this.canblog = canblog;
        }

        public int getCanprint() {
            return canprint;
        }

        public void setCanprint(int canprint) {
            this.canprint = canprint;
        }

        public int getCanshare() {
            return canshare;
        }

        public void setCanshare(int canshare) {
            this.canshare = canshare;
        }
    }

    public static class Comments {
        /**
         * _content : 0
         */

        @SerializedName("_content")
        private int content;

        public int getContent() {
            return content;
        }

        public void setContent(int content) {
            this.content = content;
        }
    }

    public static class Notes {
        @SerializedName("note")
        private List<?> note;

        public List<?> getNote() {
            return note;
        }

        public void setNote(List<?> note) {
            this.note = note;
        }
    }

    public static class People {
        /**
         * haspeople : 0
         */

        @SerializedName("haspeople")
        private int haspeople;

        public int getHaspeople() {
            return haspeople;
        }

        public void setHaspeople(int haspeople) {
            this.haspeople = haspeople;
        }
    }

    public static class Tags {
        @SerializedName("tag")
        private List<?> tag;

        public List<?> getTag() {
            return tag;
        }

        public void setTag(List<?> tag) {
            this.tag = tag;
        }
    }

    public static class Location {
        /**
         * latitude : 37.147797
         * longitude : 38.785244
         * accuracy : 16
         * context : 0
         * locality : {"_content":"Sanliurfa","place_id":"..sw5elTUb7eGTm.","woeid":"2344354"}
         * county : {"_content":"Merkez","place_id":"U8VbsLFTW7l0D30ZAg","woeid":"29390954"}
         * region : {"_content":"Sanliurfa","place_id":"P_SJHytTUb6Q6vdH","woeid":"2347314"}
         * country : {"_content":"Turkey","place_id":"aaobVhlTUb58c3KqSA","woeid":"23424969"}
         * place_id : ..sw5elTUb7eGTm.
         * woeid : 2344354
         */

        @SerializedName("latitude")
        private double latitude;
        @SerializedName("longitude")
        private double longitude;
        @SerializedName("accuracy")
        private int accuracy;
        @SerializedName("context")
        private int context;
        @SerializedName("locality")
        private Locality locality;
        @SerializedName("county")
        private County county;
        @SerializedName("region")
        private Region region;
        @SerializedName("country")
        private Country country;
        @SerializedName("place_id")
        private String placeId;
        @SerializedName("woeid")
        private String woeid;

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

        public Locality getLocality() {
            return locality;
        }

        public void setLocality(Locality locality) {
            this.locality = locality;
        }

        public County getCounty() {
            return county;
        }

        public void setCounty(County county) {
            this.county = county;
        }

        public Region getRegion() {
            return region;
        }

        public void setRegion(Region region) {
            this.region = region;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
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

        public static class Locality {
            /**
             * _content : Sanliurfa
             * place_id : ..sw5elTUb7eGTm.
             * woeid : 2344354
             */

            @SerializedName("_content")
            private String content;
            @SerializedName("place_id")
            private String placeId;
            @SerializedName("woeid")
            private String woeid;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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
        }

        public static class County {
            /**
             * _content : Merkez
             * place_id : U8VbsLFTW7l0D30ZAg
             * woeid : 29390954
             */

            @SerializedName("_content")
            private String content;
            @SerializedName("place_id")
            private String placeId;
            @SerializedName("woeid")
            private String woeid;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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
        }

        public static class Region {
            /**
             * _content : Sanliurfa
             * place_id : P_SJHytTUb6Q6vdH
             * woeid : 2347314
             */

            @SerializedName("_content")
            private String content;
            @SerializedName("place_id")
            private String placeId;
            @SerializedName("woeid")
            private String woeid;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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
        }

        public static class Country {
            /**
             * _content : Turkey
             * place_id : aaobVhlTUb58c3KqSA
             * woeid : 23424969
             */

            @SerializedName("_content")
            private String content;
            @SerializedName("place_id")
            private String placeId;
            @SerializedName("woeid")
            private String woeid;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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
        }
    }

    public static class Geoperms {
        /**
         * ispublic : 1
         * iscontact : 0
         * isfriend : 0
         * isfamily : 0
         */

        @SerializedName("ispublic")
        private int ispublic;
        @SerializedName("iscontact")
        private int iscontact;
        @SerializedName("isfriend")
        private int isfriend;
        @SerializedName("isfamily")
        private int isfamily;

        public int getIspublic() {
            return ispublic;
        }

        public void setIspublic(int ispublic) {
            this.ispublic = ispublic;
        }

        public int getIscontact() {
            return iscontact;
        }

        public void setIscontact(int iscontact) {
            this.iscontact = iscontact;
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
    }

    public static class Urls {
        @SerializedName("url")
        private List<Url> url;

        public List<Url> getUrl() {
            return url;
        }

        public void setUrl(List<Url> url) {
            this.url = url;
        }

        public static class Url {
            /**
             * type : photopage
             * _content : https://www.flickr.com/photos/mhkose/34155506782/
             */

            @SerializedName("type")
            private String type;
            @SerializedName("_content")
            private String content;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
