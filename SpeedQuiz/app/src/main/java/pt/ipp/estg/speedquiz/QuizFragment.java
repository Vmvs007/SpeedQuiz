package pt.ipp.estg.speedquiz;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int score = 0;
    int correct = 0;
    int qid = 0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mContentView;
    private Chronometer chronometer;
    private RadioButton rda, rdb, rdc, rdd;
    private TextView txtQuestion, txtNumber, number;
    private FloatingActionButton fb;
    private QuestionModel currentQ;
    private List<QuestionModel> quesList;
    private String answQuizz = "";


    public QuizFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizFragment newInstance(String param1, String param2) {
        QuizFragment fragment = new QuizFragment();
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

        //  getDriversInfo();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContentView = inflater.inflate(R.layout.fragment_quiz, container, false);
        quesList = new ArrayList<QuestionModel>();
        chronometer = mContentView.findViewById(R.id.chronometer);
        txtQuestion = mContentView.findViewById(R.id.question);
        txtNumber = mContentView.findViewById(R.id.questNumber);
        number = mContentView.findViewById(R.id.number);
        rda = mContentView.findViewById(R.id.radio0);
        rdb = mContentView.findViewById(R.id.radio1);
        rdc = mContentView.findViewById(R.id.radio2);
        fb = mContentView.findViewById(R.id.floating_action_button);

        getDriversInfo();
        //getResultsInfo();


        chronometer.setCountDown(true);
        long dayInMilli = 30 * 1000;
        chronometer.setBase(SystemClock.elapsedRealtime() + dayInMilli);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometerChanged) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) > 0) {
                    chronometer.stop();
                } else {
                    chronometer = chronometerChanged;
                }
            }
        });


        return mContentView;
    }


