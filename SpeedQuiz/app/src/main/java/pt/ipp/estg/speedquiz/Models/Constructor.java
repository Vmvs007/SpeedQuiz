package pt.ipp.estg.speedquiz.Models;

import java.util.Date;

public class Constructor {

    private int id;
    private String name;
    private String  url;
    private String nationality;

    public Constructor (int id, String name, String url, String nationality) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.nationality = nationality;
    }


    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getNationality () {
        return nationality;
    }

    public void setNationality (String nationality) {
        this.nationality = nationality;
    }
}
