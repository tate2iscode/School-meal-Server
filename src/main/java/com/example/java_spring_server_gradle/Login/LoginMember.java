package com.example.java_spring_server_gradle.Login;

public class LoginMember {
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPW() {
        return PW;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }

    String ID;
    String PW;
}
