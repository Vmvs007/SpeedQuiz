package pt.ipp.estg.speedquiz.Services;

import java.util.List;

import pt.ipp.estg.speedquiz.DaysWeather;
import pt.ipp.estg.speedquiz.Models.CircuitsApi.CircuitList;
import pt.ipp.estg.speedquiz.Models.ConstructorsApi.Constructor;
import pt.ipp.estg.speedquiz.Models.DriversApi.DriverInfo;
import pt.ipp.estg.speedquiz.Models.DriversApi.DriverTable;
import pt.ipp.estg.speedquiz.Models.Results.ResultsList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("{year}/drivers.json")
    Call<DriverInfo> getDriversByYear(
            @Path("year") int year);

    @GET("{year}/{round}/drivers.json")
    Call<DriverInfo> getDriversByYearAndRound(
            @Path("year") int year,
            @Path("round") int round);

    @GET("{year}/constructors/{constructorid}/drivers.json")
    Call<List<DriverTable>> getDriversByYearAndConstructor(
            @Path("year") int year,
            @Path("constructorid") String name);

    @GET("{year}/constructors.json")
    Call<CircuitList> getConstructorsByYearAndRound(
            @Query("year") int year,
            @Query("round") int round);

    @GET("constructors/{constructorid}.json")
    Call<Constructor> getConstructorById(
            @Path("constructorid") String name);

    @GET("{season}/results.json")
    Call<ResultsList> getResultsByYear(@Path("season") int year);

    @GET("circuits.json")
    Call<CircuitList> getCircuits();


    @GET("drivers/{driverid}.json")
    Call<DriverInfo> getDriverById(
            @Path("driverid") String name);


    @GET("forecast")
    Call<DaysWeather> getWeather(
            @Query("lat") float lat,
            @Query("lon") float lon,
            @Query("appid") String type
    );

}
