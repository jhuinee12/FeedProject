package com.example.feedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bt_dog, bt_cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_dog = (Button)findViewById(R.id.btnDog);
        bt_cat = (Button)findViewById(R.id.btnCat);

        bt_dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DogMainActivity.class);
                startActivity(intent);
            }
        });

        bt_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), XmlPullParser_example.class);
                startActivity(intent);
            }
        });
    }
}