package pt.ipp.estg.speedquiz.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pt.ipp.estg.speedquiz.Models.QuestionModel;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface QuestionDAO {


    @Query("SELECT * FROM questions_table ORDER BY id ASC")
    public  LiveData<List<QuestionModel>> getQuestions () ;

    @Insert(onConflict = REPLACE)
    public void addQuestion (QuestionModel questionModel);

    @Delete
    public  void deleteQuestion(QuestionModel questionModel);

    @Update
    public  void updateQuestion(QuestionModel questionModel);
}
