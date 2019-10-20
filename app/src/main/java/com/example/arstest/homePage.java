package com.example.arstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arstest.DTO.attraction;
import com.example.arstest.DTO.localGU;
import com.example.arstest.server.RequestHttpURLConnection;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class homePage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView localName, localName2, localName3,recomandText;
    private Button searchBtn,mapBtn,rewardBtn,mypageBtn,profileBtn, tourBtn;
    private ImageView myStampt1, myStampt2, myStampt3,recomandImage;
    public LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    public Context context =this;
    private int num =0,randomIndex;
    Random random;

    public static JsonArray jsonArray;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContentValues values = new ContentValues();
        values.put("from","LOCAL_GU");

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

        recomandImage=findViewById(R.id.imageViewRecommend);
        recomandText = findViewById(R.id.RecommendlocalName);


        if(DataStorage.guList==null) {
            NetworkTask networkTask = new NetworkTask(DataStorage.ipAdress+"/users", values);
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

            random = new Random();
            randomIndex = random.nextInt(DataStorage.guList.size());

            Log.i("ddddddddddddddddddd",""+randomIndex);

            recomandImage.setBackgroundResource(getResources().getIdentifier(DataStorage.guList.get(randomIndex).getMainAttraction(), "drawable", context.getPackageName()));
            recomandText.setText(DataStorage.guList.get(randomIndex).getName()+" - "+DataStorage.guList.get(randomIndex).getInfo());
        }





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
            Log.i("gg",array[1].getMainAttraction()+"");
            if(array[1].getMainAttraction()!=null){
                values = new ContentValues();
                values.put("from","ATTRACTION");

                DataStorage.guList = Arrays.asList(array);

                recyclerView = findViewById(R.id.recycler_view);
                recyclerView.setHasFixedSize(true);

                //layoutManager = new GridLayoutManager(this, 1);
                layoutManager = new LinearLayoutManager(context);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                recyclerView.setLayoutManager(layoutManager);

                adapter = new RecyclerViewB("Gu", context);
                recyclerView.setAdapter(adapter);

                random = new Random();
                randomIndex = random.nextInt(DataStorage.guList.size());

                recomandImage.setBackgroundResource(getResources().getIdentifier(DataStorage.guList.get(randomIndex).getMainAttraction(), "drawable", context.getPackageName()));
                recomandText.setText(DataStorage.guList.get(randomIndex).getName()+" - "+DataStorage.guList.get(randomIndex).getInfo());



                NetworkTask networkTask = new NetworkTask(DataStorage.ipAdress+"/users", values);
                networkTask.execute();
            }
            else {
                attraction[] array2 = gson.fromJson(result,attraction[].class);
                DataStorage.attractions = Arrays.asList(array2);

                DataStorage.guMap = new HashMap<>();
                int localGu;
                List<attraction> list;


                for(int i=0; i<array.length; i++){
                    localGu = array2[i].getLOCAL_GU();
                    if(DataStorage.guMap.containsKey(localGu)) {
                        list=DataStorage.guMap.get(localGu);
                        list.add(array2[i]);
                        DataStorage.guMap.put(localGu,list);
                    }
                    else{
                        list=new ArrayList<>();
                        list.add(array2[i]);
                        DataStorage.guMap.put(localGu,list);
                    }
                }
            }

        }
    }
}
