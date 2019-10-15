package com.example.arstest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class rewardPage extends AppCompatActivity implements View.OnClickListener
{
    private ListView m_oListView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        // 데이터 생성 ============================
        String[] strDate = {"2017-01-03", "1965-02-23", "2016-04-13", "2010-01-01", "2017-06-20",
                "2012-07-08", "1980-04-14", "2016-09-26", "2014-10-11", "2010-12-24"};
        int nDatCnt=0;
        ArrayList<ItemData> oData = new ArrayList<>();
        for (int i=0; i<1000; ++i)
        {
            ItemData oItem = new ItemData();
            oItem.strTitle = "데이터 " + (i+1);
            oItem.onClickListener = this;
            oData.add(oItem);
            if (nDatCnt >= strDate.length) nDatCnt = 0;
        }

        // ListView 생성 ===============================
        m_oListView = (ListView)findViewById(R.id.list_cards);
        ListAdapter oAdapter = new ListAdapter(oData);
        m_oListView.setAdapter(oAdapter);
    }


    @Override
    public void onClick(View v)
    {
        View oParentView = (View)v.getParent();
        TextView oTextTitle = (TextView) oParentView.findViewById(R.id.textTitle);
        String position = (String) oParentView.getTag();

        AlertDialog.Builder oDialog = new AlertDialog.Builder(this,
                android.R.style.Theme_DeviceDefault_Light_Dialog);

        oDialog.setMessage(oTextTitle.getText()+" 상품을 구매하시겠습니까?")
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
}
