package com.example.arstest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.arstest.R;

public class stampPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    public LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_page);

        recyclerView = findViewById(R.id.stamprecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new stampRecyclerView();
        recyclerView.setAdapter(adapter);

    }
}
