package com.example.java_spring_server_gradle.newBot.Register.classes;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;

public class GetInformation {
    public String getInformation(String content, String id) {
        JSONParser jsonParser = new JSONParser();
        Object obj = null;
        try {
            obj = jsonParser.parse(content);
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject.get(id).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String utterance(String content) {
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(content);
            JSONObject jsonObject = (JSONObject) obj;
            Object userRequest = jsonObject.get("userRequest");
            //userRequest
            obj = jsonParser.parse(userRequest.toString());
            jsonObject = (JSONObject) obj;
            return jsonObject.get("utterance").toString();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String userId(String content) {
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(content);
            JSONObject jsonObject = (JSONObject) obj;
            Object userRequest = jsonObject.get("userRequest");
            //userRequest
            obj = jsonParser.parse(userRequest.toString());
            jsonObject = (JSONObject) obj;
            String user = jsonObject.get("user").toString();

            obj = jsonParser.parse(user);
            jsonObject = (JSONObject) obj;
            return jsonObject.get("id").toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public void SaveMembers(String id,String school,String grade,String clas) {
        HashMap<String,String> user = new HashMap<>();
        user.put("id",id);
        user.put("school",school);
        user.put("grade",grade);
        user.put("clas",clas);
        //Members.members.put(id,user);
    }

}
