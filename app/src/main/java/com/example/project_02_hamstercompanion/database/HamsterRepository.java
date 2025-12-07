package com.example.project_02_hamstercompanion.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.project_02_hamstercompanion.MainActivity;
import com.example.project_02_hamstercompanion.database.entities.User;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HamsterRepository {
    private final UserDAO userDAO;

    private static HamsterRepository repository;

    private HamsterRepository(Application application) {
        HamsterDatabase db = HamsterDatabase.getDatabase(application);
        this.userDAO = db.userDAO();
    }
    public static HamsterRepository getRepository(Application application) {
        if(repository!= null){
            return repository;

    }

        Future<HamsterRepository> future =
                HamsterDatabase.databaseWriteExecutor.submit(
                        new Callable<HamsterRepository>() {
                            @Override
                            public HamsterRepository call() {
                                return new HamsterRepository(application);
                            }
                        }
                );

        try {
            repository = future.get();   // wait for it to be created
            return repository;
        } catch (InterruptedException | ExecutionException e) {
            Log.d(MainActivity.TAG,
                    "Problem getting HamsterRepository, thread error.", e);
        }
        return null;

    }

    public void insertUser(User... users){
        HamsterDatabase.databaseWriteExecutor.execute(()->{
            userDAO.insert(users);
        });
    }

    public void deleteUser(User user){
        HamsterDatabase.databaseWriteExecutor.execute(() -> {
                userDAO.delete(user);
        });
    }

    public void deleteAllUsers(){
        HamsterDatabase.databaseWriteExecutor.execute(()-> {
            userDAO.deleteAll();
                });
    }

    public LiveData<List<User>> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public LiveData<User> getUserByUserName(String username) {
        return userDAO.getUserByUsername(username);
    }

    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }



}
