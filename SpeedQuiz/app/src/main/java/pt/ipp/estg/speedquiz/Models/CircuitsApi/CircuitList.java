package pt.ipp.estg.speedquiz.Models.CircuitsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pt.ipp.estg.speedquiz.Models.MRData;

public class CircuitList {
    @SerializedName("MRData")
    @Expose
    private MRData mRData;

    public CircuitList(MRData mRData) {
        super();
        this.mRData = mRData;
    }


    public CircuitList() {
    }

    public MRData getMRData() {
        return mRData;
    }

    public void setMRData(MRData mRData) {
        this.mRData = mRData;
    }

}

