package pt.ipp.estg.speedquiz.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pt.ipp.estg.speedquiz.DAO.QuestionDAO;
import pt.ipp.estg.speedquiz.Models.QuestionModel;
import pt.ipp.estg.speedquiz.Repository.SpeedRepository;

public class QuizViewModel extends AndroidViewModel {

    private SpeedRepository speedRepository;

    private LiveData<List<QuestionModel>> listLiveData;

    private QuestionDAO questionDAO;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        //questionDAO= SpeedQuizDB.getInstance(application).questionDAO();
        speedRepository = new SpeedRepository(application);
        listLiveData = speedRepository.getqListLiveData();

    }


    public LiveData<List<QuestionModel>> getQuestList() {

        return listLiveData;
    }


    public void insert(QuestionModel questionModel) {
        speedRepository.insert(questionModel);
    }
}
