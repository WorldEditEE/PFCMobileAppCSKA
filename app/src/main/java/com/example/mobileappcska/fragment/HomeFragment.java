package com.example.mobileappcska.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    NewsAdapter adapter;
    private String role;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        actionButton = view.findViewById(R.id.floatingButtonAddNews);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        initRoleModel(view);
        initFloatingButton(view);
        initRecyclerView(view);

        return view;
    }

    private void initRoleModel(View view) {

        if(user != null){
            db.collection("users").document(user.getUid()).collection(user.getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot document : task.getResult()){
                            role = document.getString("role");
                            if (role != null && role.equals("user")) {
                                actionButton.setVisibility(View.GONE);
                            }else if(role != null && role.equals("admin")){
                                actionButton.setVisibility(View.VISIBLE);
                            }else {
                                actionButton.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            });
        }else {
            actionButton.setVisibility(View.GONE);
        }


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
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewHome);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NewsAdapter(newsList,getActivity(),this);
        recyclerView.setAdapter(adapter);
        if(user != null){
            db.collection("users").document(user.getUid()).collection(user.getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot document : task.getResult()){
                            role = document.getString("role");
                            if(role.equals("admin")){
                                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                    @Override
                                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                        if(dy > 0){
                                            actionButton.setVisibility(View.GONE);
                                        } else {
                                            actionButton.setVisibility(View.VISIBLE);
                                        }
                                        super.onScrolled(recyclerView, dx, dy);
                                    }
                                });
                            }
                        }
                    }
                }
            });
        }

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
