package com.example.capstoneproject.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstoneproject.Activities.ChildActivity;
import com.example.capstoneproject.DataClass.Child;
import com.example.capstoneproject.R;

import java.util.ArrayList;

public class ChildActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<Child> childList;

    public ChildActivityAdapter(Context context, ArrayList<Child> childList) {
        this.context = context;
        this.childList = childList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_childactivity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mainHolder, @SuppressLint("RecyclerView") int position) {
        ViewHolder holder = (ViewHolder) mainHolder;
        Child model = childList.get(position);
        holder.tvTitle.setText(model.getUsername());
        holder.button.setOnClickListener(view -> {
            Intent intent = new Intent(context, ChildActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return childList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.childMainName);
            button = itemView.findViewById(R.id.openActivityBtn);
        }
    }
}