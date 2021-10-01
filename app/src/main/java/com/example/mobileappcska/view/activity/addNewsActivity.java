package com.example.mobileappcska.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileappcska.R;
import com.example.mobileappcska.model.entity.News;
import com.example.mobileappcska.viewmodel.NewsViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addNewsActivity extends AppCompatActivity {


    private EditText editTextHeader;
    private EditText editTextAbout;
    private EditText editTextURLToImage;
    private NewsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        editTextHeader = findViewById(R.id.editTextHeaderNews);
        editTextAbout = findViewById(R.id.editTextAboutNews);
        editTextURLToImage = findViewById(R.id.editTextUrlImage);

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication())).get(NewsViewModel.class);

    }

    public void addNews(View view) {

        String header = editTextHeader.getText().toString().trim();
        String about = editTextAbout.getText().toString().trim();
        String urlToImage = editTextURLToImage.getText().toString().trim();
        String tempTime = new SimpleDateFormat("yy MM dd").format(Calendar.getInstance().getTime());
        String time = tempTime;

        if(urlToImage.equals("")){
            urlToImage = "https://ic.wampi.ru/2021/06/28/tinkoff_rplcbc41b96a9badbb6.jpg";
        }
        
        News news = new News(header,about,urlToImage,time);
        viewModel.addNewsList(news);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}