package com.example.mobileappcska.view.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileappcska.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.time.Month;
import java.util.Calendar;

public class DetailNewsActivity extends AppCompatActivity {

    private String header;
    private String about;
    private String date;
    private String urlToImage;

    private TextView textViewDate;
    private TextView textViewHeader;
    private ImageView imageViewDetail;
    private TextView textViewAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("header") && intent.hasExtra("about") && intent.hasExtra("date") && intent.hasExtra("url")){
            header = intent.getStringExtra("header");
            about = intent.getStringExtra("about");
            date = intent.getStringExtra("date");
            urlToImage = intent.getStringExtra("url");
        }

        textViewDate = findViewById(R.id.textViewDetailTime);
        textViewHeader = findViewById(R.id.textViewDetailHeader);
        imageViewDetail = findViewById(R.id.imageViewDetail);
        textViewAbout = findViewById(R.id.textViewDetailAbout);


        initViewElements(header,about,date,urlToImage);
    }

    @SuppressLint("NewApi")
    private void initViewElements(String header, String about, String date, String urlToImage) {


        String day = date.substring(6,8);
        int month = Integer.parseInt(date.substring(3,5));


        textViewDate.setText(day + " " +Month.of(month));
        textViewHeader.setText(header);
        Picasso.get().load(urlToImage).into(imageViewDetail);
        textViewAbout.setText(about);

    }
}