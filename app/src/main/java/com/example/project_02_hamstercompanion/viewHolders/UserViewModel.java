package com.example.project_02_hamstercompanion.viewHolders;

import android.app.Application;

import androidx.annotation.NonNull;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project_02_hamstercompanion.database.HamsterRepository;
import com.example.project_02_hamstercompanion.database.entities.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private final HamsterRepository repository;
    private final LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = HamsterRepository.getRepository(application);
        allUsers = repository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insertUser(User user) {
        repository.insertUser(user);
    }

    public void deleteUser(User user) {
        repository.deleteUser(user);
    }
}
