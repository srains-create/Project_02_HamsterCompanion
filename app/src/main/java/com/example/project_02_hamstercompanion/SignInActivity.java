package com.example.project_02_hamstercompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.project_02_hamstercompanion.database.HamsterRepository;
import com.example.project_02_hamstercompanion.database.entities.User;
import com.example.project_02_hamstercompanion.databinding.ActivitySigninBinding;

public class SignInActivity extends AppCompatActivity {
    private ActivitySigninBinding binding;

    private HamsterRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = HamsterRepository.getRepository(getApplication());

        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUser();
            }
        });

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SignUpActivity.signUpIntentFactory(getApplicationContext()));
            }
        });

        //Admin button logic -Jael
        binding.adminButton.setOnClickListener(v -> {
            String username = binding.usernameSignInEditText.getText().toString().trim();
            String password = binding.passwordSignInEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                ToastMaker("Username and password required");
                return;
            }

            LiveData<User> adminObserver = repository.getUserByUserName(username);
            adminObserver.observe(this, user -> {
                if (user == null) {
                    ToastMaker("Invalid admin credentials");
                    return;
                }
                if (!user.getUserPassword().equals(password)) {
                    ToastMaker("Invalid admin credentials");
                    return;
                }
                if (!user.isAdmin()) {
                    ToastMaker("Access denied: not an admin");
                    return;
                }

                Intent intent = new Intent(
                        SignInActivity.this,
                        AdminActivity.class
                );
                startActivity(intent);
                finish();
            });
        });

    }

    private void verifyUser(){
        String username = binding.usernameSignInEditText.getText().toString();

        if(username.isEmpty()){
            ToastMaker("Username shouldn't be blank.");
        }
        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
            if (user != null) {
                String password = binding.passwordSignInEditText.getText().toString();
                if (password.equals(user.getUserPassword()))
                    //startActivity();    // TODO: Assign target Activity to verifyUser
                    ToastMaker("It's working!");
                else {
                    ToastMaker("Password Invalid");
                }
            } else {
                ToastMaker(String.format("%s isn't a valid username", username));
            }
        });
    }

    static Intent signInIntentFactory(Context context){
        return new Intent(context,SignInActivity.class);
    }

    private void ToastMaker(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
