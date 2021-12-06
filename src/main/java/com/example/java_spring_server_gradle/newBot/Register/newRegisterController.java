package com.example.java_spring_server_gradle.newBot.Register;

import com.example.java_spring_server_gradle.chatbot.Response;
import com.example.java_spring_server_gradle.chatbot.responseMessage;
import com.example.java_spring_server_gradle.newBot.Register.classes.GetInformation;
import com.example.java_spring_server_gradle.newBot.Register.classes.Members;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class newRegisterController {

    responseMessage responseMessage = new responseMessage();
    GetInformation getInformation = new GetInformation();

    @RequestMapping("registschool")
    public Response SchoolRegister(@RequestBody Map<String, Object> content) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String school = getInformation.utterance(jsonString);
        String id = getInformation.userId(jsonString);
        Members.members.put(id+"school",school);
       // System.out.println(school);
        return responseMessage.responsemessage(getInformation.userId(jsonString));
    }

    @RequestMapping("registgrade")
    public Response GradeRegister(@RequestBody Map<String,Object> content) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String grade = getInformation.utterance(jsonString);
        String id = getInformation.userId(jsonString);

        Members.members.put(id+"grade",grade);
        //System.out.println(grade);
        return responseMessage.responsemessage(getInformation.userId(jsonString));
    }

    @RequestMapping("registclas")
    public Response ClassRegister(@RequestBody Map<String,Object> content) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String clas = getInformation.utterance(jsonString);
        String id = getInformation.userId(jsonString);

        Members.members.put(id+"class",clas);
        //System.out.println(clas);
        String response = ""+Members.members.get(id+"school")+" "+Members.members.get(id+"grade")+" "+Members.members.get(id+"class");
        return responseMessage.responsemessage(response);
    }

    @RequestMapping("returnInformation")
    public Response returnInformation(@RequestBody Map<String,Object> content){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String id = getInformation.userId(jsonString);
        //String response = "학교 "+data.get("school")+" 학년 "+data.get("grade")+" 반 "+data.get("clas");
        return responseMessage.responsemessage("good");
    }
}
