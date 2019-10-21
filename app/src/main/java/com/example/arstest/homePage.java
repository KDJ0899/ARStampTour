package com.example.arstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arstest.AR.MapActivity;
import com.example.arstest.DTO.RegisterTour;
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
    List<ImageView> ImageList;
    List<TextView> TextList;
    Random random;

    public static JsonArray jsonArray;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContentValues values = new ContentValues();
        values.put("from","LOCAL_GU");

        setContentView(R.layout.activity_home_page);

        ImageList = new ArrayList<>();
        myStampt1 = findViewById(R.id.myStampTour1);
        myStampt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickBtn(0);
            }
        });
        ImageList.add(myStampt1);

        myStampt2 = findViewById(R.id.myStampTour2);
        myStampt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickBtn(1);
            }
        });
        ImageList.add(myStampt2);

        myStampt3 = findViewById(R.id.myStampTour3);
        myStampt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickBtn(2);
            }
        });
        ImageList.add(myStampt3);

        TextList = new ArrayList<>();
        localName = findViewById(R.id.localName);
        TextList.add(localName);
        localName2 = findViewById(R.id.localName2);
        TextList.add(localName2);
        localName3 = findViewById(R.id.localName3);
        TextList.add(localName3);

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


            values = new ContentValues();
            values.put("from","REGISTER_TOUR");
            values.put("where","User_Id = "+DataStorage.userDetail.getUser_Id());
            NetworkTask networkTask = new NetworkTask(DataStorage.ipAdress+"/users", values);
            networkTask.execute();

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

            recomandImage.setBackgroundResource(getResources().getIdentifier(DataStorage.guList.get(randomIndex).getMainAttraction(), "drawable", context.getPackageName()));
            recomandText.setText(DataStorage.guList.get(randomIndex).getName()+" - "+DataStorage.guList.get(randomIndex).getInfo());

            recomandImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),detailPage.class);
                    List<localGU> list = DataStorage.guList;
                    intent.putExtra("id", list.get(randomIndex).getGu_Id());
                    intent.putExtra("name", list.get(randomIndex).getName());
                    intent.putExtra("si", list.get(randomIndex).getLocal_Si());
                    intent.putExtra("info", list.get(randomIndex).getInfo());
                    intent.putExtra("image",list.get(randomIndex).getImage());
                    startActivity(intent);
                }
            });
            if(DataStorage.registerTours!=null) {
                for (int i = 0; i < 3; i++) {
                    if (DataStorage.registerTours.size() == i) {
                        break;
                    }
                    RegisterTour gu = DataStorage.registerTours.get(i);
                    for (int j = 0; j < DataStorage.guList.size(); j++) {
                        if (DataStorage.guList.get(j).getGu_Id() == gu.getGu_Id()) {
                            ImageList.get(i).setBackgroundResource(getResources().getIdentifier(DataStorage.guList.get(j).getMainAttraction(), "drawable", context.getPackageName()));
                            TextList.get(i).setText(DataStorage.guList.get(j).getName());
                        }
                    }
                }
            }
        }


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
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
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
            if(array.length==0)
                return;
            if(array[0].getMainAttraction()!=null){
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

                recomandImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),detailPage.class);
                        List<localGU> list = DataStorage.guList;
                        intent.putExtra("id", list.get(randomIndex).getGu_Id());
                        intent.putExtra("name", list.get(randomIndex).getName());
                        intent.putExtra("si", list.get(randomIndex).getLocal_Si());
                        intent.putExtra("info", list.get(randomIndex).getInfo());
                        intent.putExtra("image",list.get(randomIndex).getImage());
                        startActivity(intent);
                    }
                });



                NetworkTask networkTask = new NetworkTask(DataStorage.ipAdress+"/users", values);
                networkTask.execute();
            }
            else {
                attraction[] array2 = gson.fromJson(result,attraction[].class);
                if(array2[0].getAddress()!=null) {
                    DataStorage.attractions = Arrays.asList(array2);

                    DataStorage.guMap = new HashMap<>();
                    int localGu;
                    List<attraction> list;


                    for (int i = 0; i < array.length; i++) {
                        localGu = array2[i].getLOCAL_GU();
                        if (DataStorage.guMap.containsKey(localGu)) {
                            list = DataStorage.guMap.get(localGu);
                            list.add(array2[i]);
                            DataStorage.guMap.put(localGu, list);
                        } else {
                            list = new ArrayList<>();
                            list.add(array2[i]);
                            DataStorage.guMap.put(localGu, list);
                        }
                    }

                    values = new ContentValues();
                    values.put("from","REGISTER_TOUR");
                    values.put("where","User_Id = "+DataStorage.userDetail.getUser_Id());
                    NetworkTask networkTask = new NetworkTask(DataStorage.ipAdress+"/users", values);
                    networkTask.execute();

                }

                else{

                    RegisterTour[] array3 = gson.fromJson(result,RegisterTour[].class);
                    DataStorage.registerTours = Arrays.asList(array3);


                    for(int i=0; i<3;i++){
                        if(DataStorage.registerTours.size()==i) {
                            break;
                        }
                        RegisterTour gu = DataStorage.registerTours.get(i);
                        for(int j=0; j<DataStorage.guList.size(); j++){
                            if(DataStorage.guList.get(j).getGu_Id() == gu.getGu_Id()) {
                                ImageList.get(i).setBackgroundResource(getResources().getIdentifier(DataStorage.guList.get(j).getMainAttraction(), "drawable", context.getPackageName()));
                                TextList.get(i).setText(DataStorage.guList.get(j).getName());
                            }
                        }
                    }

                }
            }

        }
    }
    public void ClickBtn(int num){
        if(DataStorage.registerTours.size()<=num)
            return;
        Log.i("########################3",num+"");
        int id = DataStorage.registerTours.get(num).getGu_Id();
        Intent intent = new Intent(getApplicationContext(),detailPage.class);
        List<localGU> list = DataStorage.guList;
        for(int i=0; i<list.size();i++) {
            if(id==list.get(i).getGu_Id()) {
                id = i;
                break;
            }
        }
        intent.putExtra("id", list.get(id).getGu_Id());
        intent.putExtra("name", list.get(id).getName());
        intent.putExtra("si", list.get(id).getLocal_Si());
        intent.putExtra("info", list.get(id).getInfo());
        intent.putExtra("image",list.get(id).getImage());
        startActivity(intent);
    }

}
