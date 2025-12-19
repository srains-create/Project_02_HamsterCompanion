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


    public static final String USER_ID_KEY = "USER_ID";
    public static final String USERNAME_KEY = "USERNAME";
    public static final String HAMSTER_ID_KEY = "HAMSTER_ID";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_log);


        int userId = getIntent().getIntExtra(USER_ID_KEY, -1);
        String username = getIntent().getStringExtra(USERNAME_KEY);
        int hamsterId = getIntent().getIntExtra(HAMSTER_ID_KEY, -1); // -1 if not passed

        RecyclerView recyclerView = findViewById(R.id.careLogRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CareLogAdapter adapter = new CareLogAdapter();
        recyclerView.setAdapter(adapter);

        CareLogViewModel viewModel = new ViewModelProvider(this)
                .get(CareLogViewModel.class);

        if(hamsterId == -1){
            viewModel.getAllLogs().observe(this, adapter::setCareLogs);
        } else {
            viewModel.getLogsForHamster(hamsterId).observe(this, adapter::setCareLogs);
        }
        // for per hamster logs
      //  viewModel.getAllLogs().observe(this, adapter::setCareLogs);

        // logs for the hamster that was clicked
 //       viewModel.getLogsForHamster(hamsterId)
 //               .observe(this, adapter::setCareLogs);
    }


    static Intent careLogActivityIntentFactory(Context context, int userId, String username) {
        Intent intent = new Intent(context, CareLogActivity.class);
        intent.putExtra(USER_ID_KEY, userId);
        intent.putExtra(USERNAME_KEY, username);
        return intent;
    }

    // used when you open CareLog for a specific hamster
    public static Intent careLogIntentFactory(Context context,
                                              int hamsterId,
                                              int userId,
                                              String username){
        Intent intent = new Intent(context, CareLogActivity.class);
        intent.putExtra(HAMSTER_ID_KEY, hamsterId);
        intent.putExtra(USER_ID_KEY, userId);
        intent.putExtra(USERNAME_KEY, username);
        return intent;
    }

}
