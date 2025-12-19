package com.example.project_02_hamstercompanion.viewHolders;

import android.content.res.ColorStateList;
import android.graphics.Color;
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
    final Button hamsterButton;

    private HamsterViewHolder(@NonNull View itemView) {
        super(itemView);
        //getting references
        hamsterName = itemView.findViewById(R.id.hamsterNameTextView);
        hamsterHunger = itemView.findViewById(R.id.hamsterHungerTextView);
        hamsterEnergy = itemView.findViewById(R.id.hamsterEnergyTextView);
        hamsterCleanliness = itemView.findViewById(R.id.hamsterCleanlinessTextView);
        hamsterButton = itemView.findViewById(R.id.hamsterActionButton);
    }

    public void bind(Hamster hamster) {
        hamsterName.setText(String.valueOf(hamster.getName()));
        hamsterHunger.setText(String.valueOf(hamster.getHunger()));
        hamsterEnergy.setText(String.valueOf(hamster.getEnergy()));
        hamsterCleanliness.setText(String.valueOf(hamster.getCleanliness()));
        if (hamster.getAdoptionDate() != null) {
            //not null adoption date = its in hamster home
            hamsterButton.setText("Care");
            hamsterButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#67568F")));
        } else {
            hamsterButton.setText("Adopt");
            hamsterButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#71945D")));
        }
    }

    static HamsterViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hamster,
                        parent, false);
        return new HamsterViewHolder(view);
    }
}
