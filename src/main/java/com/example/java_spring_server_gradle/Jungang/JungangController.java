package com.example.java_spring_server_gradle.Jungang;

import com.example.java_spring_server_gradle.chatbot.Message;
import com.example.java_spring_server_gradle.chatbot.OutPuts;
import com.example.java_spring_server_gradle.chatbot.Response;
import com.example.java_spring_server_gradle.chatbot.SimpleText;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JungangController {

    @RequestMapping("jungang/lunch")
    public Response Jungang_lunch() {
        Message message = new Message();
        JungangMealRequest menu = new JungangMealRequest();
        menu.setLunch();
        message.setText(menu.getLunch());

        SimpleText simpleText = new SimpleText();
        simpleText.setSimpleText(message);

        OutPuts outPuts = new OutPuts();
        outPuts.setOutputs(new SimpleText[] {simpleText});

        Response response = new Response();
        response.setVersion("2.0");
        response.setTemplate(outPuts);

        return response;
    }

    @RequestMapping("jungang/dinner")
    public Response Jungang_dinner() {
        Message message = new Message();
        JungangMealRequest menu = new JungangMealRequest();
        menu.setLunch();
        message.setText(menu.getLunch());

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
