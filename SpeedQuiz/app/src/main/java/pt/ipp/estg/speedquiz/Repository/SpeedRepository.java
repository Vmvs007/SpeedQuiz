package pt.ipp.estg.speedquiz.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import pt.ipp.estg.speedquiz.DAO.QuestionDAO;
import pt.ipp.estg.speedquiz.Database.SpeedQuizDB;
import pt.ipp.estg.speedquiz.Models.QuestionModel;

public class SpeedRepository {

    private QuestionDAO questionDAO;
    private LiveData<List<QuestionModel>> qListLiveData;

        public SpeedRepository(Application application) {
        SpeedQuizDB db = SpeedQuizDB.getInstance(application);
        questionDAO = db.questionDAO();
//        registo_de_toma_dao=db.registo_de_toma_dao();
//        uDao=db.utilizadorDao();
        qListLiveData = questionDAO.getQuestions();
        //registoTomaList=registo_de_toma_dao.getRegistoToma();
       // utilizadorList=uDao.getUtilizador();

    }

    public LiveData<List<QuestionModel>> getqListLiveData () {
        return qListLiveData;
    }


    public void insert(QuestionModel questionModel) {
       questionDAO.addQuestion(questionModel);

    }



}
