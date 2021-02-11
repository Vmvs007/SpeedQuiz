package pt.ipp.estg.speedquiz.Models.ConstructorsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pt.ipp.estg.speedquiz.Models.MRData;

public class ConstructorList {

    @SerializedName("MRData")
    @Expose
    private MRData mRData;

    public ConstructorList(MRData mRData) {
        this.mRData = mRData;
    }

    public MRData getmRData() {
        return mRData;
    }

    public void setmRData(MRData mRData) {
        this.mRData = mRData;
    }
}
