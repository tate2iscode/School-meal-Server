package com.example.java_spring_server_gradle.chatbot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DayTime {
    public String getDayTime() {
        SimpleDateFormat todaySdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        //한국기준 날짜
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());
        todaySdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        return todaySdf.format(date);
    }

    String dayTime;
}
