package com.example.project_02_hamstercompanion.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project_02_hamstercompanion.database.entities.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM " + HamsterDatabase.USER_TABLE)
    void deleteAll();

    @Query("SELECT * FROM " + HamsterDatabase.USER_TABLE + " ORDER BY userName")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM " + HamsterDatabase.USER_TABLE + " WHERE username == :username")
    LiveData<User> getUserByUsername(String username);

    @Query("SELECT * FROM " + HamsterDatabase.USER_TABLE + " WHERE userId == :userId")
    LiveData<User> getUserByUserId(int userId);

}
