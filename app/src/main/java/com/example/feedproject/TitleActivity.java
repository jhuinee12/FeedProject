package com.example.feedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class TitleActivity extends AppCompatActivity {

    Button btn_Dog, btn_Cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        btn_Dog = (Button)findViewById(R.id.btnDog);
    }
}