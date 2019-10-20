package com.example.arstest;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.arstest.DTO.localGU;
import com.example.arstest.server.RequestHttpURLConnection;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class rewardPage extends AppCompatActivity implements View.OnClickListener
{
    private ListView m_oListView = null;
    private Button homeBtn,searchBtn,userBtn,mapBtn,mypageBtn, btn_search;
    ArrayAdapter<CharSequence> adspin1, adspin2;
    String selectedCategory="";
    String selectedBrand="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        homeBtn = findViewById(R.id.imageHome);
        searchBtn = findViewById(R.id.imageSearch);
        userBtn = findViewById(R.id.imageMypage);
        mapBtn = findViewById(R.id.map);
        mypageBtn = findViewById(R.id.profile);
        btn_search = findViewById(R.id.btn_search);

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), searchPage.class);
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

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),myPage.class);
                startActivity(intent);
            }
        });
        ContentValues cv = new ContentValues();
        cv.put("from","REWARD");
        cv.put("where","brand="+1);

        NetworkTask networkTask = new NetworkTask(DataStorage.ipAdress+"/users", cv);
        networkTask.execute();

        // 데이터 생성 ============================
        ArrayList<ItemData> oData = new ArrayList<>();
        for (int i=0; i<30; ++i)
        {
            ItemData oItem = new ItemData();
            oItem.strTitle = "데이터 " + (i+1);
            oItem.onClickListener = this;
            oData.add(oItem);
        }

        // ListView 생성 ===============================
        m_oListView = (ListView)findViewById(R.id.list_cards);
        ListAdapter oAdapter = new ListAdapter(oData);
        m_oListView.setAdapter(oAdapter);


        final Spinner category = (Spinner)findViewById(R.id.category);
        final Spinner brand = (Spinner)findViewById(R.id.brand);

        adspin1 = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_dropdown_item);

        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        category.setAdapter(adspin1);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (adspin1.getItem(i).equals("식품")) {

                    selectedCategory = "식품"; //출력을 위한 값 입력
                    adspin2 = ArrayAdapter.createFromResource(rewardPage.this, R.array.food_brand, android.R.layout.simple_spinner_dropdown_item);

                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    brand.setAdapter(adspin2);
                    brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        //두번째 spinner 선택 이벤트를 정의합니다.
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedBrand = adspin2.getItem(i).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else if (adspin1.getItem(i).equals("패션")) {

                    selectedCategory = "패션";
                    adspin2 = ArrayAdapter.createFromResource(rewardPage.this, R.array.food_brand, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    brand.setAdapter(adspin2);
                    brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedBrand = adspin2.getItem(i).toString();
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

                Toast.makeText(rewardPage.this, "선택된 카테고리는 " + selectedCategory + ", 선택된 브랜드는 " + selectedBrand+" 입니다.", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onClick(View v)
    {
        View oParentView = (View)v.getParent();
        TextView rewardTitle = (TextView) oParentView.findViewById(R.id.rewardTitle);
        TextView stampAmt = (TextView) oParentView.findViewById(R.id.stampAmt);
        String position = (String) oParentView.getTag();

        AlertDialog.Builder oDialog = new AlertDialog.Builder(this,
                android.R.style.Theme_DeviceDefault_Light_Dialog);

        oDialog.setMessage(rewardTitle.getText()+" 상품을 구매하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Dialog","예");
                        Toast.makeText(getApplicationContext(),"상품 구매 완료!",Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("Dialog", "아니오");
                Toast.makeText(getApplicationContext(), "상품 구매 취소", Toast.LENGTH_LONG).show();
            }
        }).setCancelable(false).show();
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

        }
    }


}
