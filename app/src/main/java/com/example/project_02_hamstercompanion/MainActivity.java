package com.example.project_02_hamstercompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.project_02_hamstercompanion.database.HamsterRepository;
import com.example.project_02_hamstercompanion.database.entities.User;
import com.example.project_02_hamstercompanion.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "DAC_HAMSTER";

    private ActivityMainBinding binding;
    private HamsterRepository repository;
    static final String MAIN_ACTIVITY_USER_ID = "com.example.project_02_hamstercompanion.MAIN_ACTIVITY_USER_ID";
    private static final int LOGGED_OUT = -1;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = HamsterRepository.getRepository(getApplication());

        Intent intent = getIntent();
        int loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);
        LiveData<User> userObserver = repository.getUserByUserId(loggedInUserId);
        userObserver.observe(this, u -> {
            if (u != null) {
                this.user = u;
                binding.titleWelcome.setText("Welcome "+user.getUserName());
            }
        });

    }



    static Intent mainActivityIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }

}