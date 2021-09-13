package com.example.mobileappcska.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileappcska.R;
import com.example.mobileappcska.data.User;
import com.example.mobileappcska.formattext.CurrencyTextWatcherDate;
import com.example.mobileappcska.viewmodel.AuthViewModel;
import com.example.mobileappcska.viewmodel.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextAge;
    private EditText editTextFIO;
    private TextView textViewLog;
    private Button buttonRegister;
    private ProgressBar progressBar;
    private AuthViewModel viewModelAuth;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        editTextEmail = findViewById(R.id.editTextUserLoginReg);
        editTextPassword = findViewById(R.id.editTextPasswordReg);
        editTextAge = findViewById(R.id.editTextAge);
        editTextFIO = findViewById(R.id.editTextName);

        editTextAge.addTextChangedListener(new CurrencyTextWatcherDate());

        textViewLog = findViewById(R.id.textViewLoginFromReg);
        buttonRegister = findViewById(R.id.buttonRegister);
        progressBar = findViewById(R.id.progressBarReg);

        viewModelAuth = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication())).get(AuthViewModel.class);

        userViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication())).get(UserViewModel.class);

        viewModelAuth.getUserData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                Intent intentSuccessReg = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intentSuccessReg);
            }
        });

        textViewLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void backToActivityLogin(View view) {

        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);

    }

    public void RegisterAcc(View view) {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String name = editTextFIO.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Введите ваш Email");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Некорректный Email !");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Введите пароль");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("Пароль должен быть больше 5 символов !");
            editTextPassword.requestFocus();
            return;
        }

        if(age.isEmpty()){
            editTextAge.setError("Введите ваш возраст");
            editTextAge.requestFocus();
        }

        if(name.isEmpty()){
            editTextFIO.setError("Введите ФИО");
            editTextFIO.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        User user = new User(email,age,name,"user");
        viewModelAuth.registerUser(email,password,user);

    }
}