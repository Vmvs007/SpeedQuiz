package pt.ipp.estg.speedquiz.Models.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import pt.ipp.estg.speedquiz.Models.ConstructorsApi.Constructor;
import pt.ipp.estg.speedquiz.Models.DriversApi.Driver;
import pt.ipp.estg.speedquiz.Models.DriversApi.DriverTable;

public class Result {

    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("positionText")
    @Expose
    private String positionText;
    @SerializedName("points")
    @Expose
    private String points;
    @SerializedName("Driver")
    @Expose
    private Driver driver;
    @SerializedName("Constructor")
    @Expose
    private Constructor constructor;
    @SerializedName("grid")
    @Expose
    private String grid;
    @SerializedName("laps")
    @Expose
    private String laps;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Time")
    @Expose
    private Time time;
    @SerializedName("FastestLap")
    @Expose
    private FastestLap fastestLap;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param number
     * @param positionText
     * @param driver
     * @param grid
     * @param constructor
     * @param laps
     * @param position
     * @param time
     * @param fastestLap
     * @param points
     * @param status
     */
    public Result(String number, String position, String positionText, String points, Driver driver, Constructor constructor, String grid, String laps, String status, Time time, FastestLap fastestLap) {
        super();
        this.number = number;
        this.position = position;
        this.positionText = positionText;
        this.points = points;
        this.driver = driver;
        this.constructor = constructor;
        this.grid = grid;
        this.laps = laps;
        this.status = status;
        this.time = time;
        this.fastestLap = fastestLap;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionText() {
        return positionText;
    }

    public void setPositionText(String positionText) {
        this.positionText = positionText;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public String getLaps() {
        return laps;
    }

    public void setLaps(String laps) {
        this.laps = laps;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public FastestLap getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(FastestLap fastestLap) {
        this.fastestLap = fastestLap;
    }

}

