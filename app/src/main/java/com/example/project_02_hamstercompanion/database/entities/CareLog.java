package com.example.project_02_hamstercompanion.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project_02_hamstercompanion.database.HamsterDatabase;

// Each row is one action done to a hamster: feed, clean, play, rest, etc.
@Entity(tableName = HamsterDatabase.CARE_LOG_TABLE)
public class CareLog {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    // Foreign key to the Hamster table (adjust the type/name if your Hamster PK is different)
    @ColumnInfo(name = "hamster_id")
    private int hamsterId;

    // Example values: "feed", "clean", "play", "rest"
    @NonNull
    @ColumnInfo(name = "action_type")
    private String actionType;

    // Time of the action in milliseconds since epoch (System.currentTimeMillis())
    @ColumnInfo(name = "timestamp")
    private long timestamp;

    // Optional notes, can be null
    @ColumnInfo(name = "notes")
    private String notes;

    public CareLog(int hamsterId, @NonNull String actionType, long timestamp, String notes) {
        this.hamsterId = hamsterId;
        this.actionType = actionType;
        this.timestamp = timestamp;
        this.notes = notes;
    }

    public CareLog(int hamsterId, int hunger, int cleanliness, int energy) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHamsterId() {
        return hamsterId;
    }

    public void setHamsterId(int hamsterId) {
        this.hamsterId = hamsterId;
    }

    @NonNull
    public String getActionType() {
        return actionType;
    }

    public void setActionType(@NonNull String actionType) {
        this.actionType = actionType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
