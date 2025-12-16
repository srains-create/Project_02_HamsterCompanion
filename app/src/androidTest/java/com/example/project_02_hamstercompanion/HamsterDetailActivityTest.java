package com.example.project_02_hamstercompanion;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class HamsterDetailActivityTest {

    @Before
    public void setUp() throws Exception { }

    @After
    public void tearDown() throws Exception { }

    // test 1
    @Test
    public void intentFactory_putsCorrectHamsterId() {
        Context context = ApplicationProvider.getApplicationContext();
        int expectedId = 42;

        Intent intent = HamsterDetailActivity.intentFactory(context, expectedId);

        int actualId =
                intent.getIntExtra(HamsterDetailActivity.EXTRA_HAMSTER_ID, -1);

        assertEquals(expectedId, actualId);
    }

    // test 2
    @Test
    public void intentFactory_setsCorrectTargetActivity() {
        Context context = ApplicationProvider.getApplicationContext();
        int hamsterId = 7;

        Intent intent = HamsterDetailActivity.intentFactory(context, hamsterId);

        assertNotNull(intent.getComponent());
        assertEquals(
                HamsterDetailActivity.class.getName(),
                intent.getComponent().getClassName()
        );
    }
}
