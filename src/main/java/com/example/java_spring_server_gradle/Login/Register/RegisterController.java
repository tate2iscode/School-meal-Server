package com.example.java_spring_server_gradle.Login.Register;

import com.example.java_spring_server_gradle.Login.Members.MemberInfo;
import com.example.java_spring_server_gradle.Login.Members.Members;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
public class RegisterController {

    @RequestMapping("/register")
    @ResponseBody
    public String register() {

        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>회원가입</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form action=\"/register/account\" method=\"POST\">\n" +
                "    <p><input type=\"text\" name=\"RegisterID\" placeholder=\"아이디\" required pattern=\"[a-zA-Z]+${8,16}\" title=\"소문자 영어\"autocomplete=\"off\" autofocus maxlength=\"30\"></p>\n" +
                "    <p><input type=\"password\" name=\"RegisterPW\" placeholder=\"비밀번호\" required pattern=\"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$\"  maxlength=\"30\"></p>\n" +
                "    <p><input type=\"text\" name=\"RegisterName\" id=\"NAME\" placeholder=\"이름\" maxlength=\"30\"></p>\n" +
                "    <p><input type=\"submit\" id=\"HG\" value=\"회원가입\" maxlength=\"30\"></p>\n" +
                "    </form>\n" +
                "    <img src=\"https://pbs.twimg.com/profile_images/1311522537050836992/M84mBp63.jpg\" alt=\"\">\n" +
                "</body>\n" +
                "</html>";
    }

    @RequestMapping("/register/account")
    public String registerAccount(RegisterMember registerMember, HttpServletRequest request) {

        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setId(registerMember.getRegisterID());
        memberInfo.setName(registerMember.getRegisterName());
        memberInfo.setPw(registerMember.getRegisterPW());

        if(registerMember.getRegisterName() != null && registerMember.getRegisterID() != null && registerMember.getRegisterPW() != null) {
            if(!Members.information.containsKey(registerMember.getRegisterID())){
                HttpSession session = request.getSession();
                session.setAttribute("id",registerMember.getRegisterID());
                session.setAttribute("pw",registerMember.getRegisterPW());
                Members.information.put(registerMember.getRegisterID(),memberInfo);
            }
        }
        return "redirect:/";
    }
}
