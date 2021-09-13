package com.example.mobileappcska.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.data.User;
import com.example.mobileappcska.model.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    private MutableLiveData<User> userMutableLiveData;

    public UserViewModel(@NonNull Application application) {

        super(application);
        repository = new UserRepository(application);
        userMutableLiveData = repository.getUserMutableLiveData();


    }

    public MutableLiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public void getUserInfo(String email){
        repository.getUserInfo(email);
    }

    public void getUserRole(String email){
        repository.getUserRole(email);
    }
}
