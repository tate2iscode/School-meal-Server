package com.example.java_spring_server_gradle.Jungang;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class JungangMealRequest {
    public String DayTime() {
        SimpleDateFormat todaySdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        //한국기준 날짜
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());
        todaySdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        return todaySdf.format(date);
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner() {
        String url = String.format("https://open.neis.go.kr/hub/mealServiceDietInfo?ATPT_OFCDC_SC_CODE=S10&SD_SCHUL_CODE=9010279&MLSV_YMD=%s&Type=json&MMEAL_SC_CODE=3",DayTime());
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
            this.dinner = "급식 없음";
            return;
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
        this.dinner = jsonObject.get("DDISH_NM").toString();
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch() {
        String url = String.format("https://open.neis.go.kr/hub/mealServiceDietInfo?ATPT_OFCDC_SC_CODE=S10&SD_SCHUL_CODE=9010279&MLSV_YMD=%s&Type=json&MMEAL_SC_CODE=2",DayTime());
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
            this.lunch = "급식 없음";
            return;
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
        this.lunch = jsonObject.get("DDISH_NM").toString();
    }

    String dinner;
    String lunch;
}
