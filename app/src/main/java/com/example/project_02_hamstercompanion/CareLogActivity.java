package com.example.project_02_hamstercompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_02_hamstercompanion.viewHolders.CareLogAdapter;
import com.example.project_02_hamstercompanion.viewHolders.CareLogViewModel;

public class CareLogActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_log);

        RecyclerView recyclerView = findViewById(R.id.careLogRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CareLogAdapter adapter = new CareLogAdapter();
        recyclerView.setAdapter(adapter);

        CareLogViewModel viewModel = new ViewModelProvider(this).get(CareLogViewModel.class);
        viewModel.getAllLogs().observe(this, adapter::setCareLogs);
    }


    static Intent careLogActivityIntentFactory(Context context, int userId, String username) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("USER_ID", userId);
        intent.putExtra("USERNAME", username);
        return intent;
    }

}
