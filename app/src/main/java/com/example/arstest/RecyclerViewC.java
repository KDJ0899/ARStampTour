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

import com.example.arstest.DTO.attraction;
import com.example.arstest.DTO.localGU;
import com.example.arstest.DataStorage;
import com.example.arstest.R;
import com.example.arstest.landmarkDetail;

import java.util.List;

public class RecyclerViewC extends RecyclerView.Adapter<RecyclerViewC.ViewHolder> {
    Context context;
    public String table;
    List<attraction> list;

    private String[] title;

    private String[] content;

    public RecyclerViewC(String table, Context context,List<attraction> list) {
        this.table = table;
        this.context = context;

        this.list = list;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextView textView2;
        public ImageView imageView;
        String imageID = "";

        public ViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.textView);
            this.textView2 = view.findViewById(R.id.textView2);
            this.imageView = view.findViewById(R.id.imageView);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewC.ViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getName());
        holder.textView2.setText(list.get(position).getInfo());
        holder.imageView.setBackgroundResource(R.drawable.guro_attraction00);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), position + "번 째 이미지!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, landmarkDetail.class);
                intent.putExtra("id",list.get(position).getAtt_Id());
                context.startActivity(intent);
            }
        });

        holder.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, landmarkDetail.class);
                intent.putExtra("id",list.get(position).getAtt_Id());
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, landmarkDetail.class);
                intent.putExtra("id",list.get(position).getAtt_Id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (list!=null)?list.size():0;
    }
}
