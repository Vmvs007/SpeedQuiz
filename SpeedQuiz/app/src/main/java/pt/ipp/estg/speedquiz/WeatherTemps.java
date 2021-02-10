package pt.ipp.estg.speedquiz;

public class WeatherTemps {
    private Weather main;

    public WeatherTemps(Weather main) {
        this.main = main;
    }

    public Weather getMain() {
        return main;
    }

    public void setMain(Weather main) {
        this.main = main;
    }
}
