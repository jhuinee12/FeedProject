package com.example.feedproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.parser.Tag;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.BreakIterator;
import java.util.ArrayList;

public class DogMainActivity extends AppCompatActivity {
    private static final String TAG = "DogMainActivity";
    SearchView searchView;

    ArrayList<list_item> DataList;
    ArrayList<list_item> searchList;
    ListViewAdapter adapter;
    MenuItem mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_main);

        this.InitializeData();

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

        TextView tvLink = (TextView)findViewById(R.id.tv_link); //파싱된 결과확인!
        TextView tvImage = (TextView)findViewById(R.id.tv_image); //파싱된 결과확인!
        TextView tvMallName = (TextView)findViewById(R.id.tv_mallName); //파싱된 결과확인!
        TextView tvCategory2 = (TextView)findViewById(R.id.tv_category2); //파싱된 결과확인!

        boolean inTitle = false, inLink = false, inImage = false, inMallName = false, inMaker = false;
        boolean inBrand = false, inCategory1 = false, inCategory2 = false, inCategory3 = false, inCategory4 = false;

        Log.d(TAG, "트라이로 넘어갈까요?");

        try {
            Thread thread = new Thread() {
                public void run() {
                    ApiExamSearchShop api = new ApiExamSearchShop();
                    api.main();
                }
            };
            thread.start();

            URL url = new URL("https://openapi.naver.com/v1/search/blog.xml?query=" + ApiExamSearchShop.text);
            // 검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            //parser.setInput(ApiExamSearchShop.main();)
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();

            Log.d(TAG, "파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG: //parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("TITLE")) { //title 만나면 내용을 받을수 있게 하자
                            inTitle = true;
                            Log.d(TAG, "Title.");
                        }
                        if (parser.getName().equals("LINK")) { //address 만나면 내용을 받을수 있게 하자
                            inLink = true;
                            Log.d(TAG, "link.");
                        }
                        if (parser.getName().equals("SAMPLING_IMAGE")) { //mapx 만나면 내용을 받을수 있게 하자
                            inImage = true;
                        }
                        if (parser.getName().equals("SAMPLING_MALLNAME")) { //mapx 만나면 내용을 받을수 있게 하자
                            inMallName = true;
                        }
                        if (parser.getName().equals("SAMPLING_MAKER")) { //mapy 만나면 내용을 받을수 있게 하자
                            inMaker = true;
                        }
                        if (parser.getName().equals("SAMPLING_BRAND")) { //mapy 만나면 내용을 받을수 있게 하자
                            inBrand = true;
                        }
                        if (parser.getName().equals("CATEGORY1")) { //mapy 만나면 내용을 받을수 있게 하자
                            inCategory1 = true;
                        }
                        if (parser.getName().equals("CATEGORY2")) { //mapy 만나면 내용을 받을수 있게 하자
                            inCategory2 = true;
                        }
                        if (parser.getName().equals("CATEGORY3")) { //mapy 만나면 내용을 받을수 있게 하자
                            inCategory3 = true;
                        }
                        if (parser.getName().equals("CATEGORY4")) { //mapy 만나면 내용을 받을수 있게 하자
                            inCategory4 = true;
                        }
                        //if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                        //  status1.setText(status1.getText()+"에러");
                        //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        break;
                    case XmlPullParser.TEXT:
                        if (inTitle) { //isTitle이 true일 때 태그의 내용을 저장.
                            fsk.title = parser.getText();
                            inTitle = false;
                        }
                        if (inLink) { //isAddress이 true일 때 태그의 내용을 저장.
                            fsk.link = parser.getText();
                            inLink = false;
                        }
                        if (inImage) { //isMapx이 true일 때 태그의 내용을 저장.
                            fsk.image = parser.getText();
                            inImage = false;
                        }
                        if (inMallName) { //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.mallName = parser.getText();
                            inMallName = false;
                        }
                        if (inMaker) { //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.maker = parser.getText();
                            inMaker = false;
                        }
                        if (inBrand) { //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.brand = parser.getText();
                            inBrand = false;
                        }
                        if (inCategory1) { //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.category1 = parser.getText();
                            inCategory1 = false;
                        }
                        if (inCategory2) { //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.category2 = parser.getText();
                            inCategory2 = false;
                        }
                        if (inCategory3) { //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.category3 = parser.getText();
                            inCategory3 = false;
                        }
                        if (inCategory4) { //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.category4 = parser.getText();
                            inCategory4 = false;
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("TITLE")) {
                            status1.setText("번호 : " + fsk.title + "\n 링크: " + fsk.link + "\n 이미지 : " + fsk.image
                                    + "\n 쇼핑몰상호 : " + fsk.mallName + "\n 제조사 : " + fsk.maker + "\n 브랜드명 : " + fsk.brand
                                    + "\n 카테고리대분류 : " + fsk.category1 + "\n 카테고리중분류 : " + fsk.category2 + "\n 카테고리소분류 : " + fsk.category3
                                    + "\n 카테고리세분류 : " + fsk.category4 + "\n");
                            tvLink.setText(fsk.link);
                            tvImage.setText(fsk.image);
                            tvMallName.setText(fsk.mallName);
                            tvCategory2.setText(fsk.category2);
                            inTitle = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i(TAG, fsk.Title +"번호 : "+ fsk.Title +"\n 링크: "+ fsk.link +"\n 이미지 : " + fsk.image
                +"\n 쇼핑몰상호 : " + fsk.mallName +  "\n 제조사 : " + fsk.maker + "\n 브랜드명 : " + fsk.brand
                +"\n 카테고리대분류 : " + fsk.category1  + "\n 카테고리중분류 : " + fsk.category2 + "\n 카테고리소분류 : " + fsk.category3
                +"\n 카테고리세분류 : " + fsk.category4  +"\n" +"\n");
        //</editor-fold>

    }
    //<editor-fold desc="리스트뷰 추가">
    private void InitializeData() {
        DataList = new ArrayList<list_item>();
        DataList.add(new list_item("test1","울랄라1"));
        DataList.add(new list_item("tt2","룰루2"));
        DataList.add(new list_item("test3","울랄라3"));
        DataList.add(new list_item("tt4","울랄라4"));
        DataList.add(new list_item("test5","룰루5"));
        DataList.add(new list_item("test6","울랄라6"));
        DataList.add(new list_item("test7","룰루7"));
        DataList.add(new list_item("tt8","울랄라8"));

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

    //<editor-fold desc="액션바 처리">
/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_search :
//                Log.d(TAG, "서치 액션을 선택했습니다.");
//                // Todo
//                return true;
//            //...
//            //...
//            default:
//                return super.onOptionsItemSelected(item);
//        }

        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
//</editor-fold>
}