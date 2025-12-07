package com.example.project_02_hamstercompanion.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project_02_hamstercompanion.database.HamsterDatabase;

import java.util.Objects;

@Entity(tableName = HamsterDatabase.USER_TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int userId;

    private String userName;

    private String userPassword;

    private boolean isAdmin;

public User(String userName, String userPassword, boolean isAdmin){
    this.userName = userName;
    this.userPassword = userPassword;
    this.isAdmin = isAdmin;
}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && isAdmin == user.isAdmin && Objects.equals(userName, user.userName) && Objects.equals(userPassword, user.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPassword, isAdmin);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}



