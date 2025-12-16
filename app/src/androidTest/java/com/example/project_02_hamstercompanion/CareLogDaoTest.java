package com.example.project_02_hamstercompanion;
import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.project_02_hamstercompanion.database.CareLogDao;
import com.example.project_02_hamstercompanion.database.HamsterDatabase;
import com.example.project_02_hamstercompanion.database.entities.CareLog;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class CareLogDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private HamsterDatabase db;
    private CareLogDao careLogDao;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, HamsterDatabase.class)
                .allowMainThreadQueries()
                .build();
        careLogDao = db.careLogDao();
    }
    @After
    public void tearDown() {
        db.close();
    }
    @Test
    public void insertAndQuery_logsAreReturned() {
        int hamsterId = 7;

        CareLog oldLog = new CareLog(hamsterId, "FEED", 1000L, "old");
        CareLog newLog = new CareLog(hamsterId, "CLEAN", 2000L, "new");

        careLogDao.insert(oldLog);
        careLogDao.insert(newLog);

        List<CareLog> results = careLogDao.getLogsForHamster(hamsterId);

        assertEquals(2, results.size());
        assertEquals(2000L, results.get(0).getTimestamp());
        assertEquals("new", results.get(0).getNotes());
    }

}
