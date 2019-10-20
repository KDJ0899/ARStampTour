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
    Boolean reRegister = false;
    Boolean canRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        spinnerSi = findViewById(R.id.spinnerSi);
        spinnerGu = findViewById(R.id.spinnerGu);
        userId = findViewById(R.id.userId);
        userPassword = findViewById(R.id.userPassword);
        passwordCheck = findViewById(R.id.checkPassword);
        userName = findViewById(R.id.userName);
        userNumber = findViewById(R.id.number);
        year = findViewById(R.id.year);
        month = findViewById(R.id.month);
        day = findViewById(R.id.day);
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

                    if(male.isChecked()){
                        userSex="Male";
                    }else if(female.isChecked()){
                        userSex="Female";
                    }
                    String userBirth = year.getText().toString()+"-"+month.getText().toString()+"-"+day.getText().toString();

                    ContentValues val = new ContentValues();
                    val.put("table","user");
                    val.put("id",userId.getText().toString());
                    val.put("password",userPassword.getText().toString());
                    val.put("name",userName.getText().toString());
                    val.put("birthday",userBirth);
                    val.put("phone",userNumber.getText().toString());
                    val.put("sex",userSex);
                    val.put("si",selectedCity);
                    val.put("gu",selectedCityDetail);
                    val.put("data","('"+userId.getText().toString()+"','"+userPassword.getText().toString()+"','"+userName.getText().toString()+"','"+userBirth+"','"+userNumber.getText().toString()+"','"+userSex+"',");
                    NetworkTask networkTask = new NetworkTask(DataStorage.ipAdress+"/register",val);
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
            Log.i("json","result = "+result);
            if(result.equals("reRegister")) {
                Toast.makeText(getApplicationContext(), "중복된 아이디입니다.", Toast.LENGTH_LONG).show();
                reRegister = true;
            }
            else if(result.equals("success!")) {
                reRegister = false;
                Intent intent = new Intent(getApplicationContext(),myPage.class);
                startActivity(intent);
            }
            Log.i("json","reRegister = "+reRegister);
        }
    }
}
