package com.example.mobileappcska.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.model.entity.User;
import com.example.mobileappcska.model.repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends AndroidViewModel {

    private AuthRepository repository;
    private MutableLiveData<FirebaseUser> userData;
    private MutableLiveData<Boolean> loggedStatus;

    public AuthViewModel(@NonNull Application application) {

        super(application);
        repository = new AuthRepository(application);
        userData = repository.getFirebaseUserMutableLiveData();
        loggedStatus = repository.getLoggedUserLiveData();
    }

    public MutableLiveData<FirebaseUser> getUserData() {
        return userData;
    }

    public MutableLiveData<Boolean> getLoggedStatus() {
        return loggedStatus;
    }

    public void registerUser(String email, String password, User user){

        repository.registerUser(email,password, user);
        
    }

    public void signInUser(String email, String password){

        repository.loginUser(email,password);

    }

    public void singOut(){

        repository.signOut();

    }

}
