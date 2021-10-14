package com.example.mobileappcska.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.model.entity.News;
import com.example.mobileappcska.model.repository.FirestoreNewsRepository;

import java.util.List;

public class NewsViewModel extends AuthViewModel{

    FirestoreNewsRepository repository;
    private MutableLiveData<List<News>> newsList;
    private MutableLiveData<String> urlImage;

    public NewsViewModel(@NonNull Application application) {

        super(application);
        repository = new FirestoreNewsRepository(application);
        newsList = repository.getNewsList();
        urlImage = repository.getUrlImage();
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

    public void addImage(Uri uri){
        repository.addImage(uri);
    }

    public MutableLiveData<String> getUrlImage() {
        return urlImage;
    }
}
