package ims.entity;

public class Location {
    private String streetName;
    private String city;
    private String longitude;
    private String latitude;

    public Location(String streetName, String city, String longitude, String latitude) {
        this.streetName = streetName;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
