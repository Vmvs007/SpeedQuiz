package pt.ipp.estg.speedquiz.Models.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AverageSpeed {

    @SerializedName("units")
    @Expose
    private String units;
    @SerializedName("speed")
    @Expose
    private String speed;


    public AverageSpeed() {
    }

    /**
     * @param units
     * @param speed
     */
    public AverageSpeed(String units, String speed) {
        super();
        this.units = units;
        this.speed = speed;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }


}
