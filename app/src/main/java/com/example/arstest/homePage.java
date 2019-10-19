package com.example.arstest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arstest.DTO.localGU;
import com.example.arstest.server.RequestHttpURLConnection;
import com.example.arstest.server.ServerController;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

public class homePage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView localName, localName2, localName3;
    private Button searchBtn,mapBtn,rewardBtn,mypageBtn,profileBtn, tourBtn;
    private ImageView myStampt1, myStampt2, myStampt3;
    public LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    public Context context =this;

    public static JsonArray jsonArray;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContentValues values = new ContentValues();
        values.put("from","local_gu");

        setContentView(R.layout.activity_home_page);

        if(DataStorage.guList==null) {
            NetworkTask networkTask = new NetworkTask("http://10.0.103.96:3000/users", values);
            networkTask.execute();
        }
        else{

            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);

            //layoutManager = new GridLayoutManager(this, 1);
            layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

            recyclerView.setLayoutManager(layoutManager);

            adapter = new RecyclerViewB("Gu",context);
            recyclerView.setAdapter(adapter);
        }

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

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result==null)
                return;

            Gson gson = new Gson();

            localGU[] array = gson.fromJson(result, localGU[].class);
            DataStorage.guList = Arrays.asList(array);

            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);

            //layoutManager = new GridLayoutManager(this, 1);
            layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

            recyclerView.setLayoutManager(layoutManager);

            adapter = new RecyclerViewB("Gu",context);
            recyclerView.setAdapter(adapter);

        }
    }
}
