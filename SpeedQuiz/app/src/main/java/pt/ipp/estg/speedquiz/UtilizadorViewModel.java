package pt.ipp.estg.speedquiz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pt.ipp.estg.speedquiz.Database.SpeedQuizDB;
import pt.ipp.estg.speedquiz.Repository.SpeedRepository;

public class UtilizadorViewModel extends AndroidViewModel {

    private SpeedQuizDB speedQuizDB;

    private SpeedRepository speedRepository;

    private LiveData<List<Utilizador>> utilizadorList;

    public UtilizadorViewModel(@NonNull Application application) {
        super(application);

        speedQuizDB= speedQuizDB.getInstance(this.getApplication());
        speedRepository =new SpeedRepository(application);

        utilizadorList=speedQuizDB.utilizadorDao().getUtilizador();
    }

    public LiveData<List<Utilizador>> getUtilizadorList() {

        return utilizadorList;
    }

    public SpeedQuizDB getSpeedQuizDB() {

        return speedQuizDB;
    }

    public void insert(Utilizador utilizador) { speedRepository.insertUtilizador(utilizador); }

    public void update(Utilizador utilizador) { speedRepository.updateUtilizador(utilizador); }

    public void delete(Utilizador utilizador) { speedRepository.deleteUtilizador(utilizador); }
}