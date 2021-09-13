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

import com.example.news.Activity.News;
import com.example.news.R;
import com.example.news.litepal.History;
import com.example.news.litepal.Likes;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.litepal.FluentQuery;
import org.litepal.LitePal;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    Context context;
    List<History> history;

    public HistoryAdapter(Context context,List<History>history){
        this.context=context;
        this.history=history;
    }


    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        History item = history.get(position);
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
        holder.LikeHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Likes likes = new Likes();
                likes.setUrl(item.getUrl());
                likes.setAuthor(item.getAuthor());
                likes.setTitle(item.getTitle());
                likes.setUniquekey(item.getUniquekey());
                likes.setThumbnail_pic_s(item.getThumbnail_pic_s());
                likes.setCategory(item.getCategory());
                List<Likes> likesList = LitePal.select("uniquekey").where("uniquekey = ?",item.getUniquekey()).find(Likes.class);
                if(likesList.isEmpty()){
                    boolean flag=likes.save();
                    if(flag){
                        Toast.makeText(context,"收藏成功",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context,"收藏失败",Toast.LENGTH_SHORT).show();
                    }
                }
                else Toast.makeText(context,"您已经收藏过该内容,无需再收藏",Toast.LENGTH_SHORT).show();
            }
        });
        holder.deleteHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int flag = LitePal.deleteAll(History.class,"uniquekey = ?",item.getUniquekey());
                if(flag>0)Toast.makeText(context,"恭喜你，删除成功",Toast.LENGTH_SHORT).show();
                else Toast.makeText(context,"很遗憾，删除失败",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView title;
        ImageView img;
        Button deleteHistoryButton;
        Button LikeHistoryButton;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            img = itemView.findViewById(R.id.img);
            cardView = itemView.findViewById(R.id.cardview);
            deleteHistoryButton =itemView.findViewById(R.id.deleteHistoryButton);
            LikeHistoryButton = itemView.findViewById(R.id.likesHistoryButton);
        }
    }


    @Override
    public int getItemCount() {
        return history.size();
    }
}
