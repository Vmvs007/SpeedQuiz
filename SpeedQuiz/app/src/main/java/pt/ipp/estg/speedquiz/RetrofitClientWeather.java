package pt.ipp.estg.speedquiz;

import pt.ipp.estg.speedquiz.Services.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientWeather {
    private static final String BASE_URL="https://api.openweathermap.org/data/2.5/";
    private static RetrofitClientWeather mInstance;
    private Retrofit retrofit;

    public RetrofitClientWeather() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClientWeather getInstance(){
        if(mInstance==null){
            mInstance= new RetrofitClientWeather();
        }
        return mInstance;
    }

    public Service getApi(){
        return retrofit.create(Service.class);
    }
}
