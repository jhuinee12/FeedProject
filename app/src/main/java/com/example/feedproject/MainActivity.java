package com.example.feedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button bt_dog, bt_cat;
    public static Context mContext;
    public static String val;

    static public ArrayList<list_item> DataList;
    static public ArrayList<list_item> searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mContext = this;

        DataList = new ArrayList<list_item>();
        searchList = new ArrayList<list_item>();

        if (!isConnected(mContext))
            Toast.makeText(getApplicationContext(), "네트워크가 연결되어 있지 않습니다.", Toast.LENGTH_LONG).show();

        bt_dog = (Button)findViewById(R.id.btnDog);
        bt_cat = (Button)findViewById(R.id.btnCat);

        bt_dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                val = "dog";
                Intent intent = new Intent(getApplicationContext(), DogMainActivity.class);
                startActivity(intent);
            }
        });

        bt_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                val = "cat";
                Intent intent = new Intent(getApplicationContext(), CatMainActivity.class);
                startActivity(intent);
            }
        });
    }

    public static Boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        return ni != null && ni.isConnected();
    }
}