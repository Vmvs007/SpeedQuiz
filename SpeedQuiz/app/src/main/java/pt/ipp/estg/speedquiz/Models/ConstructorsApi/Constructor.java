package pt.ipp.estg.speedquiz.Models.ConstructorsApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Constructor {

    @SerializedName("constructorId")
    @Expose
    private String constructorId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nationality")
    @Expose
    private String nationality;


    public Constructor () {
    }

    public Constructor (String constructorId, String url, String name, String nationality) {
        this.constructorId = constructorId;
        this.url = url;
        this.name = name;
        this.nationality = nationality;
    }


    public String getConstructorId () {
        return constructorId;
    }

    public void setConstructorId (String constructorId) {
        this.constructorId = constructorId;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getNationality () {
        return nationality;
    }

    public void setNationality (String nationality) {
        this.nationality = nationality;
    }
}
