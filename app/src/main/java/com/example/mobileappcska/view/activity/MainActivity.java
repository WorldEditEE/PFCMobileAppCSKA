 package com.example.mobileappcska.view.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.mobileappcska.data.Club;
import com.example.mobileappcska.view.fragment.HomeFragment;
import com.example.mobileappcska.R;
import com.example.mobileappcska.view.fragment.ResultFragment;
import com.example.mobileappcska.view.fragment.TablesFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {


    MeowBottomNavigation bottomNavigation;
    private FirebaseAuth mAuth;
    private static Document document;
    private static Thread secThread;
    private static Runnable runnable;
    private static String title;
    private static String result;
    private static List<Club> listClubs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        mAuth = FirebaseAuth.getInstance();
        bottomNavigation = findViewById(R.id.bottom_navigation);
        listClubs = new ArrayList<>();

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_result_matches));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_tickets));


        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                Intent intent = null;

                switch (item.getId()){
                    case  1:
                        fragment = new ResultFragment();
                        break;
                    case 2:
                        fragment = new HomeFragment();
                        break;
                    case 3:
                        fragment = new TablesFragment();
                        break;
                }

                loadFragment(fragment);
            }
        });

        bottomNavigation.show(2,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, ""+ item.getId(), Toast.LENGTH_SHORT).show();
            }
        });


        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "You Reslected " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();


    }

    public void openUserProfile(View view) {

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }


    }

}