package com.example.java_spring_server_gradle.chea;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HTMLController {
    @RequestMapping("chea")
    @ResponseBody
    public String test(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("id")+""+request.getSession().getAttribute("pw")+"  hi";
    }
}
