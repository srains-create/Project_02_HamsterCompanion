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

    enum LoginCheckResult { // Unit test helper code. Result of a login attempt.
        SUCCESS,            // Exists for ease of testing
        USER_NOT_FOUND,
        WRONG_PASSWORD,
        INVALID_PASSWORD_INPUT
    }

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

    // Pure logic: doesn't touch Android or Room, just checks user + password
    // Perfect for Unit Testing purposes.
    static LoginCheckResult checkLoginResult(User user, String inputPassword) {
        if (inputPassword == null || inputPassword.isEmpty()) {
            return LoginCheckResult.INVALID_PASSWORD_INPUT;
        }

        if (user == null) {
            return LoginCheckResult.USER_NOT_FOUND;
        }

        String stored = user.getUserPassword();
        if (stored == null) {
            return LoginCheckResult.WRONG_PASSWORD;
        }

        return stored.equals(inputPassword)
                ? LoginCheckResult.SUCCESS
                : LoginCheckResult.WRONG_PASSWORD;
    }


    protected void verifyUser(){
        String username = binding.usernameSignInEditText.getText().toString();
        String password = binding.passwordSignInEditText.getText().toString();

        if(username.isEmpty()){
            ToastMaker("Username shouldn't be blank.");
        }
        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
            LoginCheckResult result = checkLoginResult(user, password);

            switch (result) {
                case SUCCESS:
                    ToastMaker("It's working!");
                    // TODO: Assign target Activity to verifyUser. startActivity();
                    break;

                case WRONG_PASSWORD:
                    ToastMaker("Password invalid");
                    break;

                case USER_NOT_FOUND:
                    ToastMaker(username + " isn't a valid username");
                    break;

                case INVALID_PASSWORD_INPUT:
                    ToastMaker("Password shouldn't be blank.");
                    break;
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
