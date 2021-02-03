package pt.ipp.estg.speedquiz.Services;

import java.util.List;

import pt.ipp.estg.speedquiz.Models.Constructor;
import pt.ipp.estg.speedquiz.Models.Driver;
import pt.ipp.estg.speedquiz.Models.Results;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {
    @GET("drivers.json")
    Call<List<Driver>> getDriversByYear(@Query("year") int year, @Query("driverid") int name,@Query("round") int round);

    @GET("constructors.json")
    Call<List<Constructor>> getConstructorsByYear(@Query("year") int year,@Query("round") int round, @Query("constructorid") String name);

    @GET("driverStandings.json")
    Call<List<Results>> getResultsByYear(@Query("season") int year);

}
