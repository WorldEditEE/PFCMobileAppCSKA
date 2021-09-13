package com.example.mobileappcska.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobileappcska.view.adapter.NewsAdapter;
import com.example.mobileappcska.R;
import com.example.mobileappcska.view.activity.DetailNewsActivity;
import com.example.mobileappcska.view.activity.addNewsActivity;
import com.example.mobileappcska.data.News;
import com.example.mobileappcska.data.User;
import com.example.mobileappcska.viewmodel.AuthViewModel;
import com.example.mobileappcska.viewmodel.NewsViewModel;
import com.example.mobileappcska.viewmodel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements NewsAdapter.onNewsListener {

    private List<News> newsList = new ArrayList<>();
    private FloatingActionButton actionButton;
    NewsAdapter adapter;
    private UserViewModel userViewModel;
    RecyclerView recyclerView;
    private AuthViewModel viewModelAuth;
    private NewsViewModel viewModelNews;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        actionButton = view.findViewById(R.id.floatingButtonAddNews);
        recyclerView = view.findViewById(R.id.recyclerViewHome);

        viewModelNews = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication())).get(NewsViewModel.class);

        viewModelNews.getNewsList().observe(getActivity(), new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                adapter.setNews(news);
            }
        });

        viewModelAuth = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication())).get(AuthViewModel.class);

        viewModelAuth.getUserData().observe(getActivity(), new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null){
                    initInfoProfile(firebaseUser.getEmail());
                }
            }
        });

        userViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication())).get(UserViewModel.class);

        userViewModel.getUserMutableLiveData().observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user != null){
                    String role = user.getRole();
                    checkRole(role);
                }
            }
        });

        initFloatingButton(view);
        initRecyclerView(view);

        return view;
    }


    public void checkRole(String role){

        if(role.equals("admin")){
            actionButton.setVisibility(View.VISIBLE);
        }else {
            actionButton.setVisibility(View.GONE);
        }

    }

    public void initInfoProfile(String email){

        userViewModel.getUserInfo(email);

    }

    private void initFloatingButton(View view){

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), addNewsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerView(View view){
        
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NewsAdapter(newsList,getActivity(),this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        viewModelNews.initNewsList();
    }

    @Override
    public void onNewsClick(News news) {

        String header = news.getHeading();
        String about = news.getAbout();
        String date = news.getDate();
        String urlToImage = news.getImageURL();

        Intent intentDetail = new Intent(getActivity(), DetailNewsActivity.class);
        intentDetail.putExtra("header", header);
        intentDetail.putExtra("about", about);
        intentDetail.putExtra("date", date);
        intentDetail.putExtra("url", urlToImage);
        startActivity(intentDetail);
    }
}
