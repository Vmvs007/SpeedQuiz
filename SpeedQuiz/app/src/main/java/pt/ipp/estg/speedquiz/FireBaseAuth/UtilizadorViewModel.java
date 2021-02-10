package pt.ipp.estg.speedquiz.FireBaseAuth;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import pt.ipp.estg.speedquiz.Database.SpeedQuizDB;
import pt.ipp.estg.speedquiz.Repository.SpeedRepository;

public class UtilizadorViewModel  extends AndroidViewModel {

    private SpeedQuizDB f1Database;

    private SpeedRepository f1Repository;

    private LiveData<List<Utilizador>> utilizadorList;

    public UtilizadorViewModel(@NonNull Application application) {
        super(application);

        f1Database = SpeedQuizDB.getInstance(this.getApplication());
        f1Repository =new SpeedRepository(application);

        utilizadorList= f1Database.utilizadorDao().getUtilizador();
    }

    public LiveData<List<Utilizador>> getUtilizadorList() {

        return utilizadorList;
    }

    public SpeedQuizDB getF1Database() {

        return f1Database;
    }

    public void insert(Utilizador utilizador) { f1Repository.insertUtilizador(utilizador); }

    public void update(Utilizador utilizador) { f1Repository.updateUtilizador(utilizador); }

    public void delete(Utilizador utilizador) { f1Repository.deleteUtilizador(utilizador); }
}
