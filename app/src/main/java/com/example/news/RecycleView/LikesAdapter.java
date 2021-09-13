package com.example.news.RecycleView;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.Activity.ClickedHistory;
import com.example.news.Activity.News;
import com.example.news.Activity.Person;
import com.example.news.R;
import com.example.news.litepal.History;
import com.example.news.litepal.Likes;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.litepal.FluentQuery;
import org.litepal.LitePal;

import java.util.List;

public class LikesAdapter extends RecyclerView.Adapter<LikesAdapter.ViewHolder> {

    Context context;
    List<Likes> likes;

    public LikesAdapter(Context context,List<Likes>likes){
        this.context=context;
        this.likes=likes;
    }


    @Override
    public LikesAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.like_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Likes item = likes.get(position);
        holder.title.setText(item.getTitle());
        String imageUrl = item.getThumbnail_pic_s();
        Picasso.with(context).load(imageUrl).into(holder.img);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, News.class);
                intent.putExtra("url",item.getUrl());
                intent.putExtra("author_name",item.getAuthor());
                intent.putExtra("data",item.getDate());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("uniquekey",item.getUniquekey());
                intent.putExtra("pic_1",item.getThumbnail_pic_s());
                context.startActivity(intent);
            }
        });
        holder.deleteLikesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = LitePal.deleteAll(Likes.class,"uniquekey = ?",item.getUniquekey());
                if(flag>0)Toast.makeText(context,"恭喜你，删除成功",Toast.LENGTH_SHORT).show();
                else Toast.makeText(context,"很遗憾，删除失败",Toast.LENGTH_SHORT).show();
            }
        });

    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView title;
        ImageView img;
        Button deleteLikesButton;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            img = itemView.findViewById(R.id.img);
            cardView = itemView.findViewById(R.id.cardview);
            deleteLikesButton = itemView.findViewById(R.id.deleteLikesButton);
        }
    }


    @Override
    public int getItemCount() {
        return likes.size();
    }
}
