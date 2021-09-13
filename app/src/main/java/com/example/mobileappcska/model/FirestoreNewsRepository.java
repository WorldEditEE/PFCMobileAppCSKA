package com.example.mobileappcska.model;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.data.News;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class FirestoreNewsRepository {

    private Application application;
    private FirebaseFirestore database;
    private MutableLiveData<List<News>> newsList;

    public FirestoreNewsRepository(Application application){

        this.application = application;
        database = FirebaseFirestore.getInstance();
        newsList = new MutableLiveData<>();

    }

    public MutableLiveData<List<News>> getNewsList() {

        return newsList;
    }

    public void initNewsList(){

        database.collection("news").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value != null){
                    List<News> newsCSKAList = value.toObjects(News.class);
                    newsList.postValue(newsCSKAList);
                }
            }
        });
    }


}
