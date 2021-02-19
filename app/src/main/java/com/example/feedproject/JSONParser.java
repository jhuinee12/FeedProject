package com.example.feedproject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;

public class JSONParser {
    private static JSONParser jsonParser;
    public void parse(String json) {
    }
    public static void main(String[] args) {

        //JSON 데이터
        //String jsonInfo = "{\"books\":[{\"genre\":\"소설\",\"price\":\"100\",\"name\":\"사람은 무엇으로 사는가?\",\"writer\":\"톨스토이\",\"publisher\":\"톨스토이 출판사\"},{\"genre\":\"소설\",\"price\":\"300\",\"name\":\"홍길동전\",\"writer\":\"허균\",\"publisher\":\"허균 출판사\"},{\"genre\":\"소설\",\"price\":\"900\",\"name\":\"레미제라블\",\"writer\":\"빅토르 위고\",\"publisher\":\"빅토르 위고 출판사\"}],\"persons\":[{\"nickname\":\"남궁민수\",\"age\":\"25\",\"name\":\"송강호\",\"gender\":\"남자\"},{\"nickname\":\"예니콜\",\"age\":\"21\",\"name\":\"전지현\",\"gender\":\"여자\"}]}";

        try {
            //JSON데이터를 넣어 JSON Object 로 만들어 준다.
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonInfo);

            //books의 배열을 추출
            JSONArray feedshopInfoArray = (JSONArray) jsonObject.get("feedshop");

            System.out.println("* FEEDSHOP *");

            for(int i=0; i<feedshopInfoArray.size(); i++){

                System.out.println("=FEEDSHOP_"+i+" ===========================================");

                //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
                JSONObject feedshopObject = (JSONObject) feedshopInfoArray.get(i);

                //JSON name으로 추출
                System.out.println("bookInfo: name==>"+feedshopObject.get("title"));
                System.out.println("bookInfo: writer==>"+feedshopObject.get("link"));
                System.out.println("bookInfo: price==>"+feedshopObject.get("image"));
                System.out.println("bookInfo: genre==>"+feedshopObject.get("mallName"));
                System.out.println("bookInfo: publisher==>"+feedshopObject.get("maker"));

            }

             catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}