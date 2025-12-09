/**
 * Group 8
 * CST 338
 * @author Jael Roman
 * @since 12/8/25
 * **/

package com.example.project_02_hamstercompanion.viewHolders;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project_02_hamstercompanion.database.HamsterRepository;
import com.example.project_02_hamstercompanion.database.entities.CareLog;

import java.util.List;

public class CareLogViewModel extends AndroidViewModel {
    private final HamsterRepository repository;

    public CareLogViewModel(@NonNull Application application) {
        super(application);
        repository = HamsterRepository.getRepository(application);
    }

    public LiveData<List<CareLog>> getLogsForHamster(int hamsterId) {
        return repository.getLogsForHamster(hamsterId);
    }

    public void addCareLog(CareLog log) {
        repository.insertCareLog(log);
    }


}
