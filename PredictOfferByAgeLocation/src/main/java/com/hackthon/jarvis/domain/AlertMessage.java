package com.hackthon.jarvis.domain;

import com.google.gson.Gson;

public class AlertMessage {

    String to;
    String subject;
    String body;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
