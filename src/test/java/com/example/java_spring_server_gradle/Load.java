package com.example.java_spring_server_gradle;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Load {
    public static void main(String[] args){
        Map<String, String> map_from_file = new HashMap<String, String>();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("data.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String key : properties.stringPropertyNames()) {
            map_from_file.put(key, properties.get(key).toString());
        }

        System.out.println(map_from_file.values());
        System.out.println(map_from_file.containsKey("1"));

    }
}
