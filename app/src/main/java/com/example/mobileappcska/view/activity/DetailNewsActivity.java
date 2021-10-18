package com.example.mobileappcska.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappcska.R;
import com.squareup.picasso.Picasso;

import java.time.Month;

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


//
//    public void addCalendar(View view) {
//
//        Intent intent = new Intent(Intent.ACTION_INSERT);
//        intent.setData(CalendarContract.Events.CONTENT_URI);
//        intent.putExtra(CalendarContract.Events.TITLE, "ЦСКА - СПАРТАК");
//        intent.putExtra(CalendarContract.Events.EVENT_LOCATION,"ВЭБ АРЕНА");
//        intent.putExtra(CalendarContract.Events.DESCRIPTION,"Главное дерби страны");
//        intent.putExtra(CalendarContract.Events.ALL_DAY,"true");
//
//        startActivity(intent);

//        if(intent.resolveActivity(getPackageManager()) != null){
//            startActivity(intent);
//        }else {
//            Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
//        }

}