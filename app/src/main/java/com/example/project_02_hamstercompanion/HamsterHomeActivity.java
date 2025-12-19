package com.example.project_02_hamstercompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_02_hamstercompanion.database.HamsterRepository;
import com.example.project_02_hamstercompanion.database.entities.Hamster;
import com.example.project_02_hamstercompanion.databinding.ActivityHamsterHomeBinding;
import com.example.project_02_hamstercompanion.viewHolders.HamsterAdapter;
import com.example.project_02_hamstercompanion.viewHolders.HamsterViewModel;

public class HamsterHomeActivity extends AppCompatActivity implements HamsterAdapter.HamsterAdapterListener {
    private ActivityHamsterHomeBinding binding;
    private HamsterRepository repository;
    private HamsterViewModel hamsterViewModel;
    private int userId;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHamsterHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get data from SignInActivity
        userId = getIntent().getIntExtra("USER_ID", -1);
        username = getIntent().getStringExtra("USERNAME");
        // Show username on screen
       // binding.usernameTextView.setText("Welcome, " + username); I commented this out bc it showed in purple screen (potential bug). -Jael

        binding.careLogButton.setOnClickListener(v -> {//for Care Log button behavior -Jael
            startActivity(CareLogActivity.careLogActivityIntentFactory(getApplicationContext(),userId,username));
        });

        binding.backButton1.setOnClickListener(v -> {//the "back" button behavior in Care Log purple page. -Jael
            startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(),userId,username));
        });

        repository = HamsterRepository.getRepository(getApplication());

        //setting up recycler
        hamsterViewModel = new ViewModelProvider(this).get(HamsterViewModel.class);
        RecyclerView recyclerView = binding.hamsterRecycler;
        final HamsterAdapter adapter = new HamsterAdapter(
                new HamsterAdapter.HamsterDiff(),
                this
        );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        hamsterViewModel.getHamstersOfUser(userId).observe(this, adapter::submitList);
    }

    @Override
    public void startHamsterDetails(Hamster hamster) {
        startActivity(HamsterDetailActivity.hamsterDetailActivityIntentFactory(
                getApplicationContext(),
                userId, username, hamster.getHamsterId()
        ));
    }

    public static Intent hamsterHomeIntentFactory(Context context, int userId, String username){
        Intent intent = new Intent(context, HamsterHomeActivity.class);
        intent.putExtra("USER_ID", userId);
        intent.putExtra("USERNAME", username);
        return intent;
    }

    @Override
    public void adoptHamster(Hamster hamster) {
        return;
    }

    public void openCareLog(Hamster hamster){

        Intent intent = CareLogActivity.careLogActivityIntentFactory(
                this,
                hamster.getHamsterId(),
                username

        );
        startActivity(intent);
    }

    @Override
    public void onHamsterClick(Hamster hamster){
        Intent intent = HamsterDetailActivity.intentFactory(
                this,
                hamster.getHamsterId(),
                userId,
                username
        );
        startActivity(intent);
    }

}

