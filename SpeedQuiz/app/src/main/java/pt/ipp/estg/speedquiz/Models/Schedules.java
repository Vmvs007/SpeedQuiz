package pt.ipp.estg.speedquiz.Models;

import java.sql.Time;
import java.util.Date;

import pt.ipp.estg.speedquiz.Models.CircuitsApi.Circuit;

public class Schedules {

    private int position;
    private String positionText;
    private int points;

    public Schedules (int position, String positionText, int points) {
        this.position = position;
        this.positionText = positionText;
        this.points = points;
    }


    public int getPosition () {
        return position;
    }

    public void setPosition (int position) {
        this.position = position;
    }

    public String getPositionText () {
        return positionText;
    }

    public void setPositionText (String positionText) {
        this.positionText = positionText;
    }

    public int getPoints () {
        return points;
    }

    public void setPoints (int points) {
        this.points = points;
    }
}

class Race {

    private int season;
    private int round;
    private String  url;
    private String raceName;
    private Circuit circuit;
    private Date date;
    private Time time;

    public Race (int season, int round, String url, String raceName, Circuit circuit, Date date, Time time) {
        this.season = season;
        this.round = round;
        this.url = url;
        this.raceName = raceName;
        this.circuit = circuit;
        this.date = date;
        this.time = time;
    }

    public int getSeason () {
        return season;
    }

    public void setSeason (int season) {
        this.season = season;
    }

    public int getRound () {
        return round;
    }

    public void setRound (int round) {
        this.round = round;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getRaceName () {
        return raceName;
    }

    public void setRaceName (String raceName) {
        this.raceName = raceName;
    }

    public Circuit getCircuit () {
        return circuit;
    }

    public void setCircuit (Circuit circuit) {
        this.circuit = circuit;
    }

    public Date getDate () {
        return date;
    }

    public void setDate (Date date) {
        this.date = date;
    }

    public Time getTime () {
        return time;
    }

    public void setTime (Time time) {
        this.time = time;
    }
}