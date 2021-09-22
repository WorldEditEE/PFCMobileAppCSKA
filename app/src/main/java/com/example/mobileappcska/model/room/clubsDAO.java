package com.example.mobileappcska.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mobileappcska.model.entity.Club;

import java.util.List;

@Dao
public interface clubsDAO {

    @Insert
    void insert(Club club);

    @Query("SELECT * FROM club_db ORDER BY place ASC")
    LiveData<List<Club>> getAllClubs();

    @Query("DELETE FROM club_db")
    void deleteAllClubs();

    

}
