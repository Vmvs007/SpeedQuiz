package pt.ipp.estg.speedquiz.Models.DriversApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DriverTable {

    @SerializedName("driverId")
    @Expose
    private String driverId;

    @SerializedName("season")
    @Expose
    private String season;

    @SerializedName("Drivers")
    @Expose
    private List<Driver> drivers = null;

    public DriverTable () {
    }

    public DriverTable (String driverId, String season, List<Driver> drivers) {
        this.driverId = driverId;
        this.season = season;
        this.drivers = drivers;
    }
    public DriverTable(String season, List<Driver> drivers) {
        super();
        this.season = season;
        this.drivers = drivers;
    }

    public String getSeason () {
        return season;
    }

    public void setSeason (String season) {
        this.season = season;
    }

    public String getDriverId () {
        return driverId;
    }

    public void setDriverId (String driverId) {
        this.driverId = driverId;
    }

    public List<Driver> getDrivers () {
        return drivers;
    }

    public void setDrivers (List<Driver> drivers) {
        this.drivers = drivers;
    }
}
