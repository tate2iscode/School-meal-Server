package com.example.java_spring_server_gradle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Template {
    public String ReadHtml(String name) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader("templates/"+name+".html"));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        return contentBuilder.toString();
    }
}
