package com.example.project_02_hamstercompanion.viewHolders;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.example.project_02_hamstercompanion.database.entities.Hamster;

public class HamsterAdapter extends ListAdapter<Hamster, HamsterViewHolder> {


    public static final int HAMSTER_HOME = 1;
    public static final int ADOPTION_CENTER = 2;


    public HamsterAdapter(@NonNull DiffUtil.ItemCallback<Hamster> diffCallback, int buttonType){
        super(diffCallback);
    }

    @NonNull
    @Override
    public HamsterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return HamsterViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull HamsterViewHolder holder, int position){
        Hamster hamster = getItem(position);
        holder.bind(hamster);
    }

    public static class HamsterDiff extends DiffUtil.ItemCallback<Hamster>{
        @Override
        public boolean areItemsTheSame(@NonNull Hamster oldHamster, @NonNull Hamster newHamster){
            return oldHamster == newHamster;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Hamster oldHamster, @NonNull Hamster newHamster) {
            return oldHamster.equals(newHamster);
        }
    }


}
