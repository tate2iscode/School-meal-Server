package com.example.java_spring_server_gradle.Login;

import com.example.java_spring_server_gradle.Login.Members.MemberInfo;
import com.example.java_spring_server_gradle.Login.Members.Members;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

@Controller
public class LoginController {
    @RequestMapping("/")
    @ResponseBody
    public String main(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

        String login = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>로그인</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div>\n" +
                "<p><form action=\"/login\" method=\"POST\">\n" +
                "    <p><input type=\"text\" name=\"ID\" id=\"ID\" placeholder=\"아이디\" required pattern=\"[a-zA-Z]+${8,16}\" title=\"소문자 영어\"autocomplete=\"off\" autofocus maxlength=\"30\"></p>\n" +
                "    <p><input type=\"password\" name=\"PW\" id=\"PW\" placeholder=\"비밀번호\" required pattern=\"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$\" maxlength=\"30\"></p>\n" +
                "    <p><input type=\"submit\" id=\"LOG\" value=\"로그인\"></p>\n" +
                "</form></p>\n" +
                "<p><form action=\"/register\">\n" +
                "    <input type=\"submit\" value=\"회원가입\">\n" +
                "</form></p>\n" +
                "</div>\n" +
                "    <img src=\"https://pbs.twimg.com/profile_images/1311522537050836992/M84mBp63.jpg\" alt=\"\">\n" +
                "</body>\n" +
                "</html>";

        if(session.getAttribute("id") != null) {
            String logout = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>새 창</title>\n" +
                    "</head>\n" +
                    "    <body>\n" +
                    "       <div>\n" +
                    "        <h1>%s</h1>\n".format((String) session.getAttribute("id")) +
                    "        <a href=\"/logout\"><button>logout</button></a>\n" +
                    "       </div>\n" +
                    "        <img src=\"https://pbs.twimg.com/profile_images/1311522537050836992/M84mBp63.jpg\" alt=\"\">\n" +
                    "    </body>\n" +
                    "</html>";
            return logout;
        }
        //model.addAttribute("data","hi");
        return login;
    }

    @RequestMapping("login")
    public String login(HttpServletRequest request, LoginMember loginMember) {
        String id = loginMember.getID();
        String pw = loginMember.getPW();

        if(id == null || pw == null) {
            return "redirect:/";
        }

        MemberInfo memberInfo = Members.information.get(id);
        if(Members.information.containsKey(id) && Objects.equals(memberInfo.getPw(), pw)) {
            HttpSession session = request.getSession();
            session.setAttribute("id",id);
            session.setAttribute("pw",pw);
        }

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @RequestMapping("/listmembers")
    @ResponseBody
    public StringBuilder listMembers() {
        HashMap<String,MemberInfo> info = Members.information;
        Iterator<String> keys = info.keySet().iterator();
        StringBuilder result= new StringBuilder();
        while(keys.hasNext()) {
            String key = keys.next();
            result.append(" ").append(key);
        }
        return result;
    }
}
