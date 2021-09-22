package com.example.mobileappcska.model.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.model.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthRepository {

    private Application application;
    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
    private MutableLiveData<Boolean> loggedUserLiveData;
    private FirebaseAuth firebaseAuth;


    public MutableLiveData<Boolean> getLoggedUserLiveData() {
        return loggedUserLiveData;
    }

    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    public AuthRepository(Application application){

        this.application = application;
        firebaseUserMutableLiveData = new MutableLiveData<>();
        loggedUserLiveData = new MutableLiveData<>();
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            firebaseUserMutableLiveData.postValue(firebaseAuth.getCurrentUser());
            loggedUserLiveData.postValue(false);
        }

    }

    public void registerUser(String email, String password, User user){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    firebaseUserMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                    db.collection("users").document(firebaseAuth.getUid()).collection(email).add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if(task.isSuccessful()){

                                        Toast.makeText(application, "Success Register", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(application, "Failed add DB", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else {
                    Toast.makeText(application, "Failed Register", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void loginUser(String email, String password){

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    firebaseUserMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                    Toast.makeText(application, "Success Login", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(application, "Failed Login", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void signOut(){
        firebaseAuth.signOut();
        loggedUserLiveData.postValue(true);
    }

}
