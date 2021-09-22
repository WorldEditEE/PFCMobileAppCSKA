package com.example.mobileappcska.model.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.model.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UserRepository {

    private Application application;
    private MutableLiveData<User> userMutableLiveData;
    private MutableLiveData<String> roleMutableLiveData;
    private FirebaseUser firebaseUser;
   // private User user;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;

    public UserRepository(Application application){

        this.application = application;
        userMutableLiveData = new MutableLiveData<>();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();



    }

    public MutableLiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<String> getRoleMutableLiveData() {
        return roleMutableLiveData;
    }

    public void getUserInfo(String email){

        db.collection("users").document(firebaseUser.getUid()).collection(email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        String name = document.getString("name");
                        String age = document.getString("age");
                        String role = document.getString("role");
                        User user = new User(email,age,name,role);
                        userMutableLiveData.postValue(user);
                    }
                }
            }
        });

    }

    public void getUserRole(String email){

        db.collection("users").document(firebaseUser.getUid()).collection(email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        String role = document.getString("role");
                        roleMutableLiveData.postValue(role);
                    }
                }
            }
        });

    }


}
