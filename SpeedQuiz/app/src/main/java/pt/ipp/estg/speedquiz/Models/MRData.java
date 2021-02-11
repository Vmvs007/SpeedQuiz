package pt.ipp.estg.speedquiz.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pt.ipp.estg.speedquiz.Models.CircuitsApi.CircuitTable;
import pt.ipp.estg.speedquiz.Models.ConstructorsApi.ConstructorTable;
import pt.ipp.estg.speedquiz.Models.DriversApi.DriverTable;
import pt.ipp.estg.speedquiz.Models.Results.RaceTable;

public class MRData {

    @SerializedName("xmlns")
    @Expose
    private String xmlns;
    @SerializedName("series")
    @Expose
    private String series;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("limit")
    @Expose
    private String limit;
    @SerializedName("offset")
    @Expose
    private String offset;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("DriverTable")
    @Expose
    private DriverTable driverTable;
    @SerializedName("ConstructorTable")
    @Expose
    private ConstructorTable constructorTable;
    @SerializedName("CircuitTable")
    @Expose
    private CircuitTable circuitTable;
    @SerializedName("RaceTable")
    @Expose
    private RaceTable raceTable;

    public MRData() {
    }

    public MRData(String xmlns, String series, String url, String limit, String offset, String total, DriverTable driverTable) {
        super();
        this.xmlns = xmlns;
        this.series = series;
        this.url = url;
        this.limit = limit;
        this.offset = offset;
        this.total = total;
        this.driverTable = driverTable;
    }

    public MRData(String xmlns, String series, String url, String limit, String offset, String total, DriverTable driverTable, ConstructorTable constructorTable, CircuitTable circuitTable, RaceTable raceTable) {
        this.xmlns = xmlns;
        this.series = series;
        this.url = url;
        this.limit = limit;
        this.offset = offset;
        this.total = total;
        this.driverTable = driverTable;
        this.constructorTable = constructorTable;
        this.circuitTable = circuitTable;
        this.raceTable = raceTable;
    }

    public ConstructorTable getConstructorTable() {
        return constructorTable;
    }

    public void setConstructorTable(ConstructorTable constructorTable) {
        this.constructorTable = constructorTable;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public DriverTable getDriverTable() {
        return driverTable;
    }

    public void setDriverTable(DriverTable driverTable) {
        this.driverTable = driverTable;
    }

    public RaceTable getRaceTable() {
        return raceTable;
    }

    public void setRaceTable(RaceTable raceTable) {
        this.raceTable = raceTable;
    }
}
