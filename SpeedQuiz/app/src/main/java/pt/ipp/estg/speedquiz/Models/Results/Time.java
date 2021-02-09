package pt.ipp.estg.speedquiz.Models.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Time {

    @SerializedName("millis")
    @Expose
    private String millis;
    @SerializedName("time")
    @Expose
    private String time;

    /**
     * No args constructor for use in serialization
     *
     */
    public Time() {
    }

    /**
     *
     * @param time
     * @param millis
     */
    public Time(String millis, String time) {
        super();
        this.millis = millis;
        this.time = time;
    }

    public String getMillis() {
        return millis;
    }

    public void setMillis(String millis) {
        this.millis = millis;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
