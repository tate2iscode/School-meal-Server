package com.example.java_spring_server_gradle.chatbot;

public class Response {
    String version;
    OutPuts template;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public OutPuts getTemplate() {
        return template;
    }

    public void setTemplate(OutPuts template) {
        this.template = template;
    }
}
