package com.example.mobileappcska.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.mobileappcska.R;
import com.example.mobileappcska.data.News;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Locale;

public class addNewsActivity extends AppCompatActivity {


    private EditText editTextHeader;
    private EditText editTextAbout;
    private EditText editTextURLToImage;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        editTextHeader = findViewById(R.id.editTextHeaderNews);
        editTextAbout = findViewById(R.id.editTextAboutNews);
        editTextURLToImage = findViewById(R.id.editTextUrlImage);

        db = FirebaseFirestore.getInstance();


    }

    public void addNews(View view) {

        String header = editTextHeader.getText().toString().trim();
        String about = editTextAbout.getText().toString().trim();
        String urlToImage = editTextURLToImage.getText().toString().trim();
        String tempTime = new SimpleDateFormat("yy MM dd").format(Calendar.getInstance().getTime());
        String time = tempTime;
        
        News news = new News(header,about,urlToImage,time);
        db.collection("news").add(news);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}