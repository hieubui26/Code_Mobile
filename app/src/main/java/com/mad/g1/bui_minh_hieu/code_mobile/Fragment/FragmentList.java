package com.mad.g1.bui_minh_hieu.code_mobile.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mad.g1.bui_minh_hieu.code_mobile.Adapter.RecyclerViewAdapter;
import com.mad.g1.bui_minh_hieu.code_mobile.Database.SQLiteHelper;
import com.mad.g1.bui_minh_hieu.code_mobile.Model.Matches;
import com.mad.g1.bui_minh_hieu.code_mobile.R;
import com.mad.g1.bui_minh_hieu.code_mobile.UpdateDeleteActivity;
import com.mad.g1.bui_minh_hieu.code_mobile.ViewHolder.ViewHolderListener;

import java.util.ArrayList;
import java.util.List;



public class FragmentList extends Fragment implements ViewHolderListener {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SQLiteHelper sqLiteHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Matches> jobList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        sqLiteHelper = new SQLiteHelper(this.getContext());

        jobList = sqLiteHelper.getAll();
        recyclerViewAdapter.setScheduleList(jobList);
        recyclerViewAdapter.setViewHolderListener(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Matches> jobList = sqLiteHelper.getAll();
        recyclerViewAdapter.setScheduleList(jobList);
    }

    @Override
    public void onClickViewHolder(View view, int position) {
        Matches job = recyclerViewAdapter.getSchedule(position);
        Intent intent = new Intent(getContext(), UpdateDeleteActivity.class);
        intent.putExtra("matches", job);
        startActivity(intent);
    }
}