package com.example.mobileappcska.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mobileappcska.model.entity.Club;

@Database(entities = {Club.class},version = 2, exportSchema = false)
public abstract class ClubsDatabase extends RoomDatabase {

    private static ClubsDatabase clubDatabase;
    private static final String DB_NAME = "clubs.db";
    private static final Object LOCK = new Object();

    public static ClubsDatabase getInstance(Context context){

        synchronized (LOCK){
            if(clubDatabase == null){
                clubDatabase = Room.databaseBuilder(context, ClubsDatabase.class, DB_NAME)
                        .build();
            }
        }

        return clubDatabase;
    }

    public abstract clubsDAO clubsDAO();

}
