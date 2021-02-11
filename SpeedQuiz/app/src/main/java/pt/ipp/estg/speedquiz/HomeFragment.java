package pt.ipp.estg.speedquiz;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static int correct = 0;
    public static int wrong = 0;

    SharedPreferences mUser;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private PieChart pieChart;
    private View mContentView;
    private Date currentTime;
    private Location currentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mContext = getActivity();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mContentView = inflater.inflate(R.layout.home, container, false);

        pieChart = mContentView.findViewById(R.id.chart);
        generatePie(correct, wrong);

        mUser = PreferenceManager.getDefaultSharedPreferences(mContext);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mContext);
        fetchLastLocation();

        TextView nome = mContentView.findViewById(R.id.nome);
        TextView timeday = mContentView.findViewById(R.id.timeday);
        nome.setText(mUser.getString("name", ""));
        Button button = mContentView.findViewById(R.id.driversButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new RecyclerDrivers();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });
        currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat1 = new SimpleDateFormat("HH");
        Integer hora = Integer.parseInt(dateFormat1.format(currentTime));
        String tipodia = "Good Day,";

        if (hora > 6 && hora < 13) {
            tipodia = "Bom dia,";
        } else if (hora > 12 && hora < 20) {
            tipodia = "Boa tarde,";
        } else if ((hora > 19 && hora < 24) || (hora >= 0 && hora < 7)) {
            tipodia = "Boa noite,";
        }

        timeday.setText(tipodia);


        return mContentView;
    }

    public void getWeather() {
        Call<DaysWeather> call = RetrofitClientWeather
                .getInstance()
                .getApi()
                .getWeather((float) currentLocation.getLatitude(), (float) currentLocation.getLongitude(), "a5418b786e181faa123db1d224549134");
        call.enqueue(new Callback<DaysWeather>() {
            @Override
            public void onResponse(Call<DaysWeather> call, Response<DaysWeather> response) {
                Log.d("ccccc", response.toString());
                if (response.code() == 200) {
                    /*
                    DaysWeather dw=response.body();
                    List<WeatherTemps> weat=dw.getList();
                    LineChart chart =mContentView.findViewById(R.id.chart);

                    int a=0;
                    ArrayList<Entry> valorY= new ArrayList<>();
                    for (WeatherTemps wt : weat){
                        float dd=(float)(wt.getMain().getTemp() - 273.15);
                        Log.d("cccc", String.valueOf(dd));
                        valorY.add(new Entry(a, dd));
                        a++;


                    }

                    LineDataSet set1= new LineDataSet(valorY,"Weather Temperature");
                    set1.setFillAlpha(110);
                    set1.setColor(Color.RED);
                    set1.setLineWidth(3f);
                    LineData lineData = new LineData(set1);
                    chart.setData(lineData);
                    chart.invalidate();

*/

                } else {
                    Toast.makeText(mContext, "Pedidos Tempo Wrong!", Toast.LENGTH_SHORT).show();
                    ;
                }
            }

            @Override
            public void onFailure(Call<DaysWeather> call, Throwable t) {
                Toast.makeText(mContext, "Pedidos Tempo Failed " + t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    getWeather();
                }
            }
        });
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String notif) {
        if (mListener != null) {
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {/*
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void generatePie(float correct, float wrong) {

        ArrayList<PieEntry> answers = new ArrayList<>();
        answers.add(new PieEntry(correct, "Corretas"));
        answers.add(new PieEntry(wrong, "Erradas"));

        PieDataSet pieDataSet = new PieDataSet(answers, "");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(12f);

        ValueFormatter vf = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "" + (int) value;
            }
        };

        pieDataSet.setValueFormatter(vf);
        PieData pieData = new PieData(pieDataSet);

        pieChart.setUsePercentValues(false);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Respostas");
        pieChart.setCenterTextSize(12f);
        pieChart.setRotationEnabled(false);
        pieChart.setTouchEnabled(false);

        pieChart.invalidate();
    }

    public interface OnFragmentInteractionListener {
        void onButtonclick(String notif);
    }

}
