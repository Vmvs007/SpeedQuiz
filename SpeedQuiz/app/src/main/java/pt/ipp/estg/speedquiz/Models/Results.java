package pt.ipp.estg.speedquiz.Models;

import java.util.List;

public class Results {
    private int season;
    private int round;
    private List<Position> DriverStandings;

    public Results (int season, int round, List<Position> driverStandings) {
        this.season = season;
        this.round = round;
        DriverStandings = driverStandings;
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

    public List<Position> getDriverStandings () {
        return DriverStandings;
    }

    public void setDriverStandings (List<Position> driverStandings) {
        DriverStandings = driverStandings;
    }
}


class Position{
    private int position;
    private String positionText;
    private int points;
    private int wins;
    private Driver driver;
    private Constructor constructor;

    public Position (int position, String positionText, int points, int wins, Driver driver, Constructor constructor) {
        this.position = position;
        this.positionText = positionText;
        this.points = points;
        this.wins = wins;
        this.driver = driver;
        this.constructor = constructor;
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

    public int getWins () {
        return wins;
    }

    public void setWins (int wins) {
        this.wins = wins;
    }

    public Driver getDriver () {
        return driver;
    }

    public void setDriver (Driver driver) {
        this.driver = driver;
    }

    public Constructor getConstructor () {
        return constructor;
    }

    public void setConstructor (Constructor constructor) {
        this.constructor = constructor;
    }
}
