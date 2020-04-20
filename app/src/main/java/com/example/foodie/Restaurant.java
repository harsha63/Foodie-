package com.example.foodie;

public class Restaurant {
    private String name;
    private String ID;
    public Restaurant(String name, String ID)
    {
        this.name = name;
        this.ID = ID;
    }
    public String getName()
    {
        return name;
    }
    public String getID()
    {
        return ID;
    }
}
