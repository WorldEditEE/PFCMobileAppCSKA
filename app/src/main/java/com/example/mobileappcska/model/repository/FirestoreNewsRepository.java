package com.example.mobileappcska.model.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.model.entity.News;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
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

    public void addNews(News news){
        database.collection("news").add(news).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()){
                    Toast.makeText(application, "Новость успешно добавлена", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(application, "Ошибка при добавлении", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
