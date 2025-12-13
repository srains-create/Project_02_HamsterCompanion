package com.example.project_02_hamstercompanion.viewHolders;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project_02_hamstercompanion.database.HamsterRepository;
import com.example.project_02_hamstercompanion.database.entities.Hamster;

import java.util.List;

public class HamsterViewModel extends AndroidViewModel {

    private  final HamsterRepository repository;

    public HamsterViewModel(Application app) {
        super(app);
        repository = HamsterRepository.getRepository(app);
    }

    //for hamster home
    public LiveData<List<Hamster>> getHamstersOfUser(int userId){
        return repository.getHamstersOfUser(userId);
    }

    //for adoption center
    public LiveData<List<Hamster>> getHamstersForAdoption(){
        return repository.getHamstersForAdoption();
    }


}
