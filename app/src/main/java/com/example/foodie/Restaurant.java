package com.example.foodie;

public class Restaurant {
    private int mImage;
    private String mText1, mText2;
    public Restaurant( int image, String text1, String text2){
        mImage = image;
        mText1 = text1;
        mText2 = text2;
    }
    public int getImage(){
        return mImage;
    }
    public String getName(){
        return mText1;
    }
    public String getDescription(){
        return mText2;
    }
    public void changeDescrip(String text){
        mText2=text;
    }
}
