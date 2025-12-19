package com.example.project_02_hamstercompanion;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.project_02_hamstercompanion.database.entities.Hamster;

import java.util.Optional;

// tests for Hamster entity
public class HamsterEntityTests {

    // checks hamster constructor sets fields correctly
    @Test
    public void hamsterConstructor_setsFieldsCorrectly(){
        Hamster h = new Hamster(
                1,
                "Fluffy",
                10,
                20,
                30

        );
        // makes sure each getter returns the value passed in
        assertEquals(Optional.of(1), h.getUserId());
        assertEquals("Fluffy", h.getName());
        assertEquals(10, h.getHunger());
        assertEquals(20, h.getCleanliness());
        assertEquals(30, h.getEnergy());
    }

    // checks that a new hamster doesn't have adoption date set
    @Test
    public void adoptionDate_isNullByDefault(){
        Hamster h = new Hamster(null,"Fluffy",10,20,30);

        assertNull(h.getAdoptionDate()); // new hamster to not have date yet
    }
}
