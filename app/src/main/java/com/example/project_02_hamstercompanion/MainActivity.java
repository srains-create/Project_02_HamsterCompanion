package com.example.project_02_hamstercompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_02_hamstercompanion.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "DAC_HAMSTER";

    private ActivityMainBinding binding;
    private int userId;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get data from sign in
        userId  = getIntent().getIntExtra("USER_ID", -1);
        username = getIntent().getStringExtra("USERNAME");

        //show username
        binding.welcomeTextView.setText("Welcome, " + username);//I took this out + (userId) to fix awkward number next to welcome text-Jael

        //hamster room button
        binding.hamsterRoomButton.setOnClickListener(v ->{
            Intent intent = HamsterHomeActivity.hamsterHomeIntentFactory(
                    MainActivity.this,
                    userId,
                    username
            );
            startActivity(intent);
        });

        //adoption center button
        binding.adoptionCenterButton.setOnClickListener(v -> {
            Intent intent = HamsterAdoptionActivity.adoptionIntentFactory(
                    MainActivity.this,
                    userId,
                    username
            );
            startActivity(intent);
        });
    }
    static Intent mainActivityIntentFactory(Context context, int userId, String username) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("USER_ID", userId);
        intent.putExtra("USERNAME", username);
        return intent;
    }

}