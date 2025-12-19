package com.example.project_02_hamstercompanion.viewHolders;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.example.project_02_hamstercompanion.database.entities.Hamster;

import java.time.LocalDateTime;

public class HamsterAdapter extends ListAdapter<Hamster, HamsterViewHolder> {

    private final HamsterAdapterListener listener;

    public static final int HAMSTER_HOME = 1;


    public interface HamsterAdapterListener {
        void adoptHamster(Hamster hamster);
        void openCareLog(Hamster hamster);
        void onHamsterClick(Hamster hamster);
    }


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

        holder.itemView.setOnClickListener(v->{
            if (listener != null){
                listener.onHamsterClick(hamster);
            }
        });

//        holder.hamsterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
        holder.hamsterButton.setOnClickListener(v -> {
            if(listener != null) {
                listener.onHamsterClick(hamster);
            }
        });

        holder.hamsterButton.setOnClickListener(v -> {
            if (listener == null) return;
            if (hamster.getAdoptionDate() == null) {
//                    //tell activity to change hamster
                    listener.adoptHamster(hamster); //adopt it
                } else {
                    //open care log
                    listener.openCareLog(hamster); // already adopted -> opens Care Log
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


}
