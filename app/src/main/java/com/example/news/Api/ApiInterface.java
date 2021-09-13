package com.example.news.Api;

import com.example.news.JsonClass.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("index")
    Call<News> getHeadLines(
        @Query("type")String type,
        @Query("key")String key
    );
}
