package com.example.project_02_hamstercompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.project_02_hamstercompanion.database.HamsterRepository;
import com.example.project_02_hamstercompanion.database.entities.Hamster;


import androidx.appcompat.app.AppCompatActivity;

//public class ManageHamsterActivity extends AppCompatActivity {
//
//    private int userId;
//    private String username;
//
//    public static Intent manageHamsterIntentFactory(
//            Context context,
//            int userId,
//            String username
//    ) {
//        Intent intent = new Intent(context, ManageHamsterActivity.class);
//        intent.putExtra("USER_ID", userId);
//        intent.putExtra("USERNAME", username);
//        return intent;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_manage_hamster);
//
//        userId = getIntent().getIntExtra("USER_ID", -1);
//        username = getIntent().getStringExtra("USERNAME");
//
//        Button buttonOpenHamsterRoom = findViewById(R.id.buttonOpenHamsterRoom);
//        Button buttonBackToMain = findViewById(R.id.buttonBackToMain);
//
//        // Go to HamsterHomeActivity
//        buttonOpenHamsterRoom.setOnClickListener(v -> {
//            Intent intent = HamsterHomeActivity.hamsterHomeIntentFactory(
//                    ManageHamsterActivity.this,
//                    userId,
//                    username
//            );
//            startActivity(intent);
//        });
//
//        // Go back to the previous screen
//        buttonBackToMain.setOnClickListener(v -> finish());
//    }
//}


// This edited on 12/18 by Khanh Ho
public class ManageHamsterActivity extends AppCompatActivity {

    private int userId;
    private String username;

    private HamsterRepository repository;

    public static Intent manageHamsterIntentFactory(
            Context context,
            int userId,
            String username
    ) {
        Intent intent = new Intent(context, ManageHamsterActivity.class);
        intent.putExtra("USER_ID", userId);
        intent.putExtra("USERNAME", username);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_hamster);

        userId = getIntent().getIntExtra("USER_ID", -1);
        username = getIntent().getStringExtra("USERNAME");

        repository = HamsterRepository.getRepository(getApplication());

        Button buttonAddHamster = findViewById(R.id.buttonAddHamster);
        Button buttonOpenHamsterRoom = findViewById(R.id.buttonOpenHamsterRoom);
        Button buttonBackToMain = findViewById(R.id.buttonBackToMain);

        // Add hamster
        buttonAddHamster.setOnClickListener(v -> showAddHamsterDialog());

        // Go to HamsterHomeActivity (hamster list / room)
        buttonOpenHamsterRoom.setOnClickListener(v -> {
            Intent intent = HamsterHomeActivity.hamsterHomeIntentFactory(
                    ManageHamsterActivity.this,
                    userId,
                    username
            );
            startActivity(intent);
        });

        // Go back to MainActivity
        buttonBackToMain.setOnClickListener(v -> finish());
    }

    private void showAddHamsterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Hamster");

        final EditText input = new EditText(this);
        input.setHint("Hamster name");
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = input.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            // Default stats
            Hamster hamster = new Hamster(
                    userId,
                    name,
                    50, // hunger
                    50, // cleanliness
                    50  // energy
            );

            repository.insertHamster(hamster);
            Toast.makeText(this, "Hamster added!", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }
}

