package pt.ipp.estg.speedquiz.Database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import pt.ipp.estg.speedquiz.DAO.QuestionDAO;
import pt.ipp.estg.speedquiz.DAO.UtilizadorDao;
import pt.ipp.estg.speedquiz.Models.QuestionModel;
import pt.ipp.estg.speedquiz.Models.Ranking;
import pt.ipp.estg.speedquiz.Models.UserAnswer;
import pt.ipp.estg.speedquiz.Utilizador;

@Database(entities = {QuestionModel.class, Utilizador.class},
        version = 1, exportSchema = false)
public abstract class SpeedQuizDB extends RoomDatabase {

    private static final String DB_NAME= "F1Database.db";
    private static SpeedQuizDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static void destroyInstance() {
        INSTANCE=null;
    }
    public abstract UtilizadorDao utilizadorDao();
    public abstract QuestionDAO questionDAO();

    private static final Object sLock = new Object();

    @VisibleForTesting
     static final Migration MIGRATION_1_2 = new Migration(1,2){
         @Override
         public void migrate (@NonNull SupportSQLiteDatabase database) {

         }
     };

    public static SpeedQuizDB getInstance(Context context) {
        if(INSTANCE==null){
            INSTANCE=Room.databaseBuilder(context.getApplicationContext(),
                    SpeedQuizDB.class,DB_NAME)
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();

        }
        return INSTANCE;
    }



    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onCreate(db);

                }
            };



}
