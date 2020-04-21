package com.example.foodie;

public class Order {
    private String id;
    private String rest, uName, uPhone;
    private Boolean assigned;
    private double amount;

    public Order(String id, String rest, String uName, String uPhone, double amount, Boolean assigned){//*, Boolean assigned*/) {
        this.id = id;
        this.rest = rest;
        this.uName = uName;
        this.uPhone = uPhone;
        this.amount = amount;
         this.assigned = this.assigned;
    }

    public String getId() {
        return id;
    }

    public String getRest() {
        return rest;
    }

    public String getuName() {
        return uName;
    }

    public String getuPhone() {
        return uPhone;
    }

    public Boolean getAssigned(){return assigned;}

    public double getAmount() {
        return amount;
    }
};
