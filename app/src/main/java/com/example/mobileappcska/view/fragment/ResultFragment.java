package com.example.mobileappcska.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappcska.R;
import com.example.mobileappcska.model.entity.Player;
import com.example.mobileappcska.view.adapter.PlayerAdapter;
import com.example.mobileappcska.viewmodel.FootballApiViewModel;

import java.util.ArrayList;
import java.util.List;

public class ResultFragment extends Fragment {

    private RecyclerView recyclerViewPlayers;
    private PlayerAdapter playerAdapter;
    private FootballApiViewModel viewModel;
    private List<Player> playerList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        recyclerViewPlayers = view.findViewById(R.id.recyclerViewPlayers);
        playerList = new ArrayList<>();
        playerAdapter = new PlayerAdapter();
        playerAdapter.setPlayerList(playerList);
        recyclerViewPlayers.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewPlayers.setAdapter(playerAdapter);


        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication())).get(FootballApiViewModel.class);

        viewModel.initPlayerList();

        viewModel.getMutableLiveData().observe(getActivity(), new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                playerAdapter.setPlayerList(players);
            }
        });





        return view;
    }
}