package com.example.vehicle.Api.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class RideDto {


    public int Ride_ID;

    public int User_ID;


    public Date StartTime;

    public boolean Status;

    public int getRide_ID() {
        return Ride_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public boolean isStatus() {
        return Status;
    }



}
