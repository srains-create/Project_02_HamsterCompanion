package com.example.project_02_hamstercompanion.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull; //Jael added
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_02_hamstercompanion.R;
import com.example.project_02_hamstercompanion.database.entities.Hamster;

public class HamsterViewHolder extends RecyclerView.ViewHolder {
    private final TextView hamsterName, hamsterHunger, hamsterEnergy, hamsterCleanliness;
    private final Button hamsterButton;
    private static final int HAMSTER_HOME = 1;
    private static final int ADOPTION_CENTER = 2;

    private HamsterViewHolder(@NonNull View itemView) {
        super(itemView);
        //getting references
        hamsterName = itemView.findViewById(R.id.hamsterNameTextView);
        hamsterHunger = itemView.findViewById(R.id.hamsterHungerTextView);
        hamsterEnergy = itemView.findViewById(R.id.hamsterEnergyTextView);
        hamsterCleanliness = itemView.findViewById(R.id.hamsterCleanlinessTextView);
        hamsterButton = itemView.findViewById(R.id.hamsterActionButton);
    }

    public void setButtonType(int buttonType) {
        //change color too? (not implemented yet)
        switch (buttonType) {
            case HAMSTER_HOME:
                hamsterButton.setText("View");//HAMSTER_HOME
                break;
            case ADOPTION_CENTER:
                hamsterButton.setText("Adopt");//ADOPTION_CENTER
                break;
        }
    }

    public void bind(Hamster hamster) {
        hamsterName.setText(hamster.getName());
        hamsterHunger.setText(String.valueOf(hamster.getHunger()));
        hamsterEnergy.setText(String.valueOf(hamster.getEnergy()));
        hamsterCleanliness.setText(String.valueOf(hamster.getCleanliness()));
    }

    public static HamsterViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hamster, parent, false);//activity_hamster_home
        return new HamsterViewHolder(view);
    }
}
