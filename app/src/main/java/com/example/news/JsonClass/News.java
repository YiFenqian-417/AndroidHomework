package com.example.news.JsonClass;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("reason")
    @Expose
    private String reason;

    @SerializedName("result")
    @Expose
    private Result result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
