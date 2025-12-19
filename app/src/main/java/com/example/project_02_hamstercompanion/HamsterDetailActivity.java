package com.example.project_02_hamstercompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.project_02_hamstercompanion.database.HamsterRepository;
import com.example.project_02_hamstercompanion.database.entities.CareLog;
import com.example.project_02_hamstercompanion.database.entities.Hamster;

public class HamsterDetailActivity extends AppCompatActivity {

    public static final String EXTRA_HAMSTER_ID = "HAMSTER_ID";

    private TextView textHamsterName;
    private TextView textHungerValue;
    private TextView textCleanlinessValue;
    private TextView textEnergyValue;

    private Button buttonFeed;
    private Button buttonClean;
    private Button buttonPlay;
    private Button buttonRest;
    private Button buttonBack;

    private HamsterRepository repository;
    private Hamster currentHamster;
    private int userId;
    private String username;


    public static Intent hamsterDetailActivityIntentFactory(
            Context context,
            int userId,
            String username,
            int hamsterId
    ) {
        Intent intent = new Intent(context, HamsterDetailActivity.class);
        intent.putExtra("USER_ID", userId);
        intent.putExtra("USERNAME", username);
        intent.putExtra(EXTRA_HAMSTER_ID, hamsterId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamster_detail);

        initViews();

        repository = HamsterRepository.getRepository(getApplication());

        // read the extras
        userId = getIntent().getIntExtra("USER_ID", -1);
        username = getIntent().getStringExtra("USERNAME");
        int hamsterId = getIntent().getIntExtra(EXTRA_HAMSTER_ID, -1);

        if (hamsterId == -1) {
            // close the screen if no valid hamster ID
            finish();
            return;
        }

        loadHamster(hamsterId);
        setupButtonListeners();
    }

    private void initViews() {
        textHamsterName = findViewById(R.id.textHamsterName);
        textHungerValue = findViewById(R.id.textHungerValue);
        textCleanlinessValue = findViewById(R.id.textCleanlinessValue);
        textEnergyValue = findViewById(R.id.textEnergyValue);

        buttonFeed = findViewById(R.id.buttonFeed);
        buttonClean = findViewById(R.id.buttonClean);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonRest = findViewById(R.id.buttonRest);
        buttonBack = findViewById(R.id.buttonBack);
    }

    private void setupButtonListeners() {
        buttonFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleFeed();
            }
        });

        buttonClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClean();
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePlay();
            }
        });

        buttonRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRest();
            }
        });

        // Back button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadHamster(int hamsterId) {
        LiveData<Hamster> hamsterLiveData = repository.getHamsterById(hamsterId);
        hamsterLiveData.observe(this, h -> {
            if (h != null) {
                this.currentHamster = h;
                updateUiFromHamster();
            }
        });
    }

    private void updateUiFromHamster() {
        if (currentHamster == null) {
            return;
        }

        textHamsterName.setText(currentHamster.getName());
        textHungerValue.setText(String.valueOf(currentHamster.getHunger()));
        textCleanlinessValue.setText(String.valueOf(currentHamster.getCleanliness()));
        textEnergyValue.setText(String.valueOf(currentHamster.getEnergy()));
    }

    private void handleFeed() {
        // reduce hunger by 10, minimum 0.
        // increase energy by 5, maximum 100.
        if (currentHamster == null) return;

        Toast.makeText(this, "Feed clicked", Toast.LENGTH_SHORT).show();

        int hunger = currentHamster.getHunger();
        hunger = hunger - 10;
        if (hunger < 0) hunger = 0;
        currentHamster.setHunger(hunger);

        int energy = currentHamster.getEnergy();
        energy = energy + 5;
        if (energy > 100) energy = 100;
        currentHamster.setEnergy(energy);

        repository.updateHamster(currentHamster);

        CareLog log = new CareLog(
                currentHamster.getHamsterId(),
                "feed",
                System.currentTimeMillis(),
                "Fed the hamster."
        );
        repository.insertCareLog(log);
    }

    private void handleClean() {
        if (currentHamster == null) return;

        Toast.makeText(this, "Clean clicked", Toast.LENGTH_SHORT).show();

        int cleanliness = currentHamster.getCleanliness();
        cleanliness = cleanliness + 10;
        if (cleanliness > 100) cleanliness = 100;
        currentHamster.setCleanliness(cleanliness);

        repository.updateHamster(currentHamster);

        CareLog log = new CareLog(
                currentHamster.getHamsterId(),
                "clean",
                System.currentTimeMillis(),
                "Hamster is cleaned."
        );
        repository.insertCareLog(log);

        updateUiFromHamster();
    }

    private void handlePlay() {
        if (currentHamster == null) return;

        Toast.makeText(this, "Play clicked", Toast.LENGTH_SHORT).show();

        int hunger = currentHamster.getHunger();
        hunger = hunger + 5;
        if (hunger > 100) hunger = 100;
        currentHamster.setHunger(hunger);

        int energy = currentHamster.getEnergy();
        energy = energy - 5;
        if (energy < 0) energy = 0;
        currentHamster.setEnergy(energy);

        repository.updateHamster(currentHamster);

        CareLog log = new CareLog(
                currentHamster.getHamsterId(),
                "play",
                System.currentTimeMillis(),
                "Played with hamster."
        );
        repository.insertCareLog(log);

        updateUiFromHamster();
    }

    private void handleRest() {
        if (currentHamster == null) return;

        Toast.makeText(this, "Rest clicked", Toast.LENGTH_SHORT).show();

        int energy = currentHamster.getEnergy();
        energy = energy + 10;
        if (energy > 100) energy = 100;
        currentHamster.setEnergy(energy);

        repository.updateHamster(currentHamster);

        CareLog log = new CareLog(
                currentHamster.getHamsterId(),
                "rest",
                System.currentTimeMillis(),
                "Hamster rested."
        );
        repository.insertCareLog(log);

        updateUiFromHamster();
    }
}
