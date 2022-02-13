package com.example.java_spring_server_gradle;

import org.json.JSONException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.NumberFormat;

public class Test {
    public static void main(String[] args) throws IOException, ParseException, JSONException {
        Document doc = Jsoup.connect("https://api.upbit.com/v1/ticker?markets=KRW-BTC").ignoreContentType(true).get();
        String text = doc.text();
        //System.out.println(text);
        //System.out.println(text.length());
        text = text.substring(1,text.length()-1);
        System.out.println(text);

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(text);
        System.out.println(obj.toString());
        JSONObject jsonObject = (JSONObject) obj;
        //System.out.print(jsonObject.get("trade_volume"));
        Double data = (Double) jsonObject.get("trade_price");
        System.out.println(data);

        NumberFormat f = NumberFormat.getInstance();
        f.setGroupingUsed(false);
        int output = Integer.parseInt(f.format(data));
        System.out.println(output);
        //System.out.println(market);

    }

}
