package com.example.feedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

public class list_item extends AppCompatActivity {

    public String image;
    public String name;
    public String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
    }

    public list_item(String image, String name, String desc) {
        this.image = image;
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

    public String getImage()
    {
        return this.image;
    }
}