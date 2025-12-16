package com.example.project_02_hamstercompanion;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

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

@RunWith(AndroidJUnit4.class)
public class HamsterDatabaseTest {

    private HamsterDatabase db;
    private HamsterDAO hamsterDao;
    private CareLogDao careLogDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext();

        db = Room.inMemoryDatabaseBuilder(
                        context,
                        HamsterDatabase.class
                )
                .allowMainThreadQueries()
                .build();

        hamsterDao = db.hamsterDao();
        careLogDao = db.careLogDao();
    }

    @After
    public void closeDb() {
        db.close();
    }


    @Test
    public void updateHamsterStats_andInsertCareLog() {
        // Arrange: create and insert a hamster
        Hamster hamster = new Hamster(
                1,          // userId (fake)
                "Testy",    // name
                50,         // hunger
                50,         // cleanliness
                50          // energy
        );
        hamsterDao.insert(hamster);

        // read the hamster from the database
        List<Hamster> allHamsters = hamsterDao.getAllHamsters();
        Hamster stored = allHamsters.get(0);
        int hamsterId = stored.getHamsterId();

        // change stats like handleFeed() would do
        stored.setHunger(40);   // 50 - 10
        stored.setEnergy(55);   // 50 + 5
        hamsterDao.update(stored);

        // Insert a care log row for "feed"
        CareLog log = new CareLog(
                hamsterId,
                "feed",
                System.currentTimeMillis(),
                "Fed hamster."
        );
        careLogDao.insert(log);

        Hamster updated = hamsterDao.getAllHamsters().get(0);
        assertEquals(40, updated.getHunger());
        assertEquals(55, updated.getEnergy());

        // And we have exactly one care_log row for this hamster
        List<CareLog> logs = careLogDao.getLogsForHamster(hamsterId);
        assertEquals(1, logs.size());
        assertEquals("feed", logs.get(0).getActionType());
    }
}
