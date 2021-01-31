package com.example.feedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.os.AsyncTask;
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
    String html = "http://apis.data.go.kr/B553748/FeedCompanyListService/getFeedCompanyList?ServiceKey=R1H3SR71dxA1fXPYN4oF0maMnpTYZx8O6XIkKhuVvaIWYbAw4Jpq4sjzJ%2BYn%2Bkp%2FBDbzLSzrnjDaL0G1i6P%2FYg%3D%3D"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_main);
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