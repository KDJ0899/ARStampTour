package com.example.arstest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.arstest.DTO.localGU;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewB extends RecyclerView.Adapter<RecyclerViewB.ViewHolder> {

    public  List<localGU> list;
    Context context;
    public String table;

    RecyclerViewB(String table,Context context){
        this.table = table;
        this.context = context;

        list = DataStorage.guList;

    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.textView);
            this.imageView = view.findViewById(R.id.imageViewLocalGu);;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getName());
        holder.imageView.setBackgroundResource(context.getResources().getIdentifier("guro_main", "drawable", context.getPackageName()));


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(table=="Gu") {
                    Intent intent = new Intent(context,detailPage.class);
                    intent.putExtra("id", list.get(position).getGu_Id());
                    intent.putExtra("name", list.get(position).getName());
                    intent.putExtra("si", list.get(position).getLocal_Si());
                    intent.putExtra("info", list.get(position).getInfo());
                    intent.putExtra("image",list.get(position).getImage());
                    context.startActivity( intent .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(table=="Gu") {
                    Intent intent = new Intent(context,detailPage.class);
                    intent.putExtra("id", list.get(position).getGu_Id());
                    intent.putExtra("name", list.get(position).getName());
                    intent.putExtra("si", list.get(position).getLocal_Si());
                    intent.putExtra("info", list.get(position).getInfo());
                    context.startActivity( intent .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}