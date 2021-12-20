package com.example.java_spring_server_gradle.Coin;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.NumberFormat;

public class CallCoinPrice {
    NumberFormat f = NumberFormat.getInstance();
    {
        f.setGroupingUsed(false);
    }

    public int CallCoin(String CoinName) throws IOException, ParseException {
        String url = String.format("https://api.upbit.com/v1/ticker?markets=%s",CoinName);
        Document doc = Jsoup.connect(url).ignoreContentType(true).get();
        String content = doc.text();
        content = content.substring(1,content.length()-1);

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(content);
        JSONObject jsonObject = (JSONObject) obj;
        Double data = (Double) jsonObject.get("trade_price");
        return Integer.parseInt(f.format(data));
    }
}
