package com.example.arstest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class searchPage extends AppCompatActivity {

    private Button back;
    private Button homeBtn,rewardBtn,userBtn,mypageBtn,searchBtn,backBtn;
    LinearLayout layout;
    private TextView landmarkInformation;
    private RecyclerView recyclerView;
    public LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    ArrayAdapter<CharSequence> adspin1, adspin2;
    String selectedCity="";
    String selectedCityDetail="";
    Context context = this;
    int btnCount=0;
    private GoogleMap mMap;
    double longitude, latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        homeBtn = findViewById(R.id.imageHome);
        rewardBtn = findViewById(R.id.imageReward);
        userBtn = findViewById(R.id.imageMypage);
        mypageBtn = findViewById(R.id.profile);
        searchBtn = findViewById(R.id.imageSearch);
        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
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
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),homePage.class);
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
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),myPage.class);
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

        landmarkInformation = (TextView)findViewById(R.id.landmarkInformation);
        landmarkInformation.setText("지역을 선택하세요.");
        landmarkInformation.setGravity(Gravity.CENTER_HORIZONTAL);


        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewA();

        /*LinearLayout layout = (LinearLayout)findViewById(R.id.linearlayout);
        Button btn = new Button(this);
        btn.setText("투어 참여하기");
        layout.addView(btn);*/

        final Spinner spin1 = (Spinner)findViewById(R.id.localSi);
        final Spinner spin2 = (Spinner)findViewById(R.id.localGu);
        Button btn_search = (Button)findViewById(R.id.btn_search);

        adspin1 = ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_dropdown_item);

        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(adspin1);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (adspin1.getItem(i).equals("서울특별시")) {

                    selectedCity = "서울특별시"; //출력을 위한 값 입력
                    adspin2 = ArrayAdapter.createFromResource(searchPage.this, R.array.city_seoul, android.R.layout.simple_spinner_dropdown_item);

                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        //두번째 spinner 선택 이벤트를 정의합니다.
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedCityDetail = adspin2.getItem(i).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else if (adspin1.getItem(i).equals("부산광역시")) {

                    selectedCity = "부산광역시";
                    adspin2 = ArrayAdapter.createFromResource(searchPage.this, R.array.city_busan, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedCityDetail = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Toast.makeText(searchPage.this, "선택된 도시는 " + selectedCity + " " + selectedCityDetail+" 입니다.", Toast.LENGTH_SHORT).show();
                landmarkInformation.setText("선택 지역 " + selectedCity + " "+  selectedCityDetail +" 의 "+"획득 가능 스탬프 수는 15개 입니다.");
                recyclerView.setAdapter(adapter);
                Button btn = new Button(context);
                btn.setText("투어 신청하기");
              //  btn.setGravity(Gravity.CENTER_VERTICAL);
                layout = findViewById(R.id.linearlayout);

               /* ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT);
                layout.setLayoutParams(params);*/

                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                layout.setLayoutParams(param);



                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams
                        (ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                btn.setLayoutParams(lp);
                if(btnCount==0){

                    layout.addView(btn);}
                btnCount++;
            }
        });

    }

}


