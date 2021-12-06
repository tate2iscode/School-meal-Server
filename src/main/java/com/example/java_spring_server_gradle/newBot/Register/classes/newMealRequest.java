package com.example.java_spring_server_gradle.newBot.Register.classes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class newMealRequest {
    public String mealRequest(String school,String date,String time) {
        String schoolcode = null;
        String mealtime = null;
        if(school == "진주동명고") {
            schoolcode = "9010109";
        } else if(school == "진주중앙고") {
            schoolcode = "9010279";
        } else if(school == "진주명신고") {
            schoolcode = "9010050";
        }

        String url = String.format("https://open.neis.go.kr/hub/mealServiceDietInfo?ATPT_OFCDC_SC_CODE=S10&SD_SCHUL_CODE=%s&MLSV_YMD=%s&Type=json&MMEAL_SC_CODE=%s",schoolcode,date,time);
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = doc.text();
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray code = (JSONArray) jsonObject.get("mealServiceDietInfo");
        if(code == null) {
            return "급식 없음";
        }
        String mealService = code.get(1).toString();

        parser = new JSONParser();
        try {
            obj = parser.parse(mealService);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        jsonObject = (JSONObject) obj;
        code = (JSONArray) jsonObject.get("row");
        String row = code.get(0).toString();

        parser = new JSONParser();
        try {
            obj = parser.parse(row);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        jsonObject = (JSONObject) obj;
        return jsonObject.get("DDISH_NM").toString();
    }
}
