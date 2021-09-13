package com.example.mobileappcska.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.data.Club;
import com.example.mobileappcska.model.TableRepository;

import java.util.List;

public class TableViewModel extends AndroidViewModel {

    private MutableLiveData<List<Club>> clubList;
    private TableRepository repository;

    public TableViewModel(@NonNull Application application) {

        super(application);
        repository = new TableRepository(application);
        clubList = repository.getClubList();
    }

    public MutableLiveData<List<Club>> getClubList() {
        return clubList;
    }

    public void initTable(){
        repository.initTable();
    }
}
