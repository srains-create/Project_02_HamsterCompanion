package com.example.project_02_hamstercompanion.viewHolders;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project_02_hamstercompanion.R;
import com.example.project_02_hamstercompanion.database.entities.Hamster;

public class HamsterViewHolder extends RecyclerView.ViewHolder {
    private final TextView hamsterName, hamsterHunger, hamsterEnergy, hamsterCleanliness;
    private final Button hamsterButton;

    private HamsterViewHolder(View hamsterView) {
        super(hamsterView);
        //getting references
        hamsterName = hamsterView.findViewById(R.id.hamsterNameTextView);
        hamsterHunger = hamsterView.findViewById(R.id.hamsterHungerTextView);
        hamsterEnergy = hamsterView.findViewById(R.id.hamsterEnergyTextView);
        hamsterCleanliness = hamsterView.findViewById(R.id.hamsterCleanlinessTextView);
        hamsterButton = hamsterView.findViewById(R.id.hamsterActionButton);
    }

    public void setButtonAppearance(String text) {
        hamsterButton.setText(text);
    }

    public void bind(Hamster hamster) {
        hamsterName.setText(hamster.getName());
        hamsterHunger.setText(hamster.getHunger());
        hamsterEnergy.setText(hamster.getEnergy());
        hamsterCleanliness.setText(hamster.getCleanliness());
    }

    public

    static HamsterViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_hamster_home, parent, false);
        return new HamsterViewHolder(view);
    }
}
