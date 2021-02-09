package pt.ipp.estg.speedquiz.Models.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FastestLap {

    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("lap")
    @Expose
    private String lap;
    @SerializedName("Time")
    @Expose
    private Time_ time;
    @SerializedName("AverageSpeed")
    @Expose
    private AverageSpeed averageSpeed;

    /**
     * No args constructor for use in serialization
     *
     */
    public FastestLap() {
    }

    /**
     *
     * @param rank
     * @param lap
     * @param averageSpeed
     * @param time
     */
    public FastestLap(String rank, String lap, Time_ time, AverageSpeed averageSpeed) {
        super();
        this.rank = rank;
        this.lap = lap;
        this.time = time;
        this.averageSpeed = averageSpeed;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getLap() {
        return lap;
    }

    public void setLap(String lap) {
        this.lap = lap;
    }

    public Time_ getTime() {
        return time;
    }

    public void setTime(Time_ time) {
        this.time = time;
    }

    public AverageSpeed getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(AverageSpeed averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

}
