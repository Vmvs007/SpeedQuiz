package pt.ipp.estg.speedquiz.Models.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pt.ipp.estg.speedquiz.Models.MRData;

public class ResultsList {

    @SerializedName("MRData")
    @Expose
    private MRData mRData;


    public ResultsList() {
    }

    /**
     * @param mRData
     */
    public ResultsList(MRData mRData) {
        super();
        this.mRData = mRData;
    }

    public MRData getMRData() {
        return mRData;
    }

    public void setMRData(MRData mRData) {
        this.mRData = mRData;
    }

}
