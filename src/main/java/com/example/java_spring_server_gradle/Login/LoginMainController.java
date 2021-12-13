package com.example.java_spring_server_gradle.Login;

import com.example.java_spring_server_gradle.Login.Classes.LoginClasses;
import com.example.java_spring_server_gradle.Template;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Objects;

@Controller
public class LoginMainController {

    MemberSaves Datas = new MemberSaves();
    Template read = new Template();

    @RequestMapping("/")
    @ResponseBody
    public String Main(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("id");

        if(id == null) {
            return read.ReadHtml("main");
        }
        return String.format(read.ReadHtml("main_logined"),id);
    }

    @RequestMapping("/register")
    @ResponseBody
    public String Register() {
        String web = read.ReadHtml("register");
        return web;
    }

    @RequestMapping(value = "/regist",method= RequestMethod.POST)
    public String Regist(HttpServletRequest req, LoginClasses content) {
        HttpSession session = req.getSession();
        String id = content.getId();
        String pw = content.getPw();
        HashMap<String,String> Data = Datas.RequestMembers();
        System.out.println(id);
        System.out.println(pw);
        if(Data.containsKey(id)) {
            return "redirect:";
        }

        Data.put(id,pw);
        Datas.SaveMembers(Data);

        session.setAttribute("id",id);
        session.setAttribute("pw",pw);
        return "redirect:";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest req, LoginClasses content) {
        HttpSession session = req.getSession();
        String id = content.getId();
        String pw = content.getPw();
        //System.out.println(id);
        HashMap<String,String> Data = Datas.RequestMembers();
        //System.out.println(Data.get(id));
        //System.out.println(Data.get(id).getClass());
        //System.out.println(id.getClass());
        if(Objects.equals(pw, Data.get(id))) {
            System.out.println(Data.get(id));
            session.setAttribute("id",id);
            session.setAttribute("pw",pw);
            return "redirect:";
        }
        return "redirect:";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
        return "redirect:";
    }

    @RequestMapping("/datas")
    @ResponseBody
    public HashMap<String, String> RequestData(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("id");
        String pw = (String) session.getAttribute("pw");
        HashMap<String,String> Data = Datas.RequestMembers();
        if(Objects.equals(id, "kimdongyun03")) {
            return Data;
        }
        HashMap<String,String> r = new HashMap<>();
        r.put("병신새끼인가요?","아닌가요?");
        return r;
    }
}
