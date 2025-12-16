package com.example.project_02_hamstercompanion;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.project_02_hamstercompanion.database.CareLogDao;
import com.example.project_02_hamstercompanion.database.HamsterDAO;
import com.example.project_02_hamstercompanion.database.HamsterDatabase;
import com.example.project_02_hamstercompanion.database.entities.CareLog;
import com.example.project_02_hamstercompanion.database.entities.Hamster;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class HamsterDatabaseTest {

    private HamsterDatabase db;
    private HamsterDAO hamsterDao;
    private CareLogDao careLogDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, HamsterDatabase.class)
                .allowMainThreadQueries()
                .build();

        hamsterDao = db.hamsterDao();
        careLogDao = db.careLogDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    // hamster stat updates
    @Test
    public void updateHamsterStats_areSavedInDatabase() {
        // insert a hamster
        Hamster hamster = new Hamster(
                1,          // userId
                "Testy",    // name
                50,         // hunger
                50,         // cleanliness
                50          // energy
        );
        hamsterDao.insert(hamster);

        Hamster fromDb = hamsterDao.getAllHamsters().get(0);
        int id = fromDb.getHamsterId();

        // change stats
        fromDb.setHunger(40);
        fromDb.setEnergy(60);

        hamsterDao.update(fromDb);

        Hamster updated = hamsterDao.getAllHamsters().get(0);
        assertEquals(40, updated.getHunger());
        assertEquals(60, updated.getEnergy());
        assertEquals(id, updated.getHamsterId());
    }

    // care_log insert
    @Test
    public void insertCareLog_rowIsStoredForHamster() {
        // insert hamster
        Hamster hamster = new Hamster(1, "Logger", 50, 50, 50);
        hamsterDao.insert(hamster);
        int hamsterId = hamsterDao.getAllHamsters().get(0).getHamsterId();

        // insert one care log
        CareLog log = new CareLog(
                hamsterId,
                "feed",
                System.currentTimeMillis(),
                "Snack time"
        );
        careLogDao.insert(log);

        // query logs for this hamster
        List<CareLog> logs = careLogDao.getLogsForHamster(hamsterId);

        assertEquals(1, logs.size());
        assertEquals("feed", logs.get(0).getActionType());
        assertEquals(hamsterId, logs.get(0).getHamsterId());
    }
}
