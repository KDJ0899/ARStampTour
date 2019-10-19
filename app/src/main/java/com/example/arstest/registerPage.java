package com.example.arstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class registerPage extends AppCompatActivity {

    Spinner spinnerSi,spinnerGu;
    ArrayAdapter<CharSequence> adspin1, adspin2;
    EditText userId,userPassword,passwordCheck,userName,userBirth,userNumber,year,month,day;
    Button registerBtn;
    RadioButton male,female;
    String userSex,userSi,userGu;
    String selectedCity="";
    String selectedCityDetail="";

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

                }else{

                }
            }
        });
        }
}
