package com.example.mobileappcska.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.mobileappcska.data.Club;
import com.example.mobileappcska.model.room.TableRoomRepository;

import java.util.List;

public class TableRoomViewModel extends AuthViewModel{

    private LiveData<List<Club>> clubs;
    private TableRoomRepository repository;

    public TableRoomViewModel(@NonNull Application application) {
        super(application);
        repository = new TableRoomRepository(application);
        clubs = repository.getListClubLiveData();
    }

    public LiveData<List<Club>> getClubs() {
        return clubs;
    }

    public void initTable(){
        repository.initTable();
    }
}
