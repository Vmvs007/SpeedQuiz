package pt.ipp.estg.speedquiz.Models.CircuitsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CircuitTable {


    @SerializedName("Circuits")
    @Expose
    private List<Circuit> circuits = null;

    public CircuitTable() {
    }

    public CircuitTable(List<Circuit> circuits) {
        this.circuits = circuits;
    }


    public List<Circuit> getCircuits() {
        return circuits;
    }

    public void setCircuits(List<Circuit> circuits) {
        this.circuits = circuits;
    }
}
