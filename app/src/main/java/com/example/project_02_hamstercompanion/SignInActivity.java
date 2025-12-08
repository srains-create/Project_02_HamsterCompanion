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
    }

    private void verifyUser(){
        String username = binding.usernameSignInEditText.getText().toString();

        if(username.isEmpty()){
            ToastMaker("Username shouldn't be blank.");
        }
        LiveData<User> userObserver = repository.getUserByUserName(username);

    }

    static Intent signInIntentFactory(Context context){
        return new Intent(context,SignInActivity.class);
    }

    private void ToastMaker(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
