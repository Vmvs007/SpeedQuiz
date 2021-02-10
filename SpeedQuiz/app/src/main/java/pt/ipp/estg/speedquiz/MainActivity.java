package pt.ipp.estg.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button= (Button)findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent1 = new Intent(getApplicationContext(), FireBaseAuth.class);
                startActivity(intent1);
            }
        });

    }

    @Override
    protected void onStart () {
        super.onStart();
       // getDriver("alonso");

    }


}