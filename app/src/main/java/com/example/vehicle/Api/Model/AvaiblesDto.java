package com.example.vehicle.Api.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AvaiblesDto
{

    @SerializedName("body")
    public ArrayList<Integer> avaibles;


    public AvaiblesDto(ArrayList<Integer> avaibleVehicleIDs)
    {
        this.avaibles = avaibleVehicleIDs;
    }
}