package com.example.arstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class homePage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView localName, localName2, localName3;
    private Button searchBtn,mapBtn,rewardBtn,mypageBtn,profileBtn, tourBtn;
    private ImageView myStampt1, myStampt2, myStampt3;
    public LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

         myStampt1 = findViewById(R.id.myStampTour1);
         myStampt2 = findViewById(R.id.myStampTour2);
         myStampt3 = findViewById(R.id.myStampTour3);

         localName = findViewById(R.id.localName);
         localName2 = findViewById(R.id.localName2);
         localName3 = findViewById(R.id.localName3);

        tourBtn =  findViewById(R.id.tourBtn);

        searchBtn = findViewById(R.id.imageSearch);
        rewardBtn = findViewById(R.id.imageReward);
        mapBtn = findViewById(R.id.map);
        mypageBtn = findViewById(R.id.imageMypage);
        profileBtn = findViewById(R.id.profile);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        //layoutManager = new GridLayoutManager(this, 1);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewB();
        recyclerView.setAdapter(adapter);


        myStampt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),detailPage.class);
                startActivity(intent);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),searchPage.class);
                startActivity(intent);
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),mapPage.class);
                startActivity(intent);
            }
        });

        rewardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),rewardPage.class);
                startActivity(intent);
            }
        });

        mypageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),myPage.class);
                startActivity(intent);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),myPage.class);
                startActivity(intent);
            }
        });

        tourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),stampPage.class);
                startActivity(intent);
            }
        });
    }
}
