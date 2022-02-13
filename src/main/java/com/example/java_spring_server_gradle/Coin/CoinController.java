package com.example.java_spring_server_gradle.Coin;

import com.example.java_spring_server_gradle.Login.MemberSaves;
import com.example.java_spring_server_gradle.Template;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Objects;

@Controller
public class CoinController {

    Template template = new Template();
    CallCoinPrice callCoinPrice = new CallCoinPrice();
    CoinNames c = new CoinNames();
    String[] coins = c.coins;
    CoinData coinDataSet = new CoinData();

    @RequestMapping("/coin")
    @ResponseBody
    public String CoinMain(HttpServletRequest req) throws IOException, ParseException {
        HashMap<String,String> data = coinDataSet.RequestDatas();
        String result = "";
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("id");
        if(id==null) {
            return "로그인이나 해 씨발련아";
        } else{
            SetMember(id);
            data = coinDataSet.RequestDatas();
        }
        String htmlMain = template.ReadHtml("CoinMain");
        String add = template.ReadHtml("add");
        //System.out.println(add);
        HashMap<String,String> coinprice = callCoinPrice.CallCoinsList(coins);
        for(String coinName : coinprice.keySet()) {
            int haveMoney = Integer.parseInt(data.get(id));
            int MaxValue = haveMoney / Integer.parseInt(coinprice.get(coinName));
            //System.out.println(coinName);
            int haveCoin = Integer.parseInt(data.get(id+coinName)); // 새코인 생성시 오류
            //System.out.println(haveCoin);
            result = result + String.format(add,coinName,coinprice.get(coinName),coinName,MaxValue,coinName,haveCoin);
        }
        return String.format(htmlMain,id,data.get(id),CallP(id),result);
    }//                                    현금        코인+현금

    public Boolean ExistMember(String id) throws IOException, ParseException {
        HashMap<String,String> data = coinDataSet.RequestDatas();
        HashMap<String,String> coinprice = callCoinPrice.CallCoinsList(coins);
        for(String coinName : coinprice.keySet()) {
            if(data.containsKey(id+coinName)) {
                return false;
            }
            return true;
        }
        return true;
    }

    public void SetMember(String id) throws IOException, ParseException {
        HashMap<String,String> data = coinDataSet.RequestDatas();
        HashMap<String,String> coinprice = callCoinPrice.CallCoinsList(coins);
        if(!data.containsKey(id)) {
            data.put(id,"10000000");
        }
        for(String coinName : coinprice.keySet()) {
            if(!data.containsKey(id+coinName)) {
                data.put(id+coinName,"0");
            }
            //System.out.println(coinName);
        }
        coinDataSet.SaveDatas(data);
    }

    public int CallP(String id) throws IOException, ParseException {
        int result = 0;
        HashMap<String,String> data = coinDataSet.RequestDatas();
        HashMap<String,String> coinprice = callCoinPrice.CallCoinsList(coins);
        for(String coinName : coinprice.keySet()) {
            String v = data.get(id+coinName);
            String price = coinprice.get(coinName);
            int intprice = Integer.parseInt(price);
            int intv = Integer.parseInt(v);
            result = result + intprice*intv;
        }
        result = result + Integer.parseInt(data.get(id));
        return result;
    }

    @RequestMapping("/coindata")
    @ResponseBody
    public HashMap<String,String> coindata(HttpServletRequest req) throws IOException, ParseException {
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("id");
        //System.out.println(id);
        //System.out.println(id != "kimdongyun03");
        if(!Objects.equals(id, "kimdongyun03")) {
            HashMap<String,String> a = new HashMap<>();
            a.put("로그인","해");
            return a;
        }
        MemberSaves memberSaves = new MemberSaves();
        HashMap<String,String> data = memberSaves.RequestMembers();
        HashMap<String,String> result = new HashMap<>();
        //System.out.println(data);
        for(String name : data.keySet()) {
            //System.out.println(name);
            //System.out.println(CallP(name));
            result.put(name,Integer.toString(CallP(name)));
        }
        return result;
    }
}
