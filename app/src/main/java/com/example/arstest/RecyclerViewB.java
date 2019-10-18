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

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewB extends RecyclerView.Adapter<RecyclerViewB.ViewHolder> {

    public List<String> name;
    public List<String> Image;
    public List<String> Id;
    public JsonArray jsonArray;
    Context context;

    public String table;

    RecyclerViewB(JsonArray jsonArray,String table,Context context){
        this.jsonArray=jsonArray;
        this.table = table;
        this.context = context;
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
            Id=new ArrayList<>();

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        for(int i=0;i<jsonArray.size(); i++){
            name.add(jsonArray.get(i).getAsJsonObject().get("Name").toString());
            Image.add(jsonArray.get(i).getAsJsonObject().get("Image").toString());
            Id.add(jsonArray.get(i).getAsJsonObject().get(table+"_Id").toString());
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(name.get(position));
        holder.imageView.setBackgroundResource(context.getResources().getIdentifier("guro_main2", "drawable", context.getPackageName()));

        출처: https://kanais2.tistory.com/195 [飛上]);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(table=="Gu") {
                    Intent intent = new Intent(context,detailPage.class);
                    intent.putExtra("id", Id.get(position));
                    context.startActivity( intent .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(table=="Gu") {
                    Intent intent = new Intent(context,detailPage.class);
                    intent.putExtra("id", Id.get(position));
                    context.startActivity( intent .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return jsonArray.size();
    }
}