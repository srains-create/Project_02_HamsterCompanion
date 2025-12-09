package com.example.project_02_hamstercompanion;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_02_hamstercompanion.viewHolders.CareLogAdapter;
import com.example.project_02_hamstercompanion.viewHolders.CareLogViewModel;
public class CareLogActivity extends AppCompatActivity {
    public static final String EXTRA_HAMSTER_ID = "CareLogActivity.HAMSTER_ID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_log);

        int hamsterId = getIntent().getIntExtra(EXTRA_HAMSTER_ID, -1);
        if (hamsterId == -1) {
            finish();
            return;
        }

        RecyclerView recyclerView = findViewById(R.id.careLogRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CareLogAdapter adapter = new CareLogAdapter();
        recyclerView.setAdapter(adapter);

        CareLogViewModel viewModel = new ViewModelProvider(this).get(CareLogViewModel.class);
        viewModel.getLogsForHamster(hamsterId).observe(this, adapter::setCareLogs);
    }
}
