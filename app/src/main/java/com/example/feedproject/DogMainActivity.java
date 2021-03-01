package com.example.feedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.*;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import java.util.*;

import static com.example.feedproject.ApiExamSearchShop.DataList;

public class DogMainActivity extends AppCompatActivity {
    private static final String TAG = "DogMainActivity";
    SearchView searchView;
    ListView listview;
    private ProgressBar progressBar;
    private boolean mLockListView = false;
    private boolean lastItemVisibleFlag = false;
    private int page = 0;
    private final int OFFSET = 20;
    ArrayList<list_item> searchList;
    ListViewAdapter adapter;

    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_main);

        mContext = this;

        Log.d(TAG, "열렸어요.");

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

/*        listview.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 1. OnScrollListener.SCROLL_STATE_IDLE : 스크롤이 이동하지 않을때의 이벤트(즉 스크롤이 멈추었을때).
                // 2. lastItemVisibleFlag : 리스트뷰의 마지막 셀의 끝에 스크롤이 이동했을때.
                // 3. mLockListView == false : 데이터 리스트에 다음 데이터를 불러오는 작업이 끝났을때.
                // 1, 2, 3 모두가 true일때 다음 데이터를 불러온다.
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag && mLockListView == false) {
                    // 화면이 바닦에 닿을때 처리
                    // 로딩중을 알리는 프로그레스바를 보인다.
                    progressBar.setVisibility(View.VISIBLE);

                    // 다음 데이터를 불러온다.
                    // 리스트에 다음 데이터를 입력할 동안에 이 메소드가 또 호출되지 않도록 mLockListView 를 true로 설정한다.
                    mLockListView = true;

                    // 다음 20개의 데이터를 불러와서 리스트에 저장한다.
                    for(int i = 0; i < 20; i++){
                        DataList.add(searchList.get(i));
                    }

                    // 1초 뒤 프로그레스바를 감추고 데이터를 갱신하고, 중복 로딩 체크하는 Lock을 했던 mLockListView변수를 풀어준다.
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            page++;
                            adapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            mLockListView = false;
                        }
                    },1000);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // firstVisibleItem : 화면에 보이는 첫번째 리스트의 아이템 번호.
                // visibleItemCount : 화면에 보이는 리스트 아이템의 갯수
                // totalItemCount : 리스트 전체의 총 갯수
                // 리스트의 갯수가 0개 이상이고, 화면에 보이는 맨 하단까지의 아이템 갯수가 총 갯수보다 크거나 같을때.. 즉 리스트의 끝일때. true
                lastItemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
            }
        });*/
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
                String Name = DataList.get(position).getName();
                String Desc = DataList.get(position).getDesc();
                String Image = DataList.get(position).getImage();
                Log.d(TAG, "선택했어요.");
                Intent intent = new Intent(DogMainActivity.this, PopupActivity.class);
                intent.putExtra("name", Name);
                intent.putExtra("desc", Desc);
                intent.putExtra("image", Image);
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

    public SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
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