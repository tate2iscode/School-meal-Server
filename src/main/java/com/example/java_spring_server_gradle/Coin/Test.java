package com.example.java_spring_server_gradle.Coin;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) throws IOException, ParseException {
        HashMap<String,String> test = new HashMap<>();
        test.put("a","1");
        System.out.println(test);
        test.put("a","0");
        System.out.println(test);
    }
}
