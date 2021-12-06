package com.example.java_spring_server_gradle.chatbot;

import com.example.java_spring_server_gradle.Dongmyung.DongmyungMealRequest;

public class responseMessage {
    public Response responsemessage(String text) {
        Message message = new Message();
        DongmyungMealRequest meal = new DongmyungMealRequest();
        message.setText(text);

        SimpleText simpleText = new SimpleText();
        simpleText.setSimpleText(message);

        OutPuts outPuts = new OutPuts();
        outPuts.setOutputs(new SimpleText[] {simpleText});

        Response response = new Response();
        response.setVersion("2.0");
        response.setTemplate(outPuts);

        return response;
    }
}
