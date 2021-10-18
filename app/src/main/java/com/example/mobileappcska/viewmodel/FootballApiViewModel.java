package com.example.mobileappcska.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.model.API.PlayerRepository;
import com.example.mobileappcska.model.API.entity.Result;

import java.util.List;

public class FootballApiViewModel extends AndroidViewModel {

    private MutableLiveData<List<Result>> mutableLiveData;
    private PlayerRepository repository;

    public FootballApiViewModel(@NonNull Application application) {
        super(application);

        repository = new PlayerRepository(application);
        mutableLiveData = repository.getListMutableLiveData();
    }

    public MutableLiveData<List<Result>> getMutableLiveData() {
        return mutableLiveData;

    }

    public void initPlayerList(){
        repository.initListPlayers();
    }
}
