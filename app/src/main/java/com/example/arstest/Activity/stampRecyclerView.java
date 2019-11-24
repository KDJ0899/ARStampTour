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

public class stampRecyclerView extends RecyclerView.Adapter<stampRecyclerView.ViewHolder> {

    public static Context context;

    private String[] title = {"구로구", "마포구", "영등포구", "종로구",
            "제목5", "제목6", "제목7", "제목8", "제목9", "제목10", };

    private String[] content = {"30 / 14", "24 / 3", "20 / 10", "14 / 3",
            "내용5", "내용6", "내용7", "내용8", "내용9", "내용10", };

    public class ViewHolder extends  RecyclerView.ViewHolder {
        public TextView textView,textView1;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.textView27);
            this.textView1 = view.findViewById(R.id.textView28);
            this.imageView = view.findViewById(R.id.imageView6);
        }
    }

    @Override
    public stampRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stampitem, parent, false);
        stampRecyclerView.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final stampRecyclerView.ViewHolder holder, final int position) {
        holder.textView.setText(title[position]);
        holder.textView1.setText(content[position]);

                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = String.valueOf(holder.textView.getText());
                Intent intent = new Intent(context, detailPage.class);
                context.startActivity(intent);
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