package pt.ipp.estg.speedquiz.Database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pt.ipp.estg.speedquiz.DAO.QuestionDAO;
import pt.ipp.estg.speedquiz.DAO.UtilizadorDao;
import pt.ipp.estg.speedquiz.FireBaseAuth.Utilizador;
import pt.ipp.estg.speedquiz.Models.QuestionModel;

@Database(entities = {QuestionModel.class, Utilizador.class},
        version = 1, exportSchema = false)
public abstract class SpeedQuizDB extends RoomDatabase {

    @VisibleForTesting
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
    private static final String DB_NAME = "F1Database.db";
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static final Object sLock = new Object();
    private static SpeedQuizDB INSTANCE;
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                }
            };

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public static SpeedQuizDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    SpeedQuizDB.class, DB_NAME)
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();

        }
        return INSTANCE;
    }

    public abstract UtilizadorDao utilizadorDao();

    public abstract QuestionDAO questionDAO();


}
