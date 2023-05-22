package com.example.project;

import com.google.gson.annotations.SerializedName;
public class Mountain {
    @SerializedName("name")
    private String name;
    @SerializedName("location")
    private String location;
    @SerializedName("cost")
    private int cost;

    public Mountain(){
        name="Saknar namn";
        location="Saknar plats";
        cost=-1;
    }

    public Mountain(String n, String l, int h) {
        name = n;
        location = l;
        cost = h;
    }

    public String info() {
        String tmp = new String();
        tmp += name + " is located in mountain range " + location + " and reaches " + cost + "m above sea level.";
        return tmp;
    }
    public void setName(String n){

        name=n;
    }

    public String getName(){
        return name;
    }

    public String getLocation() {return location;
    }

    public int getCost() {return cost;
    }
}

