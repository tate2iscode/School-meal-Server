package com.example.java_spring_server_gradle.Login.Register;

public class RegisterMember {
    public String getRegisterID() {
        return RegisterID;
    }

    public void setRegisterID(String registerID) {
        RegisterID = registerID;
    }

    public String getRegisterPW() {
        return RegisterPW;
    }

    public void setRegisterPW(String registerPW) {
        RegisterPW = registerPW;
    }

    public String getRegisterName() {
        return RegisterName;
    }

    public void setRegisterName(String registerName) {
        RegisterName = registerName;
    }

    String RegisterID;
    String RegisterPW;
    String RegisterName;
}
