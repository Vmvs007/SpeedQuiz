package pt.ipp.estg.speedquiz;

import java.util.List;

public class DaysWeather {
    private List<WeatherTemps> list;

    public DaysWeather(List<WeatherTemps> list) {
        this.list = list;
    }

    public List<WeatherTemps> getList() {
        return list;
    }

    public void setList(List<WeatherTemps> list) {
        this.list = list;
    }
}
