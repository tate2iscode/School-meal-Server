package com.example.java_spring_server_gradle.Coin;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Objects;

public class CallCoinPrice {
    NumberFormat f = NumberFormat.getInstance();
    {
        f.setGroupingUsed(false);
    }

    JSONParser jsonParser = new JSONParser();
    CoinNames coinNames = new CoinNames();
    String[] coins = coinNames.coins;

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

    public HashMap<String, String> CallCoinsList(String[] list) throws IOException, ParseException {
        HashMap<String,String> result = new HashMap<>();
        String url = CoinAPI(coins);
        Document doc = Jsoup.connect(url).ignoreContentType(true).get();
        String content = doc.text();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(content);
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            String name = (String) jsonObject.get("market");
            //String[] array = name.split("-");
            //name = array[1];
            //System.out.println(name);
            //System.out.println(jsonObject.toString());
            Double price_double = (Double) jsonObject.get("trade_price");
            String price = Integer.toString(Integer.parseInt(f.format(price_double)));
            //System.out.println(price);
            result.put(name, price);
        }
        return result;
    }

    public Boolean confirm(String[] list, String name) {
        for(int i = 0 ; i<list.length ; i++) {
            if(Objects.equals(list[i], name)) {
                return true;
            }
        }
        return false;
    }

    public String CoinAPI(String[] coins) {
        String url = "https://api.upbit.com/v1/ticker?";
        String add = "markets=%s&";
        for (String coin : coins) {
            url = url + String.format(add, "KRW-" + coin);
        }
        return url;
    }
}
