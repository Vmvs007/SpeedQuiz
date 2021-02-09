package pt.ipp.estg.speedquiz.Database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pt.ipp.estg.speedquiz.DAO.QuestionDAO;
import pt.ipp.estg.speedquiz.Models.QuestionModel;
import pt.ipp.estg.speedquiz.Models.Ranking;
import pt.ipp.estg.speedquiz.Models.UserAnswer;

@Database(entities = {QuestionModel.class},
        version = 1, exportSchema = false)

public abstract class SpeedQuizDB extends RoomDatabase {


    private static final String DB_NAME= "SpeedQuiz_Database.db";
    private static SpeedQuizDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public abstract QuestionDAO questionDAO();

    private static final Object sLock = new Object();

    @VisibleForTesting
     static final Migration MIGRATION_1_2 = new Migration(1,2){
         @Override
         public void migrate (@NonNull SupportSQLiteDatabase database) {

         }
     };

    public static SpeedQuizDB getInstance(Context context) {
        synchronized (sLock){
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SpeedQuizDB.class, "SpeedQuiz_Database.db").addMigrations(MIGRATION_1_2).build();
            }

            return INSTANCE;
        }
    }



    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onCreate(db);

                }
            };



}
