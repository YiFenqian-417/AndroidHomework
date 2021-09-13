package com.example.news.RecycleView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.Activity.News;
import com.example.news.R;
import com.example.news.JsonClass.Data;
import com.example.news.litepal.History;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.litepal.LitePal;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<Data> data;

    public Adapter(Context context,List<Data> data) {
        this.context = context;
        this.data =  data;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data item = data.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvSource.setText(item.getCategory());
        holder.tvData.setText(item.getDate());

        String imageUrl = item.getThumbnail_pic_s();
        Picasso.with(context).load(imageUrl).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, News.class);
                History history =new History();
                history.setUrl(item.getUrl());
                history.setAuthor(item.getAuthor_name());
                history.setTitle(item.getTitle());
                history.setUniquekey(item.getUniquekey());
                history.setThumbnail_pic_s(item.getThumbnail_pic_s());
                history.setCategory(item.getCategory());
                history.save();

                intent.putExtra("url",item.getUrl());
                intent.putExtra("author_name",item.getAuthor_name());
                intent.putExtra("data",item.getDate());
                intent.putExtra("content",item.getIs_content());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("uniquekey",item.getUniquekey());
                intent.putExtra("pic_1",item.getThumbnail_pic_s());
                intent.putExtra("pic_2",item.getThumbnail_pic_s02());
                intent.putExtra("pic_3",item.getThumbnail_pic_s03());
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvSource,tvData;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvTitle= itemView.findViewById(R.id.tvTittle);
            tvSource=itemView.findViewById(R.id.tvsource);
            tvData=itemView.findViewById(R.id.tvDate);
            imageView=itemView.findViewById(R.id.img);
            cardView=itemView.findViewById(R.id.cardview);

        }
    }

}
