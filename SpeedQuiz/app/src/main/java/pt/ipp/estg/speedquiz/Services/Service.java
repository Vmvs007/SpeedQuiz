package pt.ipp.estg.speedquiz.Services;

import java.util.List;

import pt.ipp.estg.speedquiz.Models.Driver;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {
    @GET("{year}/drivers.json")
    Call<List<Driver>> getDriversByYear(@Query("year") int year);

}
