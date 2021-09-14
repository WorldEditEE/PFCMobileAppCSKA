package com.example.mobileappcska.view.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mobileappcska.R;
import com.example.mobileappcska.view.adapter.ClubAdapter;
import com.example.mobileappcska.data.Club;
import com.example.mobileappcska.viewmodel.TableRoomViewModel;
import java.util.ArrayList;
import java.util.List;


public class TablesFragment extends Fragment{

    private ClubAdapter clubAdapter;
    private List<Club> clubs = new ArrayList<>();
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TableRoomViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tables, container, false);
        progressBar = view.findViewById(R.id.progressBarTable);
        recyclerView = view.findViewById(R.id.recyclerViewTables);

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication())).get(TableRoomViewModel.class);

        viewModel.getClubs().observe(getActivity(), new Observer<List<Club>>() {
            @Override
            public void onChanged(List<Club> clubs) {
                if(clubs != null){
                    clubAdapter.setClubs(clubs);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
        initRecyclerView(view);

        return view;

    }

    private void initRecyclerView(View view) {

        progressBar.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        clubAdapter = new ClubAdapter(clubs);
        recyclerView.setAdapter(clubAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.initTable();
    }


}