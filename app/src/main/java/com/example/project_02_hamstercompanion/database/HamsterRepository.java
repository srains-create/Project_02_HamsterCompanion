package com.example.project_02_hamstercompanion.database;

import java.util.List;
import android.app.Application;
import android.util.Log;


import androidx.lifecycle.LiveData;

import com.example.project_02_hamstercompanion.MainActivity;
import com.example.project_02_hamstercompanion.database.entities.CareLog;
import com.example.project_02_hamstercompanion.database.entities.Hamster;
import com.example.project_02_hamstercompanion.database.entities.User;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HamsterRepository {
    private static HamsterRepository repository; //Khanh Ho added
    private final UserDAO userDAO;
    private final CareLogDao careLogDao; //Jael added
    private final HamsterDAO hamsterDAO; //Khanh Ho added


    //This is original
//    private HamsterRepository(Application application, HamsterDAO hamsterDAO) {
//        this.hamsterDAO = hamsterDAO;
//        HamsterDatabase db = HamsterDatabase.getDatabase(application);
//        this.userDAO = db.userDAO();
//        this.careLogDao = db.careLogDao(); //Jael added
//    }
//
//    public static HamsterRepository getRepository(Application application) {
//        if (repository != null) {
//            return repository;
//
//        }
//    }

    //This edited By Khanh Ho 13/12/25
    private HamsterRepository(Application application) throws ExecutionException, InterruptedException {
        HamsterDatabase db = HamsterDatabase.getDatabase(application);
        this.userDAO = db.userDao();
        this.careLogDao = db.careLogDao();
        this.hamsterDAO = db.hamsterDao();
    }

    public static HamsterRepository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }

        Future<HamsterRepository> future =
                HamsterDatabase.databaseWriteExecutor.submit(
                        new Callable<HamsterRepository>() {
                            @Override
                            public HamsterRepository call() throws ExecutionException, InterruptedException {
                                return new HamsterRepository(application);
                            }
                        });

        try {
            // Wait for the background task to finish and get the result
            repository = future.get();
        } catch (ExecutionException | InterruptedException e) {
            Log.d(
                    MainActivity.TAG,
                    "Problem getting HamsterRepository, thread error.",
                    e
            );
        }

        return repository;
    }

    //created by Khanh Ho 12/12/25
    public LiveData<Hamster> getHamsterById(int hamsterId) {
        return hamsterDAO.getHamsterById(hamsterId);
    }

    public void updateHamster(Hamster hamster) {
        HamsterDatabase.databaseWriteExecutor.execute(() -> {
            hamsterDAO.update(hamster);
        });
    }

    public void insertHamster(Hamster hamster) {
        HamsterDatabase.databaseWriteExecutor.execute(() -> {
            hamsterDAO.insert(hamster);
        });
    }


    public void insertCareLog(CareLog careLog) {
        HamsterDatabase.databaseWriteExecutor.execute(() -> {
            careLogDao.insert(careLog);
        });
    }
    public LiveData<List<CareLog>> getLogsForHamster(int hamsterId) {
        return careLogDao.getLogsForHamsterLiveData(hamsterId);
    }

    public void insertUser(User user) {
        HamsterDatabase.databaseWriteExecutor.execute(() -> {
            userDAO.insert(user);
        });
    }

    public LiveData<List<User>> getAllUsers() {
        return userDAO.getAllUsers();
    }
    public void deleteUser(User user) {
        HamsterDatabase.databaseWriteExecutor.execute(() -> userDAO.delete(user));
    }
    public void deleteUserById(int userId) {
        HamsterDatabase.databaseWriteExecutor.execute(() -> userDAO.deleteById(userId));
    }

    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }
    public LiveData<User> getUserByUserName(String username) {
        return userDAO.getUserByUsername(username);
    }

    //Added by Khanh Ho 12/14/25
    public LiveData<List<Hamster>> getHamstersOfUser(int userId) {
        return hamsterDAO.getHamsterOfUserIdLiveData(userId);
    }

    public LiveData<List<Hamster>> getHamstersForAdoption() {
        return hamsterDAO.getHamstersForAdoptionLiveData();
    }

    public LiveData<List<CareLog>> getAllCareLogs() {
        return careLogDao.getAllLogsLiveData();
    }

//    Future<HamsterRepository> future =
//            HamsterDatabase.databaseWriteExecutor.submit(
//                    new Callable<HamsterRepository>() {
//                        @Override
//                        public HamsterRepository call() {
//                            return new HamsterRepository(application);
//                        }
//                    }
//            );
//
//        try
//
//    {
//        repository = future.get();   // wait for it to be created
//        return repository;
//    } catch(InterruptedException |
//    ExecutionException e)
//
//    {
//        Log.d(MainActivity.TAG,
//                "Problem getting HamsterRepository, thread error.", e);
//    }
//        return null;
}

