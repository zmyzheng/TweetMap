package hello;

import org.elasticsearch.common.geo.GeoPoint;

import java.util.Date;

public class TweetEntity {
//    public class Geo {
//        private double lat;
//        private double lon;
//        public Geo(double lat, double lon) {
//            this.lat = lat;
//            this.lon = lon;
//        }
//        public double getLat() { return lat;}
//        public void setLat(double lat) {this.lat = lat; }
//        public double getLon() { return lon;}
//        public void setLon(double lon) {this.lon = lon; }
//    }

    private String keyword;
    private String username;
    private String place;


//    private double[] location;

    private GeoPoint location;

    private long tweetId;
    private String text;
    private Date createdAt;

    public TweetEntity(String keyword, String username, String place, double longitude, double latitude, long tweetId, String text, Date createdAt) {
        this.keyword = keyword;
        this.username = username;
        this.place = place;
        this.location = new GeoPoint(latitude, longitude);
//        this.location = new double[] {latitude, longitude};

        this.tweetId = tweetId;
        this.text = text;
        this.createdAt = createdAt;
    }
    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long id) {
        this.tweetId = id;
    }

//    public double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }
//
//    public double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public GeoPoint getLocation() { return location; }

    public void setLocation(GeoPoint location) { this.location = location;}

}
