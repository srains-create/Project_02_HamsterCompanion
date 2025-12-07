package com.example.project_02_hamstercompanion.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CareLogDao {

    @Insert
    long insert(CareLog careLog);

    // Get all care log entries for one hamster, newest first.
    @Query("SELECT * FROM care_log WHERE hamster_id = :hamsterId ORDER BY timestamp DESC")
    List<CareLog> getLogsForHamster(int hamsterId);

    @Query("SELECT * FROM care_log")
    List<CareLog> getAllLogs();

    @Query("DELETE FROM care_log")
    void deleteAll();
}
