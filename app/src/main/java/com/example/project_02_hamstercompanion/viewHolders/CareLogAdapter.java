package com.example.project_02_hamstercompanion.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_02_hamstercompanion.database.entities.CareLog;

import com.example.project_02_hamstercompanion.R;



import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CareLogAdapter extends RecyclerView.Adapter<CareLogAdapter.CareLogViewHolder> {
    private final List<CareLog> careLogs = new ArrayList<>();

    @NonNull
    @Override
    public CareLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_care_log, parent, false);
        return new CareLogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CareLogViewHolder holder, int position) {
        CareLog log = careLogs.get(position);
        holder.actionTypeTextView.setText(log.getActionType());

        String formattedTime = DateFormat.getDateTimeInstance().format(new Date(log.getTimestamp()));
        holder.timestampTextView.setText(formattedTime);

        if (log.getNotes() != null && !log.getNotes().isEmpty()) {
            holder.notesTextView.setVisibility(View.VISIBLE);
            holder.notesTextView.setText(log.getNotes());
        } else {
                holder.notesTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return careLogs.size();
    }
    public void setCareLogs(List<CareLog> newLogs) {
        careLogs.clear();
        if (newLogs != null) {
            careLogs.addAll(newLogs);
        }
        notifyDataSetChanged();
    }
    static class CareLogViewHolder extends RecyclerView.ViewHolder {
        TextView actionTypeTextView;
        TextView timestampTextView;
        TextView notesTextView;

        CareLogViewHolder(@NonNull View itemView) {
            super(itemView);
            actionTypeTextView = itemView.findViewById(R.id.actionTypeTextView);
            timestampTextView = itemView.findViewById(R.id.timestampTextView);
            notesTextView = itemView.findViewById(R.id.notesTextView);
        }


    }
}
