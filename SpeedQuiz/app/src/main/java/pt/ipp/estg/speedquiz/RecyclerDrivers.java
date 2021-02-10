package pt.ipp.estg.speedquiz;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pt.ipp.estg.speedquiz.Adapters.DriversAdapter;
import pt.ipp.estg.speedquiz.Models.DriversApi.Driver;
import pt.ipp.estg.speedquiz.Models.DriversApi.DriverInfo;
import pt.ipp.estg.speedquiz.Models.QuestionModel;
import pt.ipp.estg.speedquiz.Models.Results.Race;
import pt.ipp.estg.speedquiz.Models.Results.ResultsList;
import pt.ipp.estg.speedquiz.Services.F1_Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerDrivers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerDrivers extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public List<Driver> drivers;
    private DriversAdapter adapter;
    private Context context;
    private RecyclerView recyclerView;


    public RecyclerDrivers () {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerDrivers.
     */
    // TODO: Rename and change types and number of parameters


    public static RecyclerDrivers newInstance (String param1, String param2) {
        RecyclerDrivers fragment = new RecyclerDrivers();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mContentView=inflater.inflate(R.layout.fragment_recycler_drivers, container, false);
        recyclerView = mContentView.findViewById(R.id.recyclerDrivers);
         drivers= new ArrayList<Driver>();
        getDriversInfo();
        adapter = new DriversAdapter(context, drivers);


        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        return mContentView ;
    }


public  void setView(){
    Log.d("Metodo","setView");


}

    public void getDriversInfo(){

        Log.d("Metodo","driverInfo");
        Call<DriverInfo> call = F1_Service
                .getInstance()
                .getApi()
                .getDriversByYear(2020);

        call.enqueue(new Callback<DriverInfo>() {
            @Override
            public void onResponse(Call<DriverInfo> call, Response<DriverInfo> response) {
                if (response.isSuccessful()){
                    if (response != null){
                        // QuestionModel questionModel= new QuestionModel();
                        Log.d("Resposta", response.body().getmRData().toString());
                        List<Driver> driverList = response.body().getmRData().getDriverTable().getDrivers();
                        // random_int = (int)(Math.random() * (driverList.size() - 0 + 1) + 0);
                        Log.d("Tamanho lista",String.valueOf(driverList.size()));
                        Driver driver= new Driver();

                        for(int i=0; i<driverList.size();i++){
                            driver=driverList.get(i);
                            driverList.add(driver);

                        }

                        Log.d("Lista",driverList.toString());
//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        }, 3000);
//

                    }
                }
            }

            @Override
            public void onFailure(Call<DriverInfo> call, Throwable t) {

            }
        });



    }
}