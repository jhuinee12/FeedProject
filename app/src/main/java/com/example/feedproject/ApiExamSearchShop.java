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

public class ApiExamSearchShop extends Thread{

    static public String clientId = "Cr4xj10LUdJFUYvg587h"; //애플리케이션 클라이언트 아이디값"
    static public String clientSecret = "NoptEf1cw7"; //애플리케이션 클라이언트 시크릿값"
    static public String apiURL;
    static public ArrayList<list_item> DataList;

    private static final int THREAD_ID = 10000;
    private static Context mContext = null;

    public static void main() {

        String text = null;
        try {
            text = URLEncoder.encode("애견사료", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        apiURL = "https://openapi.naver.com/v1/search/shop?query=" + text+ "&display=20&start=1";    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text ; // xml 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        parseData(responseBody);
        ((DogMainActivity)DogMainActivity.mContext).InitializeData();
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
        DataList = new ArrayList<list_item>();
        String image;
        String title;
        String desc;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(responseBody.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                image = item.optString("image");
                title = item.optString("title");
                desc = item.optString("link");
                final String finalImage = image;
                final String finalTitle = title;
                final String finalDesc = desc;
                System.out.println("TITLE : " + finalTitle);
                System.out.println("DESC : " + finalDesc);
                System.out.println("IMAGE : " + finalImage);
                Handler mHandler = new Handler(Looper.getMainLooper());
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DataList.add(new list_item(finalImage, finalTitle, finalDesc));
                        //((DogMainActivity)DogMainActivity.mContext).InitializeData(finalTitle, finalDesc);
                    }
                }, 0);
                //((DogMainActivity)DogMainActivity.mContext).DataList.add(new list_item(title, desc));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}