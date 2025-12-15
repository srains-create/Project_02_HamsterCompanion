package com.example.project_02_hamstercompanion.viewHolders;

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
    private static final int HAMSTER_HOME = 1;
    private static final int ADOPTION_CENTER = 2;

    private HamsterViewHolder(View hamsterView) {
        super(hamsterView);
        //getting references
        hamsterName = hamsterView.findViewById(R.id.hamsterNameTextView);
        hamsterHunger = hamsterView.findViewById(R.id.hamsterHungerTextView);
        hamsterEnergy = hamsterView.findViewById(R.id.hamsterEnergyTextView);
        hamsterCleanliness = hamsterView.findViewById(R.id.hamsterCleanlinessTextView);
        hamsterButton = hamsterView.findViewById(R.id.hamsterActionButton);
    }

    public void setButtonType(int buttonType) {
        //change color too? (not implemented yet)
        switch (buttonType) {
            case HAMSTER_HOME:
                hamsterButton.setText(HAMSTER_HOME);
                break;
            case ADOPTION_CENTER:
                hamsterButton.setText(ADOPTION_CENTER);
                break;
        }
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
