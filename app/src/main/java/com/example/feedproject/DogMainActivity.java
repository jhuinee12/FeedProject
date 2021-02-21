package com.example.feedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DogMainActivity extends AppCompatActivity {
    private static final String TAG = "DogMainActivity";
    SearchView searchView;

    ArrayList<list_item> DataList = new ArrayList<list_item>();
    ArrayList<list_item> searchList;
    ListViewAdapter adapter;
    MenuItem mSearch;

    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_main);

        mContext = this;

        ListView listview = (ListView) findViewById(R.id.listView);
        adapter = new ListViewAdapter(this,DataList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                String value = "TestPopup \n TestPopup \n TestPopup \n TestPopup \n TestPopup \n TestPopup \n TestPopup \n TestPopup \n TestPopup \n TestPopup \n TestPopup \n TestPopup";
                Log.d(TAG, "선택했어요.");
                Intent intent = new Intent(DogMainActivity.this, PopupActivity.class);
                intent.putExtra("data", value);
                startActivityForResult(intent, 1);
            }
        });

        XmlPullParser_fsk fsk = new XmlPullParser_fsk();

        Log.d(TAG, "열렸어요.");

        StrictMode.enableDefaults();
// <editor-fold desc="XAML파싱">
        StrictMode.enableDefaults();

        boolean inTitle = false, inLink = false, inImage = false, inMallName = false, inMaker = false;
        boolean inBrand = false, inCategory1 = false, inCategory2 = false, inCategory3 = false, inCategory4 = false;

        Log.d(TAG, "트라이로 넘어갈까요?");

        try {
            Thread td = new Thread() {
                public void run() {
                    ApiExamSearchShop api = new ApiExamSearchShop();
                    api.main();
                }
            };
            td.start();

            URL url = new URL(ApiExamSearchShop.apiURL);
            // 검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            //parser.setInput(ApiExamSearchShop.main();)
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();

            Log.d(TAG, "파싱시작합니다.");
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//</editor-fold>
    }
    //<editor-fold desc="리스트뷰 추가">
    public void InitializeData(String title, String desc) {
/*        DataList = new ArrayList<list_item>();
        DataList.add(new list_item("test1","울랄라1"));
        DataList.add(new list_item("tt2","룰루2"));
        DataList.add(new list_item("test3","울랄라3"));
        DataList.add(new list_item("tt4","울랄라4"));
        DataList.add(new list_item("test5","룰루5"));
        DataList.add(new list_item("test6","울랄라6"));
        DataList.add(new list_item("test7","룰루7"));
        DataList.add(new list_item("tt8","울랄라8"));*/

        DataList.add(new list_item(title, desc));

        searchList = new ArrayList<list_item>();
        searchList.addAll(DataList);
    }
//</editor-fold>

    //<editor-fold desc="앱바 보이기">
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_actionbar_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView)menuItem.getActionView();
        searchView.setQueryHint("사료명으로 검색합니다.");
        searchView.setOnQueryTextListener(queryTextListener);

        return super.onCreateOptionsMenu(menu);
    }

    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            DataList.clear();
            if (s.length() == 0)
                DataList.addAll(searchList);
            else {
                for (int i=0; i<searchList.size(); i++)
                {
                    if(searchList.get(i).name.contains(s))
                        DataList.add(searchList.get(i));
                }
            }

            adapter.notifyDataSetChanged();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            DataList.clear();
            if (s.length() == 0)
                DataList.addAll(searchList);
            else {
                for (int i=0; i<searchList.size(); i++)
                {
                    if(searchList.get(i).name.contains(s))
                        DataList.add(searchList.get(i));
                }
            }

            adapter.notifyDataSetChanged();
            return false;
        }
    };
// </editor-fold>
}