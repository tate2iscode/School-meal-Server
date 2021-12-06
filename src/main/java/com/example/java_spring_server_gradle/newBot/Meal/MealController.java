package com.example.java_spring_server_gradle.newBot.Meal;

import com.example.java_spring_server_gradle.Dongmyung.DongmyungMealRequest;
import com.example.java_spring_server_gradle.chatbot.Response;
import com.example.java_spring_server_gradle.chatbot.responseMessage;
import com.example.java_spring_server_gradle.newBot.Register.classes.GetInformation;
import com.example.java_spring_server_gradle.newBot.Register.classes.Members;
import com.example.java_spring_server_gradle.newBot.Register.classes.newMealRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
public class MealController {

    com.example.java_spring_server_gradle.chatbot.responseMessage responseMessage = new responseMessage();
    GetInformation getInformation = new GetInformation();
    DongmyungMealRequest dongmyungMealRequest = new DongmyungMealRequest();
    newMealRequest newMealRequest = new newMealRequest();

    @RequestMapping("meal")
    public Response lunch(@RequestBody Map<String,Object> content) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String id = getInformation.userId(jsonString);
        String text = getInformation.utterance(jsonString);
        if(!Members.members.containsKey(id+"school")) {
            return responseMessage.responsemessage("정보를 등록하세요");
        }
        String school = Members.members.get(id+"school");
        if(Objects.equals(text, "점심")) {
            String time = dongmyungMealRequest.DayTime();
            return responseMessage.responsemessage(newMealRequest.mealRequest(school,time,"2"));
        }else if(Objects.equals(text, "저녁")) {
            String time = dongmyungMealRequest.DayTime();
            return responseMessage.responsemessage(newMealRequest.mealRequest(school,time,"3"));
        }else if(Objects.equals(text, "내일점심")) {
            String time = dongmyungMealRequest.nextDayTime();
            return responseMessage.responsemessage(newMealRequest.mealRequest(school,time,"2"));
        }else if(Objects.equals(text, "내일저녁")) {
            String time = dongmyungMealRequest.nextDayTime();
            return responseMessage.responsemessage(newMealRequest.mealRequest(school,time,"3"));
        }
        return responseMessage.responsemessage("");
    }
}
