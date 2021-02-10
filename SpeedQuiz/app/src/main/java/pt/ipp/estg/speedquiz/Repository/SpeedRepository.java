package pt.ipp.estg.speedquiz.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import pt.ipp.estg.speedquiz.DAO.QuestionDAO;
import pt.ipp.estg.speedquiz.DAO.UtilizadorDao;
import pt.ipp.estg.speedquiz.Database.SpeedQuizDB;
import pt.ipp.estg.speedquiz.FireBaseAuth.Utilizador;
import pt.ipp.estg.speedquiz.Models.QuestionModel;

public class SpeedRepository {

    private LiveData<List<Utilizador>> utilizadorList;
    private UtilizadorDao uDao;
    private QuestionDAO questionDAO;
    private LiveData<List<QuestionModel>> qListLiveData;

        public SpeedRepository(Application application) {
        SpeedQuizDB db = SpeedQuizDB.getInstance(application);
        questionDAO = db.questionDAO();
        uDao=db.utilizadorDao();
        utilizadorList=uDao.getUtilizador();
        qListLiveData = questionDAO.getQuestions();
        //registoTomaList=registo_de_toma_dao.getRegistoToma();
       // utilizadorList=uDao.getUtilizador();

    }

    public LiveData<List<QuestionModel>> getqListLiveData () {
        return qListLiveData;
    }
    public LiveData<List<Utilizador>> getAllUtilizador() {
        return utilizadorList;
    }

    public void insertUtilizador(Utilizador util) {
        SpeedQuizDB.databaseWriteExecutor.execute(() -> {
            uDao.addUtilizador(util);
        });
    }

    public void deleteUtilizador(Utilizador util) {
        SpeedQuizDB.databaseWriteExecutor.execute(() -> {
            uDao.deleteUtilizador(util);
        });
    }

    public void updateUtilizador( Utilizador util) {
        SpeedQuizDB.databaseWriteExecutor.execute(() -> {
            uDao.updateUtilizador(util);
        });
    }
    public void insert(QuestionModel questionModel) {
        SpeedQuizDB.databaseWriteExecutor.execute(() -> {
            questionDAO.addQuestion(questionModel);
        });

    }



}
