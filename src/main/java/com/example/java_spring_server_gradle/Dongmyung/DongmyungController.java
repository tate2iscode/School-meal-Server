package com.example.java_spring_server_gradle.Dongmyung;

import com.example.java_spring_server_gradle.chatbot.Response;
import com.example.java_spring_server_gradle.chatbot.responseMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//학교 급식 알려주기
@RestController
public class DongmyungController {

    public DongmyungMealRequest meal = new DongmyungMealRequest();
    public com.example.java_spring_server_gradle.chatbot.responseMessage responseMessage = new responseMessage();

    @RequestMapping(value = "/dongmyung/dinner")
    public Response dinner() {
        return responseMessage.responsemessage(TextLine(meal.getMeal(meal.DayTime(),"dinner")));
    }

    @RequestMapping(value = "/dongmyung/lunch")
    public Response lunch() {
        return responseMessage.responsemessage(TextLine(meal.getMeal(meal.DayTime(),"lunch")));
    }

    @RequestMapping(value = "/dongmyung/nextlunch")
    public Response nextLunch() {
        return responseMessage.responsemessage(TextLine(meal.getMeal(meal.nextDayTime(),"lunch")));
    }

    @RequestMapping(value = "/dongmyung/nextdinner")
    public Response nextdinner() {
        //System.out.println(TextLine(meal.getMeal(meal.nextDayTime(),"dinner")));
        return responseMessage.responsemessage(TextLine(meal.getMeal(meal.nextDayTime(),"dinner")));
    }

    public String TextLine(String text) {
        String[] line = text.split(" ");
        StringBuilder result = new StringBuilder();
        for(String ap : line) {
            result.append(ap).append("\n");
        }
        return result.substring(0, result.toString().length() - 1);
    }
}
