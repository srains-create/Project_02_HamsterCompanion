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

    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
    }

    @After
    public void tearDown() {
        context = null;
    }

    @Test
    public void intentFactory_putsCorrectIds() {
        int userId = 1;
        String username = "testuser";
        int hamsterId = 42;
        Intent intent = HamsterDetailActivity.hamsterDetailActivityIntentFactory(
                context, 1, "testuser", hamsterId);
        int actualHamsterId = intent.getIntExtra(HamsterDetailActivity.EXTRA_HAMSTER_ID, -1);
        int actualUserId = intent.getIntExtra("USER_ID", -1);
        int actualUsername = intent.getStringExtra("USERNAME", -1);
        assertEquals(actualUserId, actualHamsterId);
        assertEquals(actualUsername, actualHamsterId);
    }

    @Test
    public void intentFactory_usesHamsterDetailActivityComponent() {
        int hamsterId = 7;
        Intent intent = HamsterDetailActivity.hamsterDetailActivityIntentFactory(context, hamsterId);

        assertNotNull(intent.getComponent());
        assertEquals(
                HamsterDetailActivity.class.getName(),
                intent.getComponent().getClassName()
        );
    }
}