//
//    private void fetchQuestions(){
//
//    }
//
//
//

    private void setQuestionView() {

        txtQuestion.setText(currentQ.getQuestion());
        rda.setText(currentQ.getOptA());
        rdb.setText(currentQ.getOptB());
        rdc.setText(currentQ.getOptC());
        qid++;
        number.setText(String.valueOf(qid));
        chronometer.start();


    }


    public void takeAction() {
        currentQ = quesList.get(qid);
//        txtQuestion=mContentView.findViewById(R.id.question);
//        rda= mContentView.findViewById(R.id.radio0);
//        rdb= mContentView.findViewById(R.id.radio1);
//        rdc= mContentView.findViewById(R.id.radio2);
//        fb= mContentView.findViewById(R.id.floating_action_button);
        setQuestionView();
        Log.d("current", currentQ.toString());

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rda.isChecked()) {
                    answQuizz = rda.getText().toString();
                    rda.setChecked(false);
                } else if (rdb.isChecked()) {
                    answQuizz = rdb.getText().toString();
                    rdb.setChecked(false);
                } else if (rdc.isChecked()) {
                    answQuizz = rdc.getText().toString();
                    rdc.setChecked(false);
                } else {
                    // Toast.makeText(Quiz.class,"Selecione uma opção antes de avançar!",Toast.LENGTH_LONG).show();
                }
                Log.d("yourans", currentQ.getAnswer() + " " + answQuizz);

                if (currentQ.getAnswer().equals(answQuizz)) {
                    correct++;
                    score += currentQ.getPoints();
                    Log.d("score", "Your score = " + score);
                }
                if (qid < quesList.size()) {
                    chronometer.setBase(SystemClock.elapsedRealtime() + 30 * 1000);
                    currentQ = quesList.get(qid);
                    setQuestionView();
                } else {
                    chronometer.stop();
                    Bundle args = new Bundle();
                    args.putString("points", String.valueOf(score));
                    args.putString("correct", String.valueOf(correct));
                    ResultFragment resultFragment = new ResultFragment();
                    resultFragment.setArguments(args);

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, resultFragment)
                            .commit();
                }
            }
        });
    }

    public void getDriversInfo() {
        Random r = new Random();
        int ano = r.nextInt(2021 - 2017) + 2017;
        Call<DriverInfo> call = F1_Service
                .getInstance()
                .getApi()
                .getDriversByYear(ano);

        call.enqueue(new Callback<DriverInfo>() {
            @Override
            public void onResponse(Call<DriverInfo> call, Response<DriverInfo> response) {
                if (response.isSuccessful()) {
                    if (response != null) {
                        // QuestionModel questionModel= new QuestionModel();
                        Log.d("Resposta", String.valueOf(response.body().getmRData().getDriverTable().getDrivers().get(0).getUrl()));
                        List<Driver> driverList = response.body().getmRData().getDriverTable().getDrivers();
                        // random_int = (int)(Math.random() * (driverList.size() - 0 + 1) + 0);

                        for (int i = 0; i < 3; i++) {
                            Random r = new Random();
                            int posicao = r.nextInt(response.body().getmRData().getDriverTable().getDrivers().size() - 3) + 3;
                            QuestionModel questionModel = new QuestionModel();
                            String quest1 = "Em que data nasceu " + response.body().getmRData().getDriverTable().getDrivers().get(posicao).getGivenName() + " " + response.body().getmRData().getDriverTable().getDrivers().get(posicao).getFamilyName() + "?";
                            String quest2 = "Qual é a nacionalidade do " + response.body().getmRData().getDriverTable().getDrivers().get(posicao).getGivenName() + " " + response.body().getmRData().getDriverTable().getDrivers().get(posicao).getFamilyName() + "?";
                            String quest3 = "Qual é o permanent number do " + response.body().getmRData().getDriverTable().getDrivers().get(posicao).getGivenName() + " " + response.body().getmRData().getDriverTable().getDrivers().get(posicao).getFamilyName() + "?";
                            if (i == 0) {
                                questionModel.setQuestion(quest1);
                                questionModel.setOptA(driverList.get(posicao).getDateOfBirth());
                                questionModel.setOptB(driverList.get(posicao - 1).getDateOfBirth());
                                questionModel.setOptC(driverList.get(posicao - 2).getDateOfBirth());
                                questionModel.setAnswer(driverList.get(posicao).getDateOfBirth());
                                questionModel.setPoints(1);
                            } else if (i == 1) {
                                questionModel.setQuestion(quest2);
                                questionModel.setOptA(driverList.get(posicao - 1).getNationality());
                                questionModel.setOptB(driverList.get(posicao).getNationality());
                                questionModel.setOptC(driverList.get(posicao - 2).getNationality());
                                questionModel.setAnswer(driverList.get(posicao).getNationality());
                                questionModel.setPoints(1);
                            } else if (i == 2) {
                                questionModel.setQuestion(quest3);
                                questionModel.setOptA(driverList.get(posicao - 1).getPermanentNumber());
                                questionModel.setOptB(driverList.get(posicao).getPermanentNumber());
                                questionModel.setOptC(driverList.get(posicao - 2).getPermanentNumber());
                                questionModel.setAnswer(driverList.get(posicao).getPermanentNumber());
                                questionModel.setPoints(1);
                            }


                            quesList.add(questionModel);

                        }


                        //Log.d("Model",driverList.get(0).getDateOfBirth());
                        Log.d("Resultado da QUestao :", quesList.toString());
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getResultsInfo();
                            }
                        }, 3000);

                        //sampleDatabase.questionDAO().addQuestion(questionModel);


                    }
                }
            }

            @Override
            public void onFailure(Call<DriverInfo> call, Throwable t) {

            }
        });
    }

    public void getResultsInfo() {
        Random r = new Random();
        int ano = r.nextInt(2021 - 2017) + 2017;
        Log.d("Ano", String.valueOf(ano));

        Call<ResultsList> call = F1_Service
                .getInstance()
                .getApi()
                .getResultsByYear(ano);

        call.enqueue(new Callback<ResultsList>() {
            @Override
            public void onResponse(Call<ResultsList> call, Response<ResultsList> response) {
                if (response.isSuccessful()) {
                    if (response != null) {
                        // QuestionModel questionModel= new QuestionModel();
                        //Log.d("Resposta", response.body().toString());
                        List<Race> driverList = response.body().getMRData().getRaceTable().getRaces();
                        // random_int = (int)(Math.random() * (driverList.size() - 0 + 1) + 0);

                        for (int i = 0; i < 3; i++) {
                            Random r = new Random();
                            int posicao = r.nextInt(response.body().getMRData().getRaceTable().getRaces().size() - 0) + 0;
                            int opcao = 0;
                            QuestionModel questionModel = new QuestionModel();
                            String quest1 = "Quem venceu o circuito " + response.body().getMRData().getRaceTable().getRaces().get(posicao).getSeason() + " " + response.body().getMRData().getRaceTable().getRaces().get(posicao).getCircuit().getCircuitName() + "?";
                            String quest2 = "Onde decorre o circuito " + response.body().getMRData().getRaceTable().getRaces().get(posicao).getSeason() + " " + response.body().getMRData().getRaceTable().getRaces().get(posicao).getCircuit().getCircuitName() + "?";
                            String quest3 = "Em que data se realizou o circuito " + response.body().getMRData().getRaceTable().getRaces().get(posicao).getSeason() + " " + response.body().getMRData().getRaceTable().getRaces().get(posicao).getCircuit().getCircuitName() + "?";
                            if (i == 0) {
                                questionModel.setQuestion(quest1);
                                questionModel.setOptA(driverList.get(posicao).getResults().get(5).getDriver().getGivenName() + " " + driverList.get(posicao).getResults().get(5).getDriver().getFamilyName());

                                opcao = r.nextInt(response.body().getMRData().getRaceTable().getRaces().size() - 0) + 0;
                                questionModel.setOptB(driverList.get(opcao).getResults().get(0).getDriver().getGivenName() + " " + driverList.get(posicao).getResults().get(0).getDriver().getFamilyName());

                                opcao = r.nextInt(response.body().getMRData().getRaceTable().getRaces().size() - 0) + 0;
                                questionModel.setOptC(driverList.get(opcao).getResults().get(3).getDriver().getGivenName() + " " + driverList.get(posicao).getResults().get(3).getDriver().getFamilyName());

                                questionModel.setAnswer(driverList.get(posicao).getResults().get(0).getDriver().getGivenName() + " " + driverList.get(posicao).getResults().get(0).getDriver().getFamilyName());
                                questionModel.setPoints(2);
                            } else if (i == 1) {
                                questionModel.setQuestion(quest2);
                                opcao = r.nextInt(response.body().getMRData().getRaceTable().getRaces().size() - 0) + 0;
                                questionModel.setOptA(driverList.get(opcao).getCircuit().getLocation().getCountry());

                                questionModel.setOptB(driverList.get(posicao).getCircuit().getLocation().getCountry());

                                opcao = r.nextInt(response.body().getMRData().getRaceTable().getRaces().size() - 0) + 0;
                                questionModel.setOptC(driverList.get(opcao).getCircuit().getLocation().getCountry());

                                questionModel.setAnswer(driverList.get(posicao).getCircuit().getLocation().getCountry());
                                questionModel.setPoints(3);
                            } else if (i == 2) {
                                questionModel.setQuestion(quest3);
                                opcao = r.nextInt(response.body().getMRData().getRaceTable().getRaces().size() - 0) + 0;
                                questionModel.setOptA(driverList.get(opcao).getDate());

                                opcao = r.nextInt(response.body().getMRData().getRaceTable().getRaces().size() - 0) + 0;
                                questionModel.setOptB(driverList.get(opcao).getDate());

                                questionModel.setOptC(driverList.get(posicao).getDate());
                                questionModel.setAnswer(driverList.get(posicao).getDate());
                                questionModel.setPoints(1);
                            }


                            quesList.add(questionModel);

                        }
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                takeAction();

                            }
                        }, 3000);

                        //Log.d("Model",driverList.get(0).getDateOfBirth());
                        //  Log.d("Resultado da QUestao",quesList.toString());
                        // takeAction();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultsList> call, Throwable t) {

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // getDriversInfo();

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // getDriversInfo();
    }
}