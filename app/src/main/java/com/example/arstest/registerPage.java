package com.example.arstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arstest.server.RequestHttpURLConnection;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class registerPage extends AppCompatActivity {

    public static JsonArray jsonArray;
    public JsonObject jsonObject;
    Spinner spinnerSi,spinnerGu;
    ArrayAdapter<CharSequence> adspin1, adspin2;
    EditText userId,userPassword,passwordCheck,userName,userBirth,userNumber,year,month,day;
    Button registerBtn;
    RadioButton male,female;
    String userSex,userSi,userGu;
    String selectedCity="";
    String selectedCityDetail="";
    Boolean canRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        spinnerSi = findViewById(R.id.spinnerSi);
        spinnerGu = findViewById(R.id.spinnerGu);
        userId = findViewById(R.id.userId);
        userPassword = findViewById(R.id.userId);
        passwordCheck = findViewById(R.id.checkPassword);
        userName = findViewById(R.id.userId);
        userBirth = findViewById(R.id.userId);
        userNumber = findViewById(R.id.userId);
        year = findViewById(R.id.userId);
        month = findViewById(R.id.userId);
        day = findViewById(R.id.userId);
        male = findViewById(R.id.radioMale);
        female = findViewById(R.id.radioFemale);
        registerBtn = findViewById(R.id.registerBtn);

        adspin1 = ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_dropdown_item);

        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSi.setAdapter(adspin1);

        spinnerSi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adspin1.getItem(i).equals("서울특별시")) {

                    selectedCity = "서울특별시"; //출력을 위한 값 입력
                    adspin2 = ArrayAdapter.createFromResource(registerPage.this, R.array.city_seoul, android.R.layout.simple_spinner_dropdown_item);

                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerGu.setAdapter(adspin2);
                    spinnerGu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    adspin2 = ArrayAdapter.createFromResource(registerPage.this, R.array.city_busan, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerGu.setAdapter(adspin2);
                    spinnerGu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userPassword.getText().toString().equals(passwordCheck.getText().toString())){

//                    String si = selectedCity;
//                    String gu = selectedCityDetail;
//                    ContentValues idCheck = new ContentValues();
//                    idCheck.put("test","test");
//                    idCheck.put("where","ID="+userId.getText().toString()+" and Password="+userPassword.getText().toString());
//                    Log.i("json","********************************* * ID="+userId.getText().toString()+" and Password="+userPassword.getText().toString());
//                    idCheck.put("idWhere","local_si.name = "+si+" and local_gu.Name = "+gu);
//                    idCheck.put("siName",si);
//                    idCheck.put("guName",gu);
//
//                    NetworkTask networkTask = new NetworkTask(DataStorage.ipAdress+"/idCheck",idCheck);
//                    networkTask.execute();

                    if(male.isChecked()){
                        userSex="Male";
                    }else if(female.isChecked()){
                        userSex="Female";
                    }
                    ContentValues cv = new ContentValues();
//                    cv.put("ID",userId.getText().toString());
//                    cv.put("Password",userPassword.getText().toString());
//                    cv.put("Name",userName.getText().toString());
//                    cv.put("year",year.getText().toString());
//                    cv.put("month",month.getText().toString());
//                    cv.put("day",day.getText().toString());
//                    cv.put("Phone_No",userNumber.getText().toString());
//                    cv.put("Sex",userSex);
//                    cv.put("LOCAL_SI",selectedCity);
//                    cv.put("LOCAL_GU",selectedCityDetail);
                    cv.put("table","user");
                    String userBirth = year+"-"+month+"-"+day;
                    cv.put("values","("+userId.getText().toString()+","+userPassword.getText().toString()+","+userName.getText().toString()+","+userBirth+","+userNumber.getText().toString()+","+userSex+","+"1,"+"1)");

                    NetworkTask networkTask = new NetworkTask(DataStorage.ipAdress+"/register",cv);
                    networkTask.execute();
                }else{
                    Toast.makeText(getApplicationContext(),"비밀번호가 같지 않습니다.",Toast.LENGTH_LONG).show();
                }
            }
        });
        }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {
            Log.i("json","------------------------------네트워크 인자설정---------------------------------------------------------------");
            this.url = url;
            this.values = values;
        }


        @Override
        protected String doInBackground(Void... voids) {
            Log.i("json","---------------------------------------백그라운드------------------------------------------------------");
            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

        }
    }
}
