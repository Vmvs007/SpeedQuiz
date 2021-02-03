package pt.ipp.estg.speedquiz.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class F1_Service {
    private static final String BASE_URL="http://ergast.com/api/f1/";
    private static F1_Service mInstance;
    private Retrofit retrofit;
    public F1_Service() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized F1_Service getInstance(){
        if(mInstance==null){
            mInstance= new F1_Service();
        }
        return mInstance;
    }

    public Service getApi(){
        return retrofit.create(Service.class);
    }


}
