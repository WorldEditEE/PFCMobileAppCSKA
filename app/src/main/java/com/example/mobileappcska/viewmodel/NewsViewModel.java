package com.example.mobileappcska.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.data.News;
import com.example.mobileappcska.model.FirestoreNewsRepository;

import java.util.List;

public class NewsViewModel extends AuthViewModel{

    FirestoreNewsRepository repository;
    private MutableLiveData<List<News>> newsList;

    public NewsViewModel(@NonNull Application application) {

        super(application);
        repository = new FirestoreNewsRepository(application);
        newsList = repository.getNewsList();
    }

    public MutableLiveData<List<News>> getNewsList() {
        return newsList;
    }

    public void initNewsList(){
        repository.initNewsList();
    }

    public void addNewsList(News news){
        repository.addNews(news);
    }
}
