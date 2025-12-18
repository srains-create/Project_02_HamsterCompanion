package com.example.project_02_hamstercompanion;

import static org.junit.Assert.assertEquals;

import com.example.project_02_hamstercompanion.database.entities.User;

import org.junit.Test;
import static org.junit.Assert.*;

// tests make sure that the user stores data correctly
public class UserEntityTests {

    // check that a non admin user saves correct values
    @Test
    public void normalUser_hasCorrectedFields(){
        //create a new user object
        User user = new User("user1", "password123", false);

        assertEquals("user1", user.getUserName());
        assertEquals("password123", user.getUserPassword());
        assertFalse(user.isAdmin()); // makes sure user is not admin

    }

    //checks that admin is marked as admin
    @Test
    public void adminUser_isAdminTrue(){
        User admin = new User("admin1", "secret", true);
        // makes sure the username we get back is admin1
        assertEquals("admin1", admin.getUserName());
        assertTrue(admin.isAdmin());
    }
}
