package com.example.project_02_hamstercompanion.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.project_02_hamstercompanion.database.entities.User;

public class HamsterDatabase {

    @Database(
            entities = { User.class, Hamster.class, CareLog.class },
            version = 1
    )
    public abstract class HamsterDatabase extends RoomDatabase {

        public abstract UserDao userDao();
        public abstract HamsterDao hamsterDao();
        public abstract CareLogDao careLogDao();
    }
}
