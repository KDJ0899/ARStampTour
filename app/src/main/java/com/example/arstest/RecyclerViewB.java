package com.example.arstest;

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

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewB extends RecyclerView.Adapter<RecyclerViewB.ViewHolder> {

    public List<String> name;
    public List<String> Image;
    public JsonArray jsonArray;

    RecyclerViewB(JsonArray jsonArray){
        this.jsonArray=jsonArray;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.textView);
            this.imageView = view.findViewById(R.id.imageViewLocalGu);
            name=new ArrayList<>();
            Image=new ArrayList<>();

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        for(int i=0;i<jsonArray.size(); i++){
            name.add(jsonArray.get(i).getAsJsonObject().get("Name").toString());
            Image.add(jsonArray.get(i).getAsJsonObject().get("Image").toString());
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(name.get(position));
        holder.imageView.setBackgroundResource(R.drawable.guro_main2);


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), position+"번 째 이미지!", Toast.LENGTH_SHORT).show();
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), position+"번 째!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return jsonArray.size();
    }
}