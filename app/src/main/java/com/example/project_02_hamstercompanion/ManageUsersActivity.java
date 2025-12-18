package com.example.project_02_hamstercompanion;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_02_hamstercompanion.database.entities.User;
import com.example.project_02_hamstercompanion.viewHolders.UserAdapter;
import com.example.project_02_hamstercompanion.viewHolders.UserViewModel;

public class ManageUsersActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        RecyclerView rv = findViewById(R.id.usersRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new UserAdapter(user -> userViewModel.deleteUserById(user.getUserId()));//
        rv.setAdapter(adapter);

        userViewModel.getAllUsers().observe(this, users -> adapter.submitList(users));

        EditText usernameEt = findViewById(R.id.newUsernameEditText);
        EditText passwordEt = findViewById(R.id.newPasswordEditText);
        Button addBtn = findViewById(R.id.addUserButton);
        Button backBtn = findViewById(R.id.backButton);

        addBtn.setOnClickListener(v -> {
            String u = usernameEt.getText().toString().trim();
            String p = passwordEt.getText().toString().trim();

            if (u.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            User newUser = new User(u, p, false);
            userViewModel.insertUser(newUser);

            usernameEt.setText("");
            passwordEt.setText("");
            Toast.makeText(this, "User added.", Toast.LENGTH_SHORT).show();

        });
        backBtn.setOnClickListener(v -> finish());

    }


}
