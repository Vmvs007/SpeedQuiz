package pt.ipp.estg.speedquiz.Models;

import java.util.List;

public class Circuits {
    private String circuitId;
    private String  url;
    private String circuitName;
    private Location location;

    public Circuits (String circuitId, String url, String circuitName, Location location) {
        this.circuitId = circuitId;
        this.url = url;
        this.circuitName = circuitName;
        this.location = location;
    }

    public String getCircuitId () {
        return circuitId;
    }

    public void setCircuitId (String circuitId) {
        this.circuitId = circuitId;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getCircuitName () {
        return circuitName;
    }

    public void setCircuitName (String circuitName) {
        this.circuitName = circuitName;
    }

    public Location getLocation () {
        return location;
    }

    public void setLocation (Location location) {
        this.location = location;
    }
}

class Location{

    private float lat;
    private float lon;
    private String locality;
    private String country;

    public Location (float lat, float lon, String locality, String country) {
        this.lat = lat;
        this.lon = lon;
        this.locality = locality;
        this.country = country;
    }

    public float getLat () {
        return lat;
    }

    public void setLat (float lat) {
        this.lat = lat;
    }

    public float getLon () {
        return lon;
    }

    public void setLon (float lon) {
        this.lon = lon;
    }

    public String getLocality () {
        return locality;
    }

    public void setLocality (String locality) {
        this.locality = locality;
    }

    public String getCountry () {
        return country;
    }

    public void setCountry (String country) {
        this.country = country;
    }
}
