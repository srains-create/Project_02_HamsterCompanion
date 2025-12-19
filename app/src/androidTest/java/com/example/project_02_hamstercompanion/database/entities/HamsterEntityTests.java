package com.example.project_02_hamstercompanion.database.entities;

import org.junit.Test;
import static org.junit.Assert.*;

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
        assertEquals(1, (int) h.getUserId());
        assertEquals("Fluffy", h.getName());
        assertEquals(10, h.getHunger());
        assertEquals(20, h.getCleanliness());
        assertEquals(30, h.getEnergy());
    }

    // checks that a hamster made with null user doesn't have adoption date set
    @Test
    public void adoptionDate_isNullWhenUserNull(){
        //user
        Hamster h = new Hamster(null,"Fluffy",10,20,30);
        assertNull(h.getAdoptionDate()); // new hamster to not have date yet
    }

    //checks that adoption date is set if userid is given during hamster creation
    @Test
    public void adoptionDate_isSetWhenUserNotNull() {
        Hamster h = new Hamster(1, "Fluffy",10, 20, 30);
        assertNotNull(h.getAdoptionDate());
    }
}
