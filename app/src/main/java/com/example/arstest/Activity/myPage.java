package com.example.arstest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arstest.AR.MapActivity;
import com.example.arstest.DataStorage;
import com.example.arstest.R;

public class myPage extends AppCompatActivity {

    public Button homeBtn,searchBtn,rewardBtn,mypageBtn,mapBtn;
    public TextView doIngStampTour,userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        doIngStampTour = findViewById(R.id.textView12);
        homeBtn = findViewById(R.id.homebtn);
        searchBtn = findViewById(R.id.searchbtn);
        rewardBtn = findViewById(R.id.button5);
        mypageBtn = findViewById(R.id.user);
        mapBtn = findViewById(R.id.map);
        userName = findViewById(R.id.userName);

        userName.setText(DataStorage.userDetail.getName().replace("\"","")+" 님");

        doIngStampTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),stampPage.class);
                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), homePage.class);
                startActivity(intent);
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), searchPage.class);
                startActivity(intent);
            }
        });
        rewardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), rewardPage.class);
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

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });
    }
}
