package pt.ipp.estg.speedquiz.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

import pt.ipp.estg.speedquiz.FireBaseAuth.Utilizador;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UtilizadorDao {

    @Query("SELECT * FROM Utilizador")
    LiveData<List<Utilizador>> getUtilizador();

    @Insert(onConflict = REPLACE)
    void addUtilizador(Utilizador utilizador);

    @Delete
    void deleteUtilizador(Utilizador utilizador);

    @Update
    void updateUtilizador (Utilizador utilizador);
}
