package com.example.arstest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arstest.server.RequestHttpURLConnection;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MainActivity extends AppCompatActivity {

    //푸시알림 위한 선언
    NotificationManager notificationManager;
    PendingIntent pintent;
    Boolean canLogin = false;
    public static TextView userId,pw;

    JsonArray jsonArray;
    JsonObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registerbBtn = (Button)findViewById(R.id.button2);
        Button loginBtn = (Button)findViewById(R.id.loginButton);
        Button mapBtn = (Button)findViewById(R.id.map);
        Button homeBtn = (Button)findViewById(R.id.home);
        userId = findViewById(R.id.editText);
        pw = findViewById(R.id.editText3);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid = userId.getText().toString();
                String password = pw.getText().toString();

                ContentValues cv = new ContentValues();
                cv.put("from","user");
                cv.put("where","ID='"+userid+"' and Password='"+password+"';");

                NetworkTask networkTask = new NetworkTask("http://10.0.103.96:3000/login", cv);
                networkTask.execute();

            }
        });

        registerbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),registerPage.class);
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

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),homePage.class);
                startActivity(intent);
            }
        });

        //시간 지연
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                //지연시키길 원하는 밀리초 뒤에 동작
                alam();
            }
        }, 5000 );

        //알람채널 생성
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("channel description"); notificationChannel.enableLights(true); notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true); notificationChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notificationManager.createNotificationChannel(notificationChannel); }


    }

    //알람 설정
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void alam(){
        pintent = PendingIntent.getActivity(this,0,new Intent(getApplicationContext(),MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(this)

                .setSmallIcon(R.drawable.ic_launcher_background) // 아이콘 설정하지 않으면 오류남

                .setDefaults(Notification.DEFAULT_ALL)

                .setContentTitle("알림 제목") // 제목 설정

                .setContentText("알림 내용") // 내용 설정

                .setTicker("한줄 출력") // 상태바에 표시될 한줄 출력

                .setAutoCancel(true)

                .setContentIntent(pintent);

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
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
                canLogin = false;
            else
                canLogin = true;

            JsonParser jsonParser = new JsonParser();
            jsonArray = (JsonArray)jsonParser.parse(result);

            for(int i=0; i<jsonArray.size(); i++){
                jsonObject = jsonArray.get(i).getAsJsonObject();
                Log.i("json",jsonObject.get("Name").toString());
            }

            if(canLogin) {
                Intent intent = new Intent(getApplicationContext(), homePage.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"아이디 혹은 비밀번호가 틀렸습니다.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
