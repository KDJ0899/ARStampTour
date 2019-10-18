package com.example.arstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arstest.server.RequestHttpURLConnection;
import com.example.arstest.server.ServerController;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.nio.charset.Charset;

public class landmarkDetail extends AppCompatActivity implements OnMapReadyCallback {

    public Button homeBtn,searchBtn,rewardBtn,mypageBtn,mapBtn,profileBtn;
    public ImageView mainImage;
    private  GoogleMap googleMap;
    public static JsonArray jsonArray;
    public JsonObject jsonObject;
    public TextView attraction,gu,si,addre,inf;
    String attractionName,guName,siName,address,info,imageAddress;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attractionName = "고척스카이돔";
        ContentValues cv = new ContentValues();
        cv.put("where",attractionName);

        Log.i("--------------------------","통신전");
        NetworkTask networkTask = new NetworkTask("http://10.0.103.96:3000/attraction", cv);
        networkTask.execute();
        Log.i("--------------------------","통신후");

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
                Intent intent = new Intent(getApplicationContext(), searchPage.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {

        googleMap = map;
        MarkerOptions makerOptions = new MarkerOptions();
        makerOptions
                .position(latLng)
                .title(attractionName);

        // 마커를 생성한다.
        googleMap.addMarker(makerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
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

            JsonParser jsonParser = new JsonParser();
            jsonArray = (JsonArray)jsonParser.parse(result);

            //동적으로 변경될 것들
            mainImage = findViewById(R.id.imageView2);
            attraction = findViewById(R.id.attractionName);
            gu = findViewById(R.id.guName);
            si = findViewById(R.id.kind);
            addre = findViewById(R.id.address);
            inf = findViewById(R.id.textView10);

            for(int i=0; i<jsonArray.size(); i++){
                jsonObject = jsonArray.get(i).getAsJsonObject();
                info = jsonObject.get("info").toString().replace("\"","");
                address = jsonObject.get("Address").toString().replace("\"","");
                imageAddress = jsonObject.get("Image").toString().replace("\"","");
                guName = jsonObject.get("guName").toString().replace("\"","");
                siName = jsonObject.get("siName").toString().replace("\"","");
                String lt = jsonObject.get("Latitude").toString().replace("\"","");
                String lg = jsonObject.get("Longitude").toString().replace("\"","");
                latLng = new LatLng(Double.valueOf(lt).doubleValue(),Double.valueOf(lg).doubleValue());
                Log.i("-----------받은 결과 : ","info: "+info+" address: "+address+" imageAddress: "+imageAddress+" latlng: "+latLng+" guName: "+guName+" siName: "+siName);
                Log.i("json",jsonObject.get("Name").toString());
            }

            attraction.setText(attractionName);
            gu.setText(guName);
            si.setText("소속 : "+siName);
            addre.setText(address);
            inf.setText(info);
            mainImage.setBackgroundResource(getApplicationContext().getResources().getIdentifier(imageAddress,"drawable",getApplicationContext().getPackageName()));
        }
    }
}
