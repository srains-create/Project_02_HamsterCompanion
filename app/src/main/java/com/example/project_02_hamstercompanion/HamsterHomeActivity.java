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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHamsterHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get data from SignInActivity
        int userId = getIntent().getIntExtra("USER_ID", -1);
        String username = getIntent().getStringExtra("USERNAME");
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
        final HamsterAdapter adapter = new HamsterAdapter(new HamsterAdapter.HamsterDiff(),
               this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        hamsterViewModel.getHamstersOfUser(userId).observe(this, hamsters -> {
            adapter.submitList(hamsters);
        });

    }

    public static Intent hamsterHomeIntentFactory(Context context, int userId, String username){
        Intent intent = new Intent(context, HamsterHomeActivity.class);
        intent.putExtra("USER_ID", userId);
        intent.putExtra("USERNAME", username);
        return intent;
    }

    public void adoptHamster(Hamster hamster) {
        return;
    }
}
