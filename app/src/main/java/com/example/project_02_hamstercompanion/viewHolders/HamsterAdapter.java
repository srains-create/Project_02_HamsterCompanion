package com.example.project_02_hamstercompanion.viewHolders;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.example.project_02_hamstercompanion.database.entities.Hamster;

import java.time.LocalDateTime;

public class HamsterAdapter extends ListAdapter<Hamster, HamsterViewHolder> {

    HamsterAdapterListener listener;
    public static final int HAMSTER_HOME = 1;



    public HamsterAdapter(@NonNull DiffUtil.ItemCallback<Hamster> diffCallback, HamsterAdapterListener listener){
        super(diffCallback);
        this.listener = listener;
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
        holder.hamsterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hamster.getAdoptionDate() == null) {
                    //tell activity to change hamster
                    listener.adoptHamster(hamster);
                } else {
                    //open care log
                }
            }
        });
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

    public interface HamsterAdapterListener {
        public void adoptHamster(Hamster hamster);
    }


}
