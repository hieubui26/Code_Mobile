package com.mad.g1.bui_minh_hieu.code_mobile.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mad.g1.bui_minh_hieu.code_mobile.Adapter.RecyclerViewAdapter;
import com.mad.g1.bui_minh_hieu.code_mobile.Database.SQLiteHelper;
import com.mad.g1.bui_minh_hieu.code_mobile.Model.Matches;
import com.mad.g1.bui_minh_hieu.code_mobile.Model.MemberStatistic;
import com.mad.g1.bui_minh_hieu.code_mobile.R;

import java.util.List;



public class FragmentSearch extends Fragment {

    private SearchView txtName;
    private Spinner txtStatus;
    private TextView txtTotal;
    private Button btnStatistic;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SQLiteHelper sqLiteHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtName = view.findViewById(R.id.txtName);
        txtTotal = view.findViewById(R.id.txtTotal);
        txtStatus = view.findViewById(R.id.txtStatus);
        String[] temp = getResources().getStringArray(R.array.level);
        String[] status = new String[temp.length + 1];
        status[0] = "All";
        for (int i = 0; i < temp.length; i++) {
            status[i + 1] = temp[i];
        }
        txtStatus.setAdapter(new ArrayAdapter<>(this.getContext(), R.layout.item_spinner, status));
        btnStatistic = view.findViewById(R.id.btnStatistic);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerViewAdapter = new RecyclerViewAdapter();
        sqLiteHelper = new SQLiteHelper(getContext());

        txtName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Matches> jobList = sqLiteHelper.searchByName(s);
                recyclerViewAdapter.setScheduleList(jobList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);
                return true;
            }
        });

        btnStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder total = new StringBuilder("");
                List<MemberStatistic> jobStatisticList = sqLiteHelper.statisticByStatus(txtStatus.getSelectedItem().toString());
                for (MemberStatistic jobStatistic : jobStatisticList) {
                    total.append(jobStatistic.getStatus()).append(": ").append(jobStatistic.getTotal()).append("\n");
                }
                txtTotal.setText(total.toString());
            }
        });
    }
}