package com.example.java_spring_server_gradle.Login.Members;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Members {
    public static HashMap<String, MemberInfo> information = new HashMap<>();

    public void SaveData() {
        Properties properties = new Properties();
        for(HashMap.Entry<String,MemberInfo> entry : Members.information.entrySet()) {
            properties.put(entry.getKey(),entry.getValue());
        }
        try {
            properties.store(new FileOutputStream("data.properties"),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
