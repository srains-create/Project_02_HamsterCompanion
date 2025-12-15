package com.example.project_02_hamstercompanion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_02_hamstercompanion.database.HamsterRepository;
import com.example.project_02_hamstercompanion.databinding.ActivityHamsterAdoptionBinding;
import com.example.project_02_hamstercompanion.viewHolders.HamsterAdapter;
import com.example.project_02_hamstercompanion.viewHolders.HamsterViewModel;

public class HamsterAdoptionActivity extends AppCompatActivity {

    private ActivityHamsterAdoptionBinding binding;
    private HamsterRepository repository;
    private HamsterViewModel hamsterViewModel;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHamsterAdoptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = HamsterRepository.getRepository(getApplication());

        hamsterViewModel = new ViewModelProvider(this).get(HamsterViewModel.class);
        RecyclerView recyclerView = binding.hamsterRecycler;
        final HamsterAdapter adapter = new HamsterAdapter(new HamsterAdapter.HamsterDiff(),
                HamsterAdapter.ADOPTION_CENTER);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        hamsterViewModel.getHamstersForAdoption().observe(this, hamsters -> {
            adapter.submitList(hamsters);
        });

    }
}
