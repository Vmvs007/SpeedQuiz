package pt.ipp.estg.speedquiz.Models.DriversApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DriverTable {

    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("Drivers")
    @Expose
    private List<Driver> drivers = null;

    public DriverTable () {
    }

    public DriverTable (String driverId, List<Driver> drivers) {
        this.driverId = driverId;
        this.drivers = drivers;
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
