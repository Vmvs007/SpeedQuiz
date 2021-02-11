package pt.ipp.estg.speedquiz.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pt.ipp.estg.speedquiz.Models.UserAnswer;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserAnswerDAO {


    @Query("SELECT * FROM UserAnswer")
    LiveData<List<UserAnswer>> getUsersAnswers();


    @Query("SELECT * FROM UserAnswer WHERE username LIKE :name ")
    LiveData<List<UserAnswer>> findUserWithName(String name);

    @Insert(onConflict = REPLACE)
    void addUserAnswer(UserAnswer userAnswer);

    @Delete
    void deleteUserAnswer(UserAnswer userAnswer);

    @Update
    void updateUserAnswer(UserAnswer userAnswer);
}

