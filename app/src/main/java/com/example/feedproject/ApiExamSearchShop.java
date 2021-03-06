package com.example.feedproject;

import android.content.Context;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static com.example.feedproject.MainActivity.DataList;
import static com.example.feedproject.MainActivity.searchList;

public class ApiExamSearchShop extends Thread{

    static public String clientId = "Cr4xj10LUdJFUYvg587h"; //애플리케이션 클라이언트 아이디값"
    static public String clientSecret = "NoptEf1cw7"; //애플리케이션 클라이언트 시크릿값"
    static public String apiURL;

    private static final int THREAD_ID = 10000;
    private static Context mContext = null;

    public static int start = 1;    // api 파싱 시 데이터 start 위치

    public static void main(int page, String keyword) {

        String text = null;
        try {
            text = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        apiURL = "https://openapi.naver.com/v1/search/shop?query=" + text+ "&display=10&start=" + start;    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text ; // xml 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        parseData(responseBody);
        if (page == 0)
        {
            if (MainActivity.val == "dog")
            {
                ((DogMainActivity)DogMainActivity.mContext).ListViewLoad();
            }
            else if (MainActivity.val == "cat")
            {
                ((CatMainActivity)CatMainActivity.mContext).ListViewLoad();
            }
        }
        else
        {
            if (MainActivity.val == "dog")
            {
                ((DogMainActivity)DogMainActivity.mContext).ListViewUpdate();
            }
            else if (MainActivity.val == "cat")
            {
                ((CatMainActivity)CatMainActivity.mContext).ListViewUpdate();
            }
        }
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        TrafficStats.setThreadStatsTag(THREAD_ID);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    private static void parseData(String responseBody) {
        String image, title, maker, brand, desc, category1, category2, category3, category4;
        JSONObject jsonObject = null;
        int i=0;
        try {
            jsonObject = new JSONObject(responseBody.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            while (i != 10) {
                JSONObject item = jsonArray.getJSONObject(i);
                image = item.optString("image");
                brand = item.optString("brand");
                category1 = item.optString("category1");
                category2 = item.optString("category2");
                category3 = item.optString("category3");
                category4 = item.optString("category4");
                // title = item.optString("title");
                // desc = item.optString("link");
                final String finalImage = image;
                //final String finalTitle = title.replace("<b>","").replace("</b>","");
                final String finalTitle = brand;
                final String finalDesc = category1 + " > " + category2 + " > " + category3 + " > " + category4;
                final Boolean[] check = {true};
                System.out.println("TITLE : " + finalTitle);
                System.out.println("DESC : " + finalDesc);
                System.out.println("IMAGE : " + finalImage);

                if (searchList.size() != 0)
                {
                    for (int j=0; j<searchList.size(); j++)
                    {
                        System.out.println(j+"번째 searchList : " + searchList.get(j).getName());
                        if (searchList.get(j).getName() == finalTitle) {
                            check[0] = false;
                            i--;
                            break;
                        }
                    }
                }

                Handler mHandler = new Handler(Looper.getMainLooper());
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (check[0] == true)
                        {
                            DataList.add(new list_item(finalImage, finalTitle, finalDesc));
                            searchList.add(new list_item(finalImage, finalTitle, finalDesc));
                        }
                        //((DogMainActivity)DogMainActivity.mContext).InitializeData(finalTitle, finalDesc);
                    }
                }, 0);
                //((DogMainActivity)DogMainActivity.mContext).DataList.add(new list_item(title, desc));
                i++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}