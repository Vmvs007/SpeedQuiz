package pt.ipp.estg.speedquiz;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Quiz extends AppCompatActivity {


    public static final int NUMBER_OF_THREADS = 2;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    Toolbar topAppBar;
    QuizFragment fragment;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
//        topAppBar= (Toolbar)findViewById(R.id.toolbarQuiz);
        // setSupportActionBar(topAppBar);


        // quesList= new ArrayList<QuestionModel>();
        // sampleDatabase = Room.databaseBuilder(getApplicationContext(), SpeedQuizDB.class, "SpeedQuiz_Database.db").build();


        fragment = new QuizFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_view_tag, fragment)
                .commit();

//        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View view) {
//                    Intent intent = new Intent(Quiz.this, MainActivity.class);
//                    startActivity(intent);
//            }
//        });


    }


}