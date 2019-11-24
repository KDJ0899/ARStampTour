package com.example.arstest.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.arstest.R;

public class RecyclerViewA extends RecyclerView.Adapter<RecyclerViewA.ViewHolder> {

    public static Context context;

    private String[] title = {"고척스카이돔", "제목2", "제목3", "제목4",
            "제목5", "제목6", "제목7", "제목8", "제목9", "제목10", };

    private String[] content = {"서울특별시 구로구 경인로 430", "내용2", "내용3", "내용4",
            "내용5", "내용6", "내용7", "내용8", "내용9", "내용10", };
    public RecyclerViewA(){

    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        public TextView textView;
        public TextView textView2;
        public ImageView imageView;
        String imageID="";

        public ViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.textView);
            this.textView2 = view.findViewById(R.id.textView2);
            this.imageView = view.findViewById(R.id.imageView);

        }
    }

    @Override
    public RecyclerViewA.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        RecyclerViewA.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewA.ViewHolder holder, final int position) {
        holder.textView.setText(title[position]);
        holder.textView2.setText(content[position]);
        holder.imageView.setBackgroundResource(R.drawable.guro_attraction00);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, landmarkDetail.class);
                context.startActivity(intent);
            }
        });

        holder.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.length;
    }
}