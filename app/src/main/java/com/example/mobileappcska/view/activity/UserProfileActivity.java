package com.example.mobileappcska.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileappcska.R;
import com.example.mobileappcska.model.entity.User;
import com.example.mobileappcska.viewmodel.AuthViewModel;
import com.example.mobileappcska.viewmodel.UserViewModel;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewEmail;
    private TextView textViewAge;
    private UserViewModel viewModelUser;
    private AuthViewModel viewModelAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        textViewName = findViewById(R.id.textViewNameProfile);
        textViewEmail = findViewById(R.id.textViewEmailProfile);
        textViewAge = findViewById(R.id.textViewAgeProfile);

        viewModelAuth = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication())).get(AuthViewModel.class);

        viewModelAuth.getUserData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null){
                    initInfoProfile(firebaseUser.getEmail());
                    viewModelUser.getUserInfo(firebaseUser.getEmail());
                }
            }
        });

        viewModelUser = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication())).get(UserViewModel.class);

        viewModelUser.getUserMutableLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                textViewEmail.setText(user.getEmail());
                textViewName.setText(user.getName());
                textViewAge.setText(user.getAge());
            }
        });


        viewModelAuth.getLoggedStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Intent intentLogOut = new Intent(UserProfileActivity.this,MainActivity.class);
                    startActivity(intentLogOut);
                }
            }
        });

    }

    public void initInfoProfile(String email){

        viewModelUser.getUserInfo(email);

    }

    public void logOut(View view) {

        viewModelAuth.singOut();
    }

    public void backToMainFromProfile(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}