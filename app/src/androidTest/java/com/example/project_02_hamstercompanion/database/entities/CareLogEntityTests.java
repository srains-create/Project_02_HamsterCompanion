package com.example.project_02_hamstercompanion.database.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class CareLogEntityTests {

    @Test
    public void careLog_hasCorrectFields(){
        int hamsterId = 1;
        String action = "rest";
        long timestamp = System.currentTimeMillis();
        String toastNote = "Hamster rested.";
        CareLog careLog = new CareLog(hamsterId, action, timestamp, toastNote);

        assertEquals(hamsterId, careLog.getHamsterId());
        assertEquals(action, careLog.getActionType());
        assertEquals(timestamp, careLog.getTimestamp());
        assertEquals(toastNote, careLog.getNotes());
    }
}