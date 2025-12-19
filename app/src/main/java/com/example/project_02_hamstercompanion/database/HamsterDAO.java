package com.example.project_02_hamstercompanion.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.project_02_hamstercompanion.database.entities.Hamster;

import java.util.List;

@Dao
public interface HamsterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Hamster hamster);

    @Query("SELECT * FROM "+ HamsterDatabase.HAMSTER_TABLE + " ORDER BY hamsterId DESC")
    List<Hamster> getAllHamsters();

    @Query("SELECT * FROM " + HamsterDatabase.HAMSTER_TABLE + " WHERE userId = :loggedInUserId ORDER BY hamsterId DESC")
    List<Hamster> getHamstersOfUser(int loggedInUserId);

    @Query("SELECT * FROM " + HamsterDatabase.HAMSTER_TABLE + " WHERE userId = :loggedInUserId ORDER BY hamsterId DESC")
    LiveData<List<Hamster>> getHamsterOfUserIdLiveData(int loggedInUserId);

    @Query("SELECT * FROM " + HamsterDatabase.HAMSTER_TABLE + " WHERE adoptionDate IS NULL ORDER BY hamsterId DESC")
    List<Hamster> getHamstersForAdoption();
    @Query("SELECT * FROM " + HamsterDatabase.HAMSTER_TABLE + " WHERE adoptionDate IS NULL ORDER BY hamsterId DESC")
    LiveData<List<Hamster>> getHamstersForAdoptionLiveData();

    @Query("SELECT * FROM " + HamsterDatabase.HAMSTER_TABLE + " WHERE hamsterId = :hamsterId LIMIT 1")
    LiveData<Hamster> getHamsterById(int hamsterId);

    @androidx.room.Update
    void update(Hamster hamster);

}
