package com.example.mobileappcska.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mobileappcska.adapter.NewsAdapter;
import com.example.mobileappcska.R;
import com.example.mobileappcska.activity.DetailNewsActivity;
import com.example.mobileappcska.activity.addNewsActivity;
import com.example.mobileappcska.data.News;
import com.example.mobileappcska.data.User;
import com.example.mobileappcska.viewmodel.AuthViewModel;
import com.example.mobileappcska.viewmodel.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements NewsAdapter.onNewsListener {

    private List<News> newsList = new ArrayList<>();
    private FloatingActionButton actionButton;
    private FirebaseFirestore db;
    NewsAdapter adapter;
    private UserViewModel userViewModel;
    RecyclerView recyclerView;
    private AuthViewModel viewModelAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        actionButton = view.findViewById(R.id.floatingButtonAddNews);
        recyclerView = view.findViewById(R.id.recyclerViewHome);

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

        db = FirebaseFirestore.getInstance();

        initFloatingButton(view);
        initRecyclerView(view);

        return view;
    }

    public void checkRole(String role){

        if(role.equals("admin")){
            actionButton.setVisibility(View.VISIBLE);
//            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                    if(dy > 0){
//                        actionButton.setVisibility(View.GONE);
//                    } else {
//                        actionButton.setVisibility(View.VISIBLE);
//                    }
//                    super.onScrolled(recyclerView, dx, dy);
//                }
//            });
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
        db.collection("news").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value != null){
                    List<News> newsList = value.toObjects(News.class);
                    adapter.setNews(newsList);
                }
            }
        });

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
