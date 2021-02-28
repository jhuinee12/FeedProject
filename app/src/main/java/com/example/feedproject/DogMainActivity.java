package com.example.feedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.*;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;
import static com.example.feedproject.ApiExamSearchShop.DataList;

public class DogMainActivity extends AppCompatActivity {
    private static final String TAG = "DogMainActivity";
    SearchView searchView;
    ListView listview;

    //ArrayList<list_item> DataList = new ArrayList<list_item>();
    ArrayList<list_item> searchList;
    ListViewAdapter adapter;

    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_main);

        //ApiExamSearchShop apiExamSearchShop = new ApiExamSearchShop();
        mContext = this;

        Log.d(TAG, "열렸어요.");
// <editor-fold desc="XAML파싱">

        Log.d(TAG, "트라이로 넘어갈까요?");
        try {
            Thread td = new Thread() {
                public void run() {
                    ApiExamSearchShop api = new ApiExamSearchShop();
                    api.main();
                }
            };
            td.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
//</editor-fold>
    }
    //<editor-fold desc="리스트뷰 추가">
    public void InitializeData() {
        searchList = new ArrayList<list_item>();
        searchList.addAll(DataList);
        ListViewUpdate();
    }

    public void ListViewUpdate() {
        Log.d(TAG, "리스트뷰 뿌릴게요");
        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(new Runnable() {
            public void run(){
                adapter = new ListViewAdapter(mContext, DataList);
                listview.setAdapter(adapter);
            }
        },0);
        Log.d(TAG, "리스트뷰 뿌렸어요");

        listview = (ListView) findViewById(R.id.listView);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                String value = parent.getAdapter().getItem(position).toString();
                Log.d(TAG, "선택했어요.");
                Intent intent = new Intent(DogMainActivity.this, PopupActivity.class);
                intent.putExtra("data", value);
                startActivityForResult(intent, 1);
            }
        });
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
                    {
                        Log.d(TAG, DataList.toString());
                        DataList.add(searchList.get(i));
                    }
                }
            }

            //InitializeData();
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
                    {
                        Log.d(TAG, DataList.toString());
                        DataList.add(searchList.get(i));
                    }
                }
            }

            //InitializeData();
            adapter.notifyDataSetChanged();
            //ListViewUpdate();
            return false;
        }
    };
// </editor-fold>
}