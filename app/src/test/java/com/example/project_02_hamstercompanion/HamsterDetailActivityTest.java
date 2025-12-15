package com.example.project_02_hamstercompanion;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HamsterDetailActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void intentFactory_putsCorrectHamsterId() {

        // Arrange
        Context context = ApplicationProvider.getApplicationContext();
        int expectedId = 42; // any test ID you want

        // Act
        Intent intent = HamsterDetailActivity.intentFactory(context, expectedId);

        // Assert
        int actualId = intent.getIntExtra(HamsterDetailActivity.EXTRA_HAMSTER_ID, -1);

        assertEquals(expectedId, actualId);
    }



}