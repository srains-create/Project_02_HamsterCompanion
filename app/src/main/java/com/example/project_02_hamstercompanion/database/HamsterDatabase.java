package com.example.project_02_hamstercompanion.database;
import android.content.Context;

import androidx.lifecycle.LiveData;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.Room;



import com.example.project_02_hamstercompanion.database.entities.Hamster;
import com.example.project_02_hamstercompanion.database.entities.User;
import com.example.project_02_hamstercompanion.database.entities.CareLog;
import com.example.project_02_hamstercompanion.database.typeConverters.LocalDataTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = { User.class, Hamster.class, CareLog.class }, version = 1, exportSchema = false)
@TypeConverters({LocalDataTypeConverter.class})
public abstract class HamsterDatabase extends RoomDatabase {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String HAMSTER_TABLE = "HAMSTER_TABLE";
    public static final String CARE_LOG_TABLE = "care_log";


    public abstract UserDAO userDao();
    public abstract HamsterDAO hamsterDao();
    public abstract CareLogDao careLogDao();
    private static volatile HamsterDatabase INSTANCE;

    //database writes
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    //singleton
    public static HamsterDatabase getDatabase(final Context context) {
        if (INSTANCE != null) {
            return INSTANCE;
        }

        synchronized (HamsterDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), HamsterDatabase.class, "HamsterDatabase.db").fallbackToDestructiveMigration().build();
            }
        }
        return INSTANCE;
    }

}