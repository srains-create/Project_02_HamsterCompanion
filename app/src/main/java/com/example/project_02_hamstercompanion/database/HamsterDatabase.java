package com.example.project_02_hamstercompanion.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.project_02_hamstercompanion.database.entities.CareLog;
import com.example.project_02_hamstercompanion.database.entities.Hamster;
import com.example.project_02_hamstercompanion.database.entities.User;
import com.example.project_02_hamstercompanion.database.typeConverters.LocalDataTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {User.class, Hamster.class, CareLog.class},
        version = 1,
        exportSchema = false
)
@TypeConverters({LocalDataTypeConverter.class})
public abstract class HamsterDatabase extends RoomDatabase {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String HAMSTER_TABLE = "HAMSTER_TABLE";
    public static final String CARE_LOG_TABLE = "care_log";

    public abstract UserDAO userDao();
    public abstract HamsterDAO hamsterDao();
    public abstract CareLogDao careLogDao();

    private static volatile HamsterDatabase INSTANCE;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    private static final RoomDatabase.Callback roomCallback = //Jael added 12/15/25 admin callback
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    databaseWriteExecutor.execute(() -> {
                        //adding default users
                        UserDAO dao = INSTANCE.userDao();

                        User admin = new User(
                                "admin1",
                                "admin1",
                                true
                        );
                        User user1 = new User(
                                "user1",
                                "user1",
                                false
                        );
                        dao.insert(admin);
                        dao.insert(user1);


                        //adding default hamsters
                        HamsterDAO hamsterDAO = INSTANCE.hamsterDao();
                        User owner = dao.getUserByCredentials("user1","user1");
                        //owned hamsters
                        Hamster h1 = new Hamster(
                                owner.getUserId(),
                                "Hamster1",
                                50,
                                40,
                                30);
                        //unowned hamsters
                        Hamster h2 = new Hamster(
                                null,
                                "Bob",
                                5,
                                4,
                                3);
                        Hamster h3 = new Hamster(
                                null,
                                "Celery",
                                10,
                                3,
                                45);
                        Hamster h4 = new Hamster(
                                null,
                                "Darth Vader",
                                1,
                                2,
                                3);
                        hamsterDAO.insert(h1);
                        hamsterDAO.insert(h2);
                        hamsterDAO.insert(h3);
                        hamsterDAO.insert(h4);

                    });
                }
            };


    public static HamsterDatabase getDatabase(final Context context) {
        if (INSTANCE != null) {
            return INSTANCE;
        }

        synchronized (HamsterDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                                context.getApplicationContext(), HamsterDatabase.class, "HamsterDatabase.db").addCallback(roomCallback).fallbackToDestructiveMigration().build();
            }
        }
        return INSTANCE;
    }
}
