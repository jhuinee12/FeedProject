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

        XmlPullParser_fsk fsk = new XmlPullParser_fsk();

        Log.d(TAG, "열렸어요.");

        StrictMode.enableDefaults();

        TextView tvFoodCd = (TextView)findViewById(R.id.tv_food_cd); //파싱된 결과확인!
        TextView tvRegionName = (TextView)findViewById(R.id.tv_region_name); //파싱된 결과확인!
        TextView tvMonthName = (TextView)findViewById(R.id.tv_month_name); //파싱된 결과확인!
        TextView tvDeskKor = (TextView)findViewById(R.id.tv_desk_kor); //파싱된 결과확인!

        boolean inNum = false, inFoodCd = false, inRegionName = false, inMonthName = false, inRegionCd = false;
        boolean inMonthCd = false, inGroupName = false, inDeskKor = false, inResearchYear = false, inMakerName = false;
        boolean inSubRefName = false, inServingSize = false;

        Log.d(TAG, "트라이로 넘어갈까요?");

        try{
            // http://openapi.foodsafetykorea.go.kr/api/인증키/서비스명/요청파일타입/요청시작위치/요청종료위치
            URL url = new URL("http://openapi.foodsafetykorea.go.kr/api/40ca169d1ddf4764b029/I2790/xml/1/1"
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();

            Log.d(TAG,"파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("NUM")){ //title 만나면 내용을 받을수 있게 하자
                            inNum = true;
                            Log.d(TAG,"num.");
                        }
                        if(parser.getName().equals("FOOD_CD")){ //address 만나면 내용을 받을수 있게 하자
                            inFoodCd = true;
                            Log.d(TAG,"food_cd.");
                        }
                        if(parser.getName().equals("SAMPLING_REGION_NAME")){ //mapx 만나면 내용을 받을수 있게 하자
                            inRegionName = true;
                        }
                        if(parser.getName().equals("SAMPLING_MONTH_NAME")){ //mapx 만나면 내용을 받을수 있게 하자
                            inMonthName = true;
                        }
                        if(parser.getName().equals("SAMPLING_REGION_CD")){ //mapy 만나면 내용을 받을수 있게 하자
                            inRegionCd = true;
                        }
                        if(parser.getName().equals("SAMPLING_MONTH_NAME")){ //mapy 만나면 내용을 받을수 있게 하자
                            inMonthCd = true;
                        }
                        if(parser.getName().equals("GROUP_NAME")){ //mapy 만나면 내용을 받을수 있게 하자
                            inGroupName = true;
                        }
                        if(parser.getName().equals("DESC_KOR")){ //mapy 만나면 내용을 받을수 있게 하자
                            inDeskKor = true;
                        }
                        if(parser.getName().equals("RESEARCH_YEAR")){ //mapy 만나면 내용을 받을수 있게 하자
                            inResearchYear = true;
                        }
                        if(parser.getName().equals("MAKER_NAME")){ //mapy 만나면 내용을 받을수 있게 하자
                            inMakerName = true;
                        }
                        if(parser.getName().equals("SUB_REF_NAME")){ //mapy 만나면 내용을 받을수 있게 하자
                            inSubRefName = true;
                        }
                        if(parser.getName().equals("SERVING_SIZE")){ //mapy 만나면 내용을 받을수 있게 하자
                            inServingSize = true;
                        }
                        /*if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }*/
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inNum){ //isTitle이 true일 때 태그의 내용을 저장.
                            fsk.num = parser.getText();
                            inNum = false;
                        }
                        if(inFoodCd){ //isAddress이 true일 때 태그의 내용을 저장.
                            fsk.food_cd = parser.getText();
                            inFoodCd = false;
                        }
                        if(inRegionName){ //isMapx이 true일 때 태그의 내용을 저장.
                            fsk.region_name = parser.getText();
                            inRegionName = false;
                        }
                        if(inMonthName){ //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.month_name = parser.getText();
                            inMonthName = false;
                        }
                        if(inRegionCd){ //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.region_cd = parser.getText();
                            inRegionCd = false;
                        }
                        if(inMonthCd){ //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.month_cd = parser.getText();
                            inMonthCd = false;
                        }
                        if(inGroupName){ //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.group_name = parser.getText();
                            inGroupName = false;
                        }
                        if(inDeskKor){ //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.desk_kor = parser.getText();
                            inDeskKor = false;
                        }
                        if(inResearchYear){ //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.research_year = parser.getText();
                            inResearchYear = false;
                        }
                        if(inMakerName){ //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.maker_name = parser.getText();
                            inMakerName = false;
                        }
                        if(inSubRefName){ //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.sub_ref_name = parser.getText();
                            inSubRefName = false;
                        }
                        if(inServingSize){ //isMapy이 true일 때 태그의 내용을 저장.
                            fsk.serving_size = parser.getText();
                            inServingSize = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("NUM")){
/*                            status1.setText("번호 : "+ fsk.num +"\n 식품코드: "+ fsk.food_cd +"\n 지역명 : " + fsk.region_name
                                    +"\n 채취월 : " + fsk.month_name +  "\n 지역코드 : " + fsk.region_cd + "\n 채취월코드 : " + fsk.month_cd
                                    +"\n 식품군 : " + fsk.group_name  + "\n 식품이름 : " + fsk.desk_kor + "\n 조사년도 : " + fsk.research_year
                                    +"\n 제조사명 : " + fsk.maker_name  +"\n 자료출처 : " + fsk.sub_ref_name +"\n 총내용량 : "+ fsk.serving_size +"\n");*/
                            tvFoodCd.setText(fsk.food_cd);
                            tvRegionName.setText(fsk.region_name);
                            tvMonthName.setText(fsk.month_name);
                            tvDeskKor.setText(fsk.desk_kor);
                            inNum = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch(Exception e){
            tvFoodCd.setText("에러가..났습니다...");
        }

        Log.i(TAG, fsk.num +"\n 식품코드: "+ fsk.food_cd +"\n 지역명 : " + fsk.region_name
                +"\n 채취월 : " + fsk.month_name +  "\n 지역코드 : " + fsk.region_cd + "\n 채취월코드 : " + fsk.month_cd
                +"\n 식품군 : " + fsk.group_name  + "\n 식품이름 : " + fsk.desk_kor + "\n 조사년도 : " + fsk.research_year
                +"\n 제조사명 : " + fsk.maker_name  +"\n 자료출처 : " + fsk.sub_ref_name +"\n 총내용량 : "+ fsk.serving_size +"\n");
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