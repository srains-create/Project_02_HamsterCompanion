package com.example.project_02_hamstercompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

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

    }

    private void createUser(){
        if (binding.passwordSignUpEditText.getText().toString() != binding.rePasswordSignUpEditText.getText().toString()){
            ToastMaker("The passwords do not match!");
        } else {

        }
    }

    static Intent signUpIntentFactory(Context context){
        return new Intent(context,SignUpActivity.class);
    }

    private void ToastMaker(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
