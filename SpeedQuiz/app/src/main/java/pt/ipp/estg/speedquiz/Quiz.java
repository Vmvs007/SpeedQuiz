package pt.ipp.estg.speedquiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pt.ipp.estg.speedquiz.DAO.QuestionDAO;
import pt.ipp.estg.speedquiz.Database.SpeedQuizDB;
import pt.ipp.estg.speedquiz.Models.DriversApi.Driver;
import pt.ipp.estg.speedquiz.Models.DriversApi.DriverInfo;
import pt.ipp.estg.speedquiz.Models.QuestionModel;
import pt.ipp.estg.speedquiz.Repository.SpeedRepository;
import pt.ipp.estg.speedquiz.Services.F1_Service;
import pt.ipp.estg.speedquiz.ViewModel.QuizViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Quiz extends AppCompatActivity {


    Toolbar topAppBar;

    QuizFragment fragment;

    public static final int NUMBER_OF_THREADS = 2;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        topAppBar= (Toolbar)findViewById(R.id.toolbarQuiz);
       // setSupportActionBar(topAppBar);


       // quesList= new ArrayList<QuestionModel>();
       // sampleDatabase = Room.databaseBuilder(getApplicationContext(), SpeedQuizDB.class, "SpeedQuiz_Database.db").build();



        fragment=new QuizFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_view_tag, fragment)
                .commit();

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                    Intent intent = new Intent(Quiz.this, MainActivity.class);
                    startActivity(intent);
            }
        });

        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick (MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        return true;

                    case R.id.more:
                        return true;

                    default:
                        return false;
                }
            }
        });
    }



}