package com.example.project_02_hamstercompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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

        // get data from SignInActivity
        int userId = getIntent().getIntExtra("USER_ID", -1);
        String username = getIntent().getStringExtra("USERNAME");
        // Show username on screen
        binding.usernameTextView.setText("Welcome, " + username);

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

    public static Intent hamsterHomeIntentFactory(Context context, int userId, String username){
        Intent intent = new Intent(context, HamsterHomeActivity.class);
        intent.putExtra("USER_ID", userId);
        intent.putExtra("USERNAME", username);
        return intent;
    }
}
