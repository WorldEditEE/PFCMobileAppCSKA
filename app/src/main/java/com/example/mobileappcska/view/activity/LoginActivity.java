package com.example.mobileappcska.view.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobileappcska.R;
import com.example.mobileappcska.viewmodel.AuthViewModel;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewRegister;
    private TextView textViewRememberPassword;
    private AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        editTextEmail = findViewById(R.id.editTextUserLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        textViewRegister = findViewById(R.id.textViewRegisterAcc);
        textViewRememberPassword = findViewById(R.id.textViewRememberPassword);

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication())).get(AuthViewModel.class);

        viewModel.getUserData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null){
                    Intent intentSuccessLogin = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intentSuccessLogin);
                }
            }
        });

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToReg = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentToReg);
            }
        });

    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void LogInAcc(View view) {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Введите email !");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Некорректный email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Введите пароль");
            editTextPassword.requestFocus();
            return;
        }

        viewModel.signInUser(email, password);

    }
}