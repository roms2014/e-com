package com.example.e_com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_com.model.TV;

import java.util.List;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.TVViewHolder> {

    Context context;
    List<TV> TVs;

    @NonNull
    @Override
    public TVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TVViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return TVs.size();
    }

    public static final class TVViewHolder extends RecyclerView.ViewHolder
    {

        public TVViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
