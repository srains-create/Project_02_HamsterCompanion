package com.example.project_02_hamstercompanion;

import android.os.Bundle;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_02_hamstercompanion.database.HamsterRepository;
import com.example.project_02_hamstercompanion.databinding.ActivityHamsterHomeBinding;
import com.example.project_02_hamstercompanion.viewHolders.HamsterAdapter;
import com.example.project_02_hamstercompanion.viewHolders.HamsterViewModel;

public class HamsterHomeActivity extends AppCompatActivity {
    private ActivityHamsterHomeBinding binding;
    private HamsterRepository repository;
    private HamsterViewModel hamsterViewModel;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHamsterHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.careLogButton.setOnClickListener(v -> {
            startActivity(new Intent(HamsterHomeActivity.this, CareLogActivity.class));
        });

        repository = HamsterRepository.getRepository(getApplication());

        hamsterViewModel = new ViewModelProvider(this).get(HamsterViewModel.class);
        RecyclerView recyclerView = binding.hamsterRecycler;
        final HamsterAdapter adapter = new HamsterAdapter(new HamsterAdapter.HamsterDiff(),
                HamsterAdapter.HAMSTER_HOME);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //TODO: replace "userId" with user id once login is implemented
//        hamsterViewModel.getHamstersOfUser(userId).observe(this, hamsters -> {
//            adapter.submitList(hamsters);
//        });

    }
}
