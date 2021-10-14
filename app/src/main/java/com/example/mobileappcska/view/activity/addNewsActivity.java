package com.example.mobileappcska.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileappcska.R;
import com.example.mobileappcska.model.entity.News;
import com.example.mobileappcska.viewmodel.NewsViewModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addNewsActivity extends AppCompatActivity {


    private EditText editTextHeader;
    private EditText editTextAbout;
//    private EditText editTextURLToImage;
    private NewsViewModel viewModel;
    private final int RC_CODE = 404;
    private ImageView imageView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        editTextHeader = findViewById(R.id.editTextHeaderNews);
        editTextAbout = findViewById(R.id.editTextAboutNews);
        imageView = findViewById(R.id.imageViewNews);
//        editTextURLToImage = findViewById(R.id.editTextUrlImage);
        url = "";

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication())).get(NewsViewModel.class);

        viewModel.getUrlImage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String urlImage) {
                url = urlImage;
                Picasso.get().load(url).into(imageView);
                imageView.setVisibility(View.VISIBLE);
                Toast.makeText(getApplication(), "Фото добавлено", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addNews(View view) {

        String header = editTextHeader.getText().toString().trim();
        String about = editTextAbout.getText().toString().trim();
        String tempTime = new SimpleDateFormat("yy MM dd").format(Calendar.getInstance().getTime());
        String time = tempTime;

        if(url.equals("")){
            url = "https://ic.wampi.ru/2021/06/28/tinkoff_rplcbc41b96a9badbb6.jpg";
        }
        
        News news = new News(header,about,url,time);
        viewModel.addNewsList(news);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void addPhoto(View view) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
        startActivityForResult(intent,RC_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_CODE && resultCode == RESULT_OK){
            if (data != null) {
                Uri uri = data.getData();
                viewModel.addImage(uri);
            }
        }
    }
}
