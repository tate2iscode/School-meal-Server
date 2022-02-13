package com.example.java_spring_server_gradle.Login;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MemberSaves {  
    public static HashMap<String,String> MemberSaves;

    public HashMap<String,String> RequestMembers() {
        HashMap<String, String> map_from_file = new HashMap<String, String>();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("data.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String key : properties.stringPropertyNames()) {
            map_from_file.put(key, properties.get(key).toString());
        }
        return map_from_file;
    }

    public void SaveMembers(HashMap<String,String> data) {
        Properties properties = new Properties();

        for (Map.Entry<String,String> entry : data.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }

        try {
            properties.store(new FileOutputStream("data.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
