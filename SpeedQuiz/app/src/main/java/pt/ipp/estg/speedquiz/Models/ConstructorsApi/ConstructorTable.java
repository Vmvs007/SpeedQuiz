package pt.ipp.estg.speedquiz.Models.ConstructorsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConstructorTable {

    @SerializedName("Constructors")
    @Expose
    private List<Constructor> constructors = null;

    public ConstructorTable() {
    }

    public ConstructorTable(List<Constructor> constructors) {
        this.constructors = constructors;
    }

    public List<Constructor> getConstructors() {
        return constructors;
    }

    public void setConstructors(List<Constructor> constructors) {
        this.constructors = constructors;
    }
}
