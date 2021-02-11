package pt.ipp.estg.speedquiz;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.util.List;

import pt.ipp.estg.speedquiz.Database.SpeedQuizDB;
import pt.ipp.estg.speedquiz.Models.DriversApi.Driver;
import pt.ipp.estg.speedquiz.Models.DriversApi.DriverInfo;
import pt.ipp.estg.speedquiz.Models.QuestionModel;
import pt.ipp.estg.speedquiz.Services.F1_Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizAsyncTask extends AsyncTask<Void, Void, Void> {

    SpeedQuizDB sampleDatabase;
    private List<QuestionModel> mTextView;

    public QuizAsyncTask(List<QuestionModel> mTextView) {
        this.mTextView = mTextView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


//    @Override
//    protected void onPostExecute(QuestionModel result) {
//        mTextView.add(result);
//    }

    @Override
    protected Void doInBackground(Void... voids) {
        Call<DriverInfo> call = F1_Service
                .getInstance()
                .getApi()
                .getDriversByYear(2020);

        call.enqueue(new Callback<DriverInfo>() {
            @Override
            public void onResponse(Call<DriverInfo> call, Response<DriverInfo> response) {
                if (response.isSuccessful()) {
                    if (response != null) {
                        QuestionModel questionModel = new QuestionModel();
                        Log.d("Resposta", String.valueOf(response.body().getmRData().getDriverTable().getDrivers().get(0).getUrl()));
                        List<Driver> driverList = response.body().getmRData().getDriverTable().getDrivers();
                        Log.d("Resposta", String.valueOf(driverList.size()));
                        // random_int = (int)(Math.random() * (driverList.size() - 0 + 1) + 0);
                        String quest = "Em que data nasceu " + response.body().getmRData().getDriverTable().getDrivers().get(0).getGivenName() + " " + response.body().getmRData().getDriverTable().getDrivers().get(0).getFamilyName() + "?";
                        Log.d("Pergunta", quest);

                        questionModel.setQuestion(quest);
                        questionModel.setOptA(driverList.get(0).getDateOfBirth());
                        questionModel.setOptB(driverList.get(0).getDateOfBirth());
                        questionModel.setOptC(driverList.get(0).getDateOfBirth());
                        questionModel.setAnswer(driverList.get(0).getDateOfBirth());
                        questionModel.setPoints(0);

                        //Log.d("Model",driverList.get(0).getDateOfBirth());
                        Log.d("Resultado da QUestao", questionModel.toString());
                        mTextView.add(questionModel);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                sampleDatabase.questionDAO().addQuestion(questionModel);
                            }
                        }, 3000);

                    }
                }
            }

            @Override
            public void onFailure(Call<DriverInfo> call, Throwable t) {

            }
        });

        return null;
    }
}
