package com.mad.g1.bui_minh_hieu.code_mobile.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.mad.g1.bui_minh_hieu.code_mobile.Model.Matches;
import com.mad.g1.bui_minh_hieu.code_mobile.R;
import com.mad.g1.bui_minh_hieu.code_mobile.ViewHolder.RecyclerViewHolder;
import com.mad.g1.bui_minh_hieu.code_mobile.ViewHolder.ViewHolderListener;

import java.util.ArrayList;
import java.util.List;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Matches> matchesList = new ArrayList<>();

    private ViewHolderListener viewHolderListener;

    public void setScheduleList(List<Matches> matchesList) {
        this.matchesList = matchesList;
        notifyDataSetChanged();
    }

    public Matches getSchedule(int position) {
        return this.matchesList.get(position);
    }

    public void setViewHolderListener(ViewHolderListener viewHolderListener) {
        this.viewHolderListener = viewHolderListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new RecyclerViewHolder(view, viewHolderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Matches job = this.getSchedule(position);
        holder.getTxtName().setText(job.getName());
        holder.getTxtDescription().setText(job.getDescription());
        holder.getTxtDate().setText(job.getDate());
        holder.getTxtLevel().setText(job.getLevel());
        holder.getTxtStatus().setText(!job.getStatus() ? "Máy" : "Người");

    }

    @Override
    public int getItemCount() {
        return this.matchesList.size();
    }
}