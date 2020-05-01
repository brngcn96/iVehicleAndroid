package com.example.vehicle.Api.Model;

import com.google.gson.annotations.SerializedName;

public class AuthorizedDto {

    public int ID;

    public int ParentID;
    @SerializedName("body")
    public String Name;

    public String Email;

    public String ImageUrl;

    public String Role;

    public String Token;

    public double Credit;

    public int getID() {
        return ID;
    }

    public int getParentID() {
        return ParentID;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getRole() {
        return Role;
    }

    public String getToken() {
        return Token;
    }

    public double getCredit() {
        return Credit;
    }
}
