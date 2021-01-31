package com.example.feedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class list_item extends AppCompatActivity {

    //public int icon;
    public String name;
    public String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
    }

    public list_item(String name, String desc) {
        //this.icon = icon;
        this.name = name;
        this.desc = desc;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDesc()
    {
        return this.desc;
    }
}