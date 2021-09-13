package com.example.news.JsonClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("uniquekey")
    @Expose
    private String uniquekey;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("author_name")
    @Expose
    private String author_name;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("thumbnail_pic_s")
    @Expose
    private String thumbnail_pic_s;


    @SerializedName("thumbnail_pic_s02")
    @Expose
    private String thumbnail_pic_s02;

    @SerializedName("thumbnail_pic_s03")
    @Expose
    private String thumbnail_pic_s03;

    @SerializedName("is_content")
    @Expose
    private String is_content;

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getThumbnail_pic_s02() {
        return thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s03() {
        return thumbnail_pic_s03;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }

    public String getIs_content() {
        return is_content;
    }

    public void setIs_content(String is_content) {
        this.is_content = is_content;
    }
}

