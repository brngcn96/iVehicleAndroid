package com.example.vehicle.Api.Model;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int userId;
    private int id;

    private String tittle;
    @SerializedName("body")
    private String text;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public String getText() {
        return text;
    }

}