package pt.ipp.estg.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pt.ipp.estg.speedquiz.Models.DriversApi.Driver;
import pt.ipp.estg.speedquiz.Models.DriversApi.DriverInfo;
import pt.ipp.estg.speedquiz.Services.F1_Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
public DriverInfo drivers;
   public TextView nome, bv;
    private Driver driver;
    private List<Driver> driverList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome=findViewById(R.id.textoInicial);
        bv= findViewById(R.id.textView);

    }

    @Override
    protected void onStart () {
        super.onStart();
        getDriver("alonso");

    }

    public void getDriver(String driverId){
        Call<DriverInfo> call = F1_Service
                .getInstance()
                .getApi()
                .getDriverById(driverId);
        call.enqueue(new Callback<DriverInfo>() {
            @Override
            public void onResponse(Call<DriverInfo >call, Response<DriverInfo> response) {
                Log.d("ccccc",response.body().getmRData().getDriverTable().getDrivers().get(0).getGivenName());
                if(response.code()==200){
                    drivers =response.body();

                    nome.setText(drivers.getmRData().getDriverTable().getDrivers().get(0).getGivenName()+ " " + drivers.getmRData().getDriverTable().getDrivers().get(0).getFamilyName());

                   // Log.d("Nome",drivers.getmRData().toString());

                }else{
                    Toast.makeText(MainActivity.this, "Pedidos Ingredient Wrong!", Toast.LENGTH_SHORT).show();;
                }
            }

            @Override
            public void onFailure(Call<DriverInfo> call, Throwable t) {
                Log.d("cccc", t.getMessage());
                Toast.makeText(MainActivity.this, "Pedidos Ingredient Failed "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}