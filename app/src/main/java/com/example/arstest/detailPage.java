package com.example.arstest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arstest.DTO.attraction;
import com.example.arstest.server.RequestHttpURLConnection;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class detailPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    public Button homeBtn,searchBtn,rewardBtn,mypageBtn,mapBtn,profileBtn;
    private TextView textView1,textView2,textView3, textCurrenStamp, textTotalStamp,textGu,textSi,textInfo;
    private ImageView imageView;
    public LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    Context context;
    int id,localSi;
    String info,name;
    List<attraction> attraction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        Intent intent = getIntent();

        id=intent.getExtras().getInt("id");
        localSi=intent.getExtras().getInt("si");
        name=intent.getExtras().getString("name");
        info=intent.getExtras().getString("info");


        textCurrenStamp = findViewById(R.id.stampCnt2);

        imageView = findViewById(R.id.imageView5);

        homeBtn = findViewById(R.id.home);
        searchBtn = findViewById(R.id.search);
        rewardBtn = findViewById(R.id.button5);
        mypageBtn = findViewById(R.id.user);
        mapBtn = findViewById(R.id.map1);
        profileBtn = findViewById(R.id.profile);

        final AlertDialog.Builder oDialog = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog);
        final Button applyBtn = (Button) findViewById(R.id.applyBtn);

        recyclerView = findViewById(R.id.recyclerView);
        textView1 = findViewById(R.id.textView21);
        textView2 = findViewById(R.id.textView22);
        textView3 = findViewById(R.id.textView23);

        textGu = findViewById(R.id.gu);
        textGu.setText(name);

        textSi = findViewById(R.id.si);
        textSi.setText(localSi + "");

        textInfo = findViewById(R.id.info);
        textInfo.setText(info);
        textTotalStamp = findViewById(R.id.stampCnt);

        if(DataStorage.guMap==null) {
            ContentValues values = new ContentValues();
            values.put("select", "*");
            values.put("from", "attraction");

            NetworkTask networkTask = new NetworkTask("http://10.0.103.96:3000/users", values);
            networkTask.execute();
        }
        else {
            attraction = DataStorage.guMap.get(id);
            if(attraction == null)
                textTotalStamp.setText(0+"");
            else
                textTotalStamp.setText(attraction.size()+"");


            layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerView.setLayoutManager(layoutManager);

            adapter = new RecyclerViewC("",context,attraction);
            recyclerView.setAdapter(adapter);
        }

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

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oDialog.setMessage("서울특별시 구로구 투어에 참여하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Dialog","예");
                                Toast.makeText(getApplicationContext(),"예",Toast.LENGTH_LONG).show();
                                applyBtn.setText("투어 진행 중");
                                applyBtn.setBackgroundResource(R.drawable.readibtn_background);
                                textView1.setTextColor(Color.parseColor("#000000"));
                                textView2.setTextColor(Color.parseColor("#000000"));
                                textView3.setTextColor(Color.parseColor("#000000"));
                                textCurrenStamp.setTextColor(Color.parseColor("#F44336"));
                                imageView.setBackgroundResource(R.drawable.stamp);
                                oDialog.setMessage("투어 참여 완료!                          즐겁게 투어에 참여해보세요")
                                        .setPositiveButton("투어 계속 보기", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(getApplicationContext(),"투어계속보기",Toast.LENGTH_LONG).show();
                                            }
                                        }).setNegativeButton("홈으로 돌아가기", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(getApplicationContext(),"홈으로 돌아가기",Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(),homePage.class);
                                        startActivity(intent);
                                    }
                                }).setCancelable(false).show();
                            }
                        }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Dialog", "아니오");
                        Toast.makeText(getApplicationContext(), "아니오", Toast.LENGTH_LONG).show();
                    }
                }).setCancelable(false).show();
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
            if (result == null)
                return;

            Gson gson = new Gson();
            List<attraction> list;

            attraction[] array = gson.fromJson(result, attraction[].class);
            DataStorage.attractions = Arrays.asList(array);
            int localGu;
            DataStorage.guMap = new HashMap<>();

            for(int i=0; i<array.length; i++){
                localGu = array[i].getLOCAL_GU();
                if(DataStorage.guMap.containsKey(localGu)) {
                    list=DataStorage.guMap.get(localGu);
                    list.add(array[i]);
                    DataStorage.guMap.put(localGu,list);
                }
                else{
                    list=new ArrayList<>();
                    list.add(array[i]);
                    DataStorage.guMap.put(localGu,list);
                }
            }
            attraction = DataStorage.guMap.get(id);

            if(attraction == null)
                textTotalStamp.setText(0+"");
            else {
                textTotalStamp.setText(attraction.size()+"");
            }


            layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerView.setLayoutManager(layoutManager);

            adapter = new RecyclerViewC("",context,attraction);
            recyclerView.setAdapter(adapter);

        }
    }
}
