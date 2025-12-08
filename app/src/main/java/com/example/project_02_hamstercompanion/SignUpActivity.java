package com.example.project_02_hamstercompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.project_02_hamstercompanion.database.UserDAO;
import com.example.project_02_hamstercompanion.database.entities.User;
import com.example.project_02_hamstercompanion.databinding.ActivitySignupBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       binding = ActivitySignupBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());

       binding.signUpButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               createUser();
           }
       });

       binding.signInButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(SignInActivity.signInIntentFactory(getApplicationContext()));
           }
       });

    }

    private void createUser(){
        if (!binding.passwordSignUpEditText.getText().toString().equals(binding.rePasswordSignUpEditText.getText().toString())){
            ToastMaker("The passwords do not match!");
        } else {
            String password = binding.passwordSignUpEditText.getText().toString();
            String username = binding.usernameSignUpEditText.getText().toString();

            //Todo: How should we designate when a new user is an admin or not?
            new User(username, password, false);
            ToastMaker("New User Created!"); //Todo: Potentially Create responses for Dupe User cases
            startActivity(SignInActivity.signInIntentFactory(getApplicationContext()));
            // Todo: Ask the group if it should go back to SignIn screen or just auto-SignIn and launch the main activity.

        }
    }

    static Intent signUpIntentFactory(Context context){
        return new Intent(context,SignUpActivity.class);
    }

    private void ToastMaker(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
