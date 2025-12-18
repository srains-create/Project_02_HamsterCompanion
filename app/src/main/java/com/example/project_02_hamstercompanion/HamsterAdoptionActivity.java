package com.example.project_02_hamstercompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_02_hamstercompanion.database.HamsterRepository;
import com.example.project_02_hamstercompanion.database.entities.Hamster;
import com.example.project_02_hamstercompanion.databinding.ActivityHamsterAdoptionBinding;
import com.example.project_02_hamstercompanion.viewHolders.HamsterAdapter;
import com.example.project_02_hamstercompanion.viewHolders.HamsterViewModel;

import java.time.LocalDateTime;

public class HamsterAdoptionActivity extends AppCompatActivity implements HamsterAdapter.HamsterAdapterListener {

    private ActivityHamsterAdoptionBinding binding;
    private HamsterRepository repository;
    private HamsterViewModel hamsterViewModel;
    int loggedInUserId;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHamsterAdoptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get data from MainActivity / SignInActivity
        this.loggedInUserId  = getIntent().getIntExtra("USER_ID", -1);
        String username = getIntent().getStringExtra("USERNAME");

        //back button onclick
        binding.backButton2.setOnClickListener(v -> {
            startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext()
                    ,this.loggedInUserId
                    ,username));
        });

        //get repo
        repository = HamsterRepository.getRepository(getApplication());

        //setting up recyclerview
        hamsterViewModel = new ViewModelProvider(this).get(HamsterViewModel.class);

        RecyclerView recyclerView = binding.hamsterRecycler;
        final HamsterAdapter adapter = new HamsterAdapter(new HamsterAdapter.HamsterDiff(),
                this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        hamsterViewModel.getHamstersForAdoption().observe(this, hamsters -> {
            adapter.submitList(hamsters);
        });

    }

    public void adoptHamster(Hamster hamster){
        hamster.setAdoptionDate(LocalDateTime.now());
        hamster.setUserId(loggedInUserId);
        repository.updateHamster(hamster);

    }

    public static Intent adoptionIntentFactory(Context context, int userId, String username){
        Intent intent = new Intent(context, HamsterAdoptionActivity.class);
        intent.putExtra("USER_ID", userId);
        intent.putExtra("USERNAME", username);
        return intent;
    }
}
