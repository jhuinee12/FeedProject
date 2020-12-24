package com.example.feedproject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class XmlPullParser_example extends RecyclerView.Adapter<XmlPullParser_example.MyViewHolder> {

    private static final String TAG = "XmlPullParser_example";
    private ArrayList<XmlPullParser_fsk> mList;
    private LayoutInflater mInflate;
    private Context mContext;

    public void MyAdapter(Context context, ArrayList<XmlPullParser_fsk> itmes) {
        this.mList = itmes;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_pull_parser_example);

        XmlPullParser_fsk fsk = new XmlPullParser_fsk();

        Log.d(TAG, "열렸어요.");

        StrictMode.enableDefaults();

        TextView tv_food_cd = (TextView)findViewById(R.id.result); //파싱된 결과확인!
        TextView tv_region_name = (TextView)findViewById(R.id.result); //파싱된 결과확인!
        TextView tv_month_name = (TextView)findViewById(R.id.result); //파싱된 결과확인!
        TextView tv_desk_kor = (TextView)findViewById(R.id.result); //파싱된 결과확인!

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
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            tv_food_cd.setText(tv_food_cd.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
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
                        if(parser.getName().equals("item")){
                            tv_food_cd.setText(tv_food_cd.getText()+fsk.food_cd);
                            inNum = false;
                        }
                        break;
                }
                Log.i(TAG, fsk.num +"\n 식품코드: "+ fsk.food_cd +"\n 지역명 : " + fsk.region_name
                        +"\n 채취월 : " + fsk.month_name +  "\n 지역코드 : " + fsk.region_cd + "\n 채취월코드 : " + fsk.month_cd
                        +"\n 식품군 : " + fsk.group_name  + "\n 식품이름 : " + fsk.desk_kor + "\n 조사년도 : " + fsk.research_year
                        +"\n 제조사명 : " + fsk.maker_name  +"\n 자료출처 : " + fsk.sub_ref_name +"\n 총내용량 : "+ fsk.serving_size +"\n");
                parserEvent = parser.next();
            }
        } catch(Exception e){
            tv_food_cd.setText(tv_food_cd.getText()+"에러가..났습니다...");
        }
    }*/

    @NonNull
    @Override
    public XmlPullParser_example.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.activity_xml_pull_parser_example, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //binding
        holder.food_cd.setText(mList.get(position).food_cd);
        holder.region_name.setText(mList.get(position).region_name);
        holder.month_name.setText(mList.get(position).month_name);
        holder.desk_kor.setText(mList.get(position).desk_kor);

        //Click event
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView food_cd;
        public TextView region_name;
        public TextView month_name;
        public TextView desk_kor;

        public MyViewHolder(View itemView) {
            super(itemView);

            food_cd = itemView.findViewById(R.id.tv_food_cd2);
            region_name = itemView.findViewById(R.id.tv_region_name2);
            month_name = itemView.findViewById(R.id.tv_month_name2);
            desk_kor = itemView.findViewById(R.id.tv_desk_kor2);

        }
    }
}