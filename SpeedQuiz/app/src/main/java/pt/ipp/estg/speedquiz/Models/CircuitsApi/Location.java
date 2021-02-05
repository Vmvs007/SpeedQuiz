package pt.ipp.estg.speedquiz.Models.CircuitsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("country")
    @Expose
    private String country;

    public Location () {
    }


    public Location (String lat, String _long, String locality, String country) {
        this.lat = lat;
        this._long = _long;
        this.locality = locality;
        this.country = country;
    }


    public String getLat () {
        return lat;
    }

    public void setLat (String lat) {
        this.lat = lat;
    }

    public String get_long () {
        return _long;
    }

    public void set_long (String _long) {
        this._long = _long;
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
