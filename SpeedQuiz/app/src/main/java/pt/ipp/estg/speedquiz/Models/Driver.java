package pt.ipp.estg.speedquiz.Models;

import java.util.Date;

public class Driver {
    private int id;
    private String permanentNumber;
    private String code;
    private String  url;
    private String givenName;
    private String familyName;
    private Date dateOfBirth;
    private String nationality;


    public Driver (int id, String permanentNumber, String code, String url, String givenName, String familyName, Date dateOfBirth, String nationality) {
        this.id = id;
        this.permanentNumber = permanentNumber;
        this.code = code;
        this.url = url;
        this.givenName = givenName;
        this.familyName = familyName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }


    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getPermanentNumber () {
        return permanentNumber;
    }

    public void setPermanentNumber (String permanentNumber) {
        this.permanentNumber = permanentNumber;
    }

    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getGivenName () {
        return givenName;
    }

    public void setGivenName (String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName () {
        return familyName;
    }

    public void setFamilyName (String familyName) {
        this.familyName = familyName;
    }

    public Date getDateOfBirth () {
        return dateOfBirth;
    }

    public void setDateOfBirth (Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality () {
        return nationality;
    }

    public void setNationality (String nationality) {
        this.nationality = nationality;
    }
}
