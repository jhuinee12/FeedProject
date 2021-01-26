package com.example.feedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

public class DogMainActivity extends AppCompatActivity {
    private static final String TAG = "DogMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_main);

        Log.d(TAG, "열렸어요.");

        StrictMode.enableDefaults();

    }

    // 앱바 보이기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);

        return true;
    }

/*    // 액션바 처리
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search :
                Log.d(TAG, "서치 액션을 선택했습니다.");
                // Todo
                return true;
            //...
            //...
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}