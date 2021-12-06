package com.example.java_spring_server_gradle.Dongmyung;

import com.example.java_spring_server_gradle.chatbot.Response;
import com.example.java_spring_server_gradle.chatbot.responseMessage;
import com.example.java_spring_server_gradle.chatbot.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//학교 급식 알려주기
@RestController
public class DongmyungController {

    public DongmyungMealRequest meal = new DongmyungMealRequest();
    public com.example.java_spring_server_gradle.chatbot.responseMessage responseMessage = new responseMessage();

    @RequestMapping(value = "/dongmyung/dinner")
    public Response dinner() {
        return responseMessage.responsemessage(meal.getMeal(meal.DayTime(),"dinner"));
    }

    @RequestMapping(value = "/dongmyung/lunch")
    public Response lunch() {
        return responseMessage.responsemessage(meal.getMeal(meal.DayTime(),"lunch"));
    }

    @RequestMapping(value = "/dongmyung/nextlunch")
    public Response nextLunch() {
        return responseMessage.responsemessage(meal.getMeal(meal.nextDayTime(),"lunch"));
    }

    @RequestMapping(value = "/dongmyung/nextdinner")
    public Response nextdinner() {
        return responseMessage.responsemessage(meal.getMeal(meal.nextDayTime(),"dinner"));
    }
}
