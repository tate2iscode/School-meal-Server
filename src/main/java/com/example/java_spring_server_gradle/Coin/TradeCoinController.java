package com.example.java_spring_server_gradle.Coin;

import jdk.jfr.Unsigned;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TradeCoinController {
    @RequestMapping("/buycoin/{coinName}")
    public String buy(@PathVariable("coinName") String coinName, ContentClass content, HttpServletRequest req) throws IOException, ParseException {
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("id");
        String num = content.getNum();
        long intnum = Integer.parseInt(num);
        BuyCoin(id,coinName,num);
        return "redirect:/coin";
    }

    @RequestMapping("/sellcoin/{coinName}")
    public String sell(@PathVariable("coinName") String coinName, ContentClass content, HttpServletRequest req) throws IOException, ParseException {
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("id");
        String num = content.getNum();
        SellCoin(id,coinName,num);
        return "redirect:/coin";
    }

    public void SellCoin(String id, String CoinName, String num) throws IOException, ParseException {
        CoinData coinData = new CoinData();
        CallCoinPrice coinPrice = new CallCoinPrice();
        HashMap<String,String> coinprice = coinPrice.CallCoinsList(coinPrice.coins);
        HashMap<String,String> data = coinData.RequestDatas();
        String haveCoin = data.get(id+CoinName);
        String price = coinprice.get(CoinName);
        long intnum = Integer.parseInt(num);
        long inthaveCoin = Integer.parseInt(haveCoin);
        long intprice = Integer.parseInt(price);
        long money = Integer.parseInt(data.get(id));

        if(intnum>inthaveCoin) {
            return ;
        }

        money = money + intprice * intnum;
        inthaveCoin = inthaveCoin - intnum;

        data.put(id+CoinName,Long.toString(inthaveCoin));
        data.put(id,Long.toString(money));
        coinData.SaveDatas(data);
    }

    public void BuyCoin(String id, String CoinName, String num) throws IOException, ParseException {
        CoinData coinData = new CoinData();
        CallCoinPrice coinPrice = new CallCoinPrice();
        HashMap<String,String> coinprice = coinPrice.CallCoinsList(coinPrice.coins);
        HashMap<String,String> data = coinData.RequestDatas();
        String haveCoin = data.get(id+CoinName);
        String price = coinprice.get(CoinName);
        long intnum = Integer.parseInt(num);
        long inthaveCoin = Integer.parseInt(haveCoin);
        long intprice = Integer.parseInt(price);
        long money = Integer.parseInt(data.get(id));

        if(intprice*intnum > money) {
            return;
        }

        inthaveCoin = inthaveCoin+intnum;
        money = money - intprice*intnum;
        data.put(id+CoinName, Long.toString(inthaveCoin));
        data.put(id,Long.toString(money));
        coinData.SaveDatas(data);
    }
}
