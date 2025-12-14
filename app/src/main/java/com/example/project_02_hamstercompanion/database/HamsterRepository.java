package com.example.project_02_hamstercompanion.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.project_02_hamstercompanion.MainActivity;
import com.example.project_02_hamstercompanion.database.entities.CareLog;
import com.example.project_02_hamstercompanion.database.entities.Hamster;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HamsterRepository {
    private final UserDAO userDAO;
    private final CareLogDao careLogDao; //Jael added

    private final HamsterDAO hamsterDAO; //Khanh added

    private static HamsterRepository repository;

    //this is original HamsterRepository constructor
//    private HamsterRepository(Application application, HamsterDAO hamsterDAO) {
//        //this.hamsterDAO = hamsterDAO;
//        HamsterDatabase db = HamsterDatabase.getDatabase(application);
//        this.userDAO = db.userDAO();
//        this.careLogDao = db.careLogDao(); //Jael added
//        this.hamsterDAO = db.hamsterDao(); //Khanh added
//
//    }

    // Below are another constructors edited by Khanh Ho 12/12/25
    private HamsterRepository(Application application) {
        HamsterDatabase db = HamsterDatabase.getDatabase(application);
        userDAO = db.userDao();
        careLogDao = db.careLogDao();
        hamsterDAO = db.hamsterDao();
    }

    public static HamsterRepository getRepository(Application application) {
        if (repository != null) {
            return repository;

        }
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

        try

    {
        repository = future.get();   // wait for it to be created
        return repository;
    } catch(InterruptedException |
    ExecutionException e)

    {
        Log.d(MainActivity.TAG,
                "Problem getting HamsterRepository, thread error.", e);
    }
        return null;

    //created by Khanh Ho 12/12/25
    public LiveData<Hamster> getHamsterById(int hamsterId) {
        return hamsterDAO.getHamsterById(hamsterId);
    }

    public void updateHamster(Hamster hamster) {
        HamsterDatabase.databaseWriteExecutor.execute(() -> {
            hamsterDAO.update(hamster);
        });
    }

    public void insertCareLog(CareLog careLog) {
        HamsterDatabase.databaseWriteExecutor.execute(() -> {
            careLogDao.insert(careLog);
        });
    }



}
