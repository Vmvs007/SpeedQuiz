package pt.ipp.estg.speedquiz.Models.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Time_ {

    @SerializedName("time")
    @Expose
    private String time;

    public Time_() {
    }

    /**
     * @param time
     */
    public Time_(String time) {
        super();
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
