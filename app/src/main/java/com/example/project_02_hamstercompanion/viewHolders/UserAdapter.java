package com.example.project_02_hamstercompanion.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_02_hamstercompanion.R;
import com.example.project_02_hamstercompanion.database.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserVH> {
    public interface OnDeleteClick {
        void onDelete(User user);
    }

    private final OnDeleteClick onDeleteClick;
    private final List<User> users = new ArrayList<>();

    public UserAdapter(OnDeleteClick onDeleteClick) {
        this.onDeleteClick = onDeleteClick;
    }

    public void submitList(List<User> newUsers) {
        users.clear();
        if (newUsers != null) users.addAll(newUsers);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {
        User u = users.get(position);

        holder.usernameTv.setText(u.getUserName());
        holder.roleTv.setText(u.isAdmin() ? "ADMIN" : "USER");

        holder.deleteBtn.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                onDeleteClick.onDelete(users.get(pos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserVH extends RecyclerView.ViewHolder {
        TextView usernameTv, roleTv;
        Button deleteBtn;

        UserVH(@NonNull View itemView) {
            super(itemView);
            usernameTv = itemView.findViewById(R.id.usernameTextView);
            roleTv = itemView.findViewById(R.id.roleTextView);
            deleteBtn = itemView.findViewById(R.id.deleteUserButton);
        }
    }
}
