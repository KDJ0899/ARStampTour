package com.example.arstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.arstest.server.ServerController;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class landmarkDetail extends AppCompatActivity implements OnMapReadyCallback {

    public Button homeBtn,searchBtn,rewardBtn,mypageBtn,mapBtn,profileBtn;
    static final LatLng gochuk = new LatLng(37.497774,126.866994);
    private  GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String attractionName = "고척스카이돔";
        ContentValues cv = new ContentValues();
        cv.put("from","attraction");
        cv.put("where","name = "+attractionName);

        ServerController serverconn = new ServerController(cv,"getAll");

        setContentView(R.layout.activity_landmark_detail);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapdetail);
        mapFragment.getMapAsync(this);

        homeBtn = findViewById(R.id.home);
        searchBtn = findViewById(R.id.search);
        rewardBtn = findViewById(R.id.button5);
        mypageBtn = findViewById(R.id.user);
        mapBtn = findViewById(R.id.map);
        profileBtn = findViewById(R.id.profile);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),myPage.class);
                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),homePage.class);
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

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),mapPage.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {

        googleMap = map;
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gochuk,16));
    }
}
