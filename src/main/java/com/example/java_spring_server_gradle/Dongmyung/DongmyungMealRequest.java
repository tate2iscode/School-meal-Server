package com.example.java_spring_server_gradle.Dongmyung;

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

public class DongmyungMealRequest {

    public String DayTime() {
        SimpleDateFormat todaySdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        //한국기준 날짜
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());
        todaySdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        return todaySdf.format(date);
    }
    public String nextDayTime() {
        SimpleDateFormat todaySdf = new SimpleDateFormat("yyyyMMdd",Locale.KOREA);
        Calendar calendar = Calendar.getInstance();
        Date s_date = new Date(calendar.getTimeInMillis());
        todaySdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String today = todaySdf.format(s_date);
        try {
            Date date = todaySdf.parse(today);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
                cal.add(Calendar.DATE,3);
                return todaySdf.format(cal.getTime());
            }
            cal.add(Calendar.DATE,1);
            return todaySdf.format(cal.getTime());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return "0";
    }

    public String getMeal(String date,String time) {
        if(time == "lunch") {
            String url = String.format("https://open.neis.go.kr/hub/mealServiceDietInfo?ATPT_OFCDC_SC_CODE=S10&SD_SCHUL_CODE=9010109&MLSV_YMD=%s&Type=json&MMEAL_SC_CODE=%s",date,"2");
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
                return "급식없음";
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

        if(time == "dinner") {
            String url = String.format("https://open.neis.go.kr/hub/mealServiceDietInfo?ATPT_OFCDC_SC_CODE=S10&SD_SCHUL_CODE=9010109&MLSV_YMD=%s&Type=json&MMEAL_SC_CODE=%s",date,"3");
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
                return "급식없음";
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
        return "";
    }
}
