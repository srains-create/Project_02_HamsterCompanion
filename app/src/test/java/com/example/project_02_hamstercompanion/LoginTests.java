package com.example.project_02_hamstercompanion;

import com.example.project_02_hamstercompanion.database.entities.User;

import static org.junit.Assert.*;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginTests {

    private TestHamsterRepository repository;
    private SignInActivity signInActivity;  // or ViewModel/whatever holds verifyUser logic

    @Before
    public void setUp() {
        // Arrange common test objects
        repository = new TestHamsterRepository();
        // Seed with a fake user for tests
        repository.addUser("alice", "password123");


    }

    @After
    public void tearDown() {
        repository = null;
        signInActivity = null;
    }

    @Test
    public void validCredentials_returnsSuccess() {
        // Arrange
        User user = new User("alice", "password123", false);
        user.setUserName("alice");
        user.setUserPassword("password123");

        // Act
        SignInActivity.LoginCheckResult result =
                signInActivity.checkLoginResult(user, "password123");

        // Assert
        assertEquals(SignInActivity.LoginCheckResult.SUCCESS, result);
    }

    @Test
    public void invalidPassword_returnsFailure() {
        // Arrange
        User user = new User("alice", "password123", false);
        user.setUserName("alice");
        user.setUserPassword("password123");

        // Act
        SignInActivity.LoginCheckResult result =
                signInActivity.checkLoginResult(user, "wrong");

        // Assert
        assertEquals(SignInActivity.LoginCheckResult.INVALID_PASSWORD_INPUT, result);
    }

    @Test
    public void unknownUsername_returnsFailure() {
        // Arrange
        User user = null;

        // Act
        SignInActivity.LoginCheckResult result =
                signInActivity.checkLoginResult(user, "irrelevant");

        // Assert
        assertEquals(SignInActivity.LoginCheckResult.USER_NOT_FOUND, result);
    }

    /// Test helpers beyond this point.
    private static class TestHamsterRepository { // A fake repository for testing purposes

        private final java.util.Map<String, User> users = new java.util.HashMap<>();

        public void addUser(String username, String password) {
            users.put(username, new User(username, password, false));
        }

        public LiveData<User> getUserByUserName(String username) {
            MutableLiveData<User> liveData = new MutableLiveData<>();
            liveData.setValue(users.get(username)); // returns null if not found
            return liveData;
        }
    }
}
