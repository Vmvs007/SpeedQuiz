package pt.ipp.estg.speedquiz.Services;

import java.util.List;

import pt.ipp.estg.speedquiz.Models.CircuitsApi.Circuit;
import pt.ipp.estg.speedquiz.Models.ConstructorsApi.Constructor;

import pt.ipp.estg.speedquiz.Models.DriversApi.DriverInfo;
import pt.ipp.estg.speedquiz.Models.DriversApi.DriverTable;
import pt.ipp.estg.speedquiz.Models.Results;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("{year}/drivers.json")
    Call<List<DriverTable>> getDriversByYear(
            @Path("year") int year,
            @Query("driverid") int name,
            @Query("round") int round);

    @GET("{year}/{round}/drivers.json")
    Call<List<DriverTable>> getDriversByYearAndRound(
            @Path("year") int year,
            @Path("round") int round);

    @GET("{year}/constructors/{constructorid}/drivers.json")
    Call<List<DriverTable>> getDriversByYearAndConstructor(
            @Path("year") int year,
            @Path("constructorid") String name);

    @GET("{year}/constructors.json")
    Call<List<Constructor>> getConstructorsByYearAndRound(
            @Query("year") int year,
            @Query("round") int round);

    @GET("constructors/{constructorid}.json")
    Call<Constructor> getConstructorById(
            @Path("constructorid") String name);

    @GET("driverStandings.json")
    Call<List<Results>> getResultsByYear(@Query("season") int year);

    @GET("circuits.json")
    Call<List<Circuit>> getCircuits();


    @GET("drivers/{driverid}.json")
    Call<DriverInfo> getDriverById(
            @Path("driverid") String name);




}
