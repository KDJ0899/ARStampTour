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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arstest.server.RequestHttpURLConnection;
import com.example.arstest.server.ServerController;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;


public class detailPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    public Button homeBtn,searchBtn,rewardBtn,mypageBtn,mapBtn,profileBtn;
    private TextView textView1,textView2,textView3,stampCnt;
    private ImageView imageView;
    public LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    JsonArray jsonArray;
    Context context;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        Intent intent = getIntent();

        id=intent.getExtras().getInt("id");
        ContentValues values = new ContentValues();
        values.put("select", "*");
        values.put("from","local_gu");
        values.put("where","GU_Id = '"+id+"'");

        NetworkTask networkTask = new NetworkTask("http://10.0.102.44:3000/users", values);
        networkTask.execute();

        final AlertDialog.Builder oDialog = new AlertDialog.Builder(this,android.R.style.Theme_DeviceDefault_Light_Dialog);
        final Button applyBtn = (Button)findViewById(R.id.applyBtn);

        recyclerView = findViewById(R.id.recyclerView);
        textView1 = findViewById(R.id.textView21);
        textView2 = findViewById(R.id.textView22);
        textView3 = findViewById(R.id.textView23);
        stampCnt = findViewById(R.id.stampCnt2);
        imageView = findViewById(R.id.imageView5);

        homeBtn = findViewById(R.id.home);
        searchBtn = findViewById(R.id.search);
        rewardBtn = findViewById(R.id.button5);
        mypageBtn = findViewById(R.id.user);
        mapBtn = findViewById(R.id.map1);
        profileBtn = findViewById(R.id.profile);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewA();
        recyclerView.setAdapter(adapter);

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
                                stampCnt.setTextColor(Color.parseColor("#F44336"));
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

            JsonParser jsonParser = new JsonParser();
            jsonArray = (JsonArray) jsonParser.parse(result);

            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);

            //layoutManager = new GridLayoutManager(this, 1);
            layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


            recyclerView.setLayoutManager(layoutManager);

            adapter = new RecyclerViewB(jsonArray, "Gu", context,result);
            recyclerView.setAdapter(adapter);
        }
    }
}
