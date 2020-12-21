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

        TextView status1 = (TextView)findViewById(R.id.result); //파싱된 결과확인!

        boolean inNum = false, inFoodCd = false, inRegionName = false, inMonthName = false, inRegionCd = false;
        boolean inMonthCd = false, inGroupName = false, inDeskKor = false, inResearchYear = false, inMakerName = false;
        boolean inSubRefName = false, inServingSize = false;

        String num = "";		// 번호
        String food_cd = "";	// 식품코드
        String region_name = "";	 // 지역명
        String month_name = "";	 // 채취월
        String region_cd = "";	 // 지역코드
        String month_cd = "";	 // 채취월코드
        String group_name = "";	// 식품군
        String desk_kor = "";	// 식품이름
        String research_year = "";	// 조사년도
        String maker_name = "";	// 제조사명
        String sub_ref_name = "";	// 자료출처
        String serving_size = "";	// 총내용량

        Log.d(TAG, "트라이로 넘어갈까요?");

        try{
            // http://openapi.foodsafetykorea.go.kr/api/인증키/서비스명/요청파일타입/요청시작위치/요청종료위치
            URL url = new URL("http://openapi.foodsafetykorea.go.kr/api/40ca169d1ddf4764b029/I2790/xml/1/5"
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();

            Log.d(TAG,"파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("num")){ //title 만나면 내용을 받을수 있게 하자
                            inNum = true;
                            Log.d(TAG,"num.");
                        }
                        if(parser.getName().equals("food_cd")){ //address 만나면 내용을 받을수 있게 하자
                            inFoodCd = true;
                            Log.d(TAG,"food_cd.");
                        }
                        if(parser.getName().equals("region_name")){ //mapx 만나면 내용을 받을수 있게 하자
                            inRegionName = true;
                        }
                        if(parser.getName().equals("month_name")){ //mapx 만나면 내용을 받을수 있게 하자
                            inMonthName = true;
                        }
                        if(parser.getName().equals("region_cd")){ //mapy 만나면 내용을 받을수 있게 하자
                            inRegionCd = true;
                        }
                        if(parser.getName().equals("month_cd")){ //mapy 만나면 내용을 받을수 있게 하자
                            inMonthCd = true;
                        }
                        if(parser.getName().equals("group_name")){ //mapy 만나면 내용을 받을수 있게 하자
                            inGroupName = true;
                        }
                        if(parser.getName().equals("desk_kor")){ //mapy 만나면 내용을 받을수 있게 하자
                            inDeskKor = true;
                        }
                        if(parser.getName().equals("research_year")){ //mapy 만나면 내용을 받을수 있게 하자
                            inResearchYear = true;
                        }
                        if(parser.getName().equals("maker_name")){ //mapy 만나면 내용을 받을수 있게 하자
                            inMakerName = true;
                        }
                        if(parser.getName().equals("sub_ref_name")){ //mapy 만나면 내용을 받을수 있게 하자
                            inSubRefName = true;
                        }
                        if(parser.getName().equals("serving_size")){ //mapy 만나면 내용을 받을수 있게 하자
                            inServingSize = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inNum){ //isTitle이 true일 때 태그의 내용을 저장.
                            num = parser.getText();
                            inNum = false;
                        }
                        if(inFoodCd){ //isAddress이 true일 때 태그의 내용을 저장.
                            food_cd = parser.getText();
                            inFoodCd = false;
                        }
                        if(inRegionName){ //isMapx이 true일 때 태그의 내용을 저장.
                            region_name = parser.getText();
                            inRegionName = false;
                        }
                        if(inMonthName){ //isMapy이 true일 때 태그의 내용을 저장.
                            month_name = parser.getText();
                            inMonthName = false;
                        }
                        if(inRegionCd){ //isMapy이 true일 때 태그의 내용을 저장.
                            region_cd = parser.getText();
                            inRegionCd = false;
                        }
                        if(inMonthCd){ //isMapy이 true일 때 태그의 내용을 저장.
                            month_cd = parser.getText();
                            inMonthCd = false;
                        }
                        if(inGroupName){ //isMapy이 true일 때 태그의 내용을 저장.
                            group_name = parser.getText();
                            inGroupName = false;
                        }
                        if(inDeskKor){ //isMapy이 true일 때 태그의 내용을 저장.
                            desk_kor = parser.getText();
                            inDeskKor = false;
                        }
                        if(inResearchYear){ //isMapy이 true일 때 태그의 내용을 저장.
                            research_year = parser.getText();
                            inResearchYear = false;
                        }
                        if(inMakerName){ //isMapy이 true일 때 태그의 내용을 저장.
                            maker_name = parser.getText();
                            inMakerName = false;
                        }
                        if(inSubRefName){ //isMapy이 true일 때 태그의 내용을 저장.
                            sub_ref_name = parser.getText();
                            inSubRefName = false;
                        }
                        if(inServingSize){ //isMapy이 true일 때 태그의 내용을 저장.
                            serving_size = parser.getText();
                            inServingSize = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            status1.setText(status1.getText()+"번호 : "+ num +"\n 식품코드: "+ food_cd +"\n 지역명 : " + region_name
                                    +"\n 채취월 : " + month_name +  "\n 지역코드 : " + region_cd + "\n 채취월코드 : " + month_cd
                                    +"\n 식품군 : " +group_name  + "\n 식품이름 : " + desk_kor + "\n 조사년도 : " +research_year
                                    +"\n 제조사명 : " +maker_name  +"\n 자료출처 : " +sub_ref_name +"\n 총내용량 : "+serving_size +"\n");
                            inNum = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch(Exception e){
            status1.setText("에러가..났습니다...");
        }
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