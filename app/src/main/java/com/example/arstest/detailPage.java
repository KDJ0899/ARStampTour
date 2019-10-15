package com.example.arstest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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



public class detailPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textView1,textView2,textView3,stampCnt;
    private ImageView imageView;
    public LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        final AlertDialog.Builder oDialog = new AlertDialog.Builder(this,android.R.style.Theme_DeviceDefault_Light_Dialog);
        final Button applyBtn = (Button)findViewById(R.id.applyBtn);

        recyclerView = findViewById(R.id.recyclerView);
        textView1 = findViewById(R.id.textView21);
        textView2 = findViewById(R.id.textView22);
        textView3 = findViewById(R.id.textView23);
        stampCnt = findViewById(R.id.stampCnt2);
        imageView = findViewById(R.id.imageView5);


        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewA();
        recyclerView.setAdapter(adapter);

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
}
