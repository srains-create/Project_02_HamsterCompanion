package com.example.project_02_hamstercompanion;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {//This will load activity_admin.xml -Jael
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        findViewById(R.id.signOutButton).setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.manageUsers).setOnClickListener(v ->  {
            startActivity(new Intent(AdminActivity.this, ManageUsersActivity.class));
        });
    }
}
