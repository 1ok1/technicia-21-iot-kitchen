package com.technicia.iot.kitchen.ruleengine.model;


public class NotificationMessage {
    private String title;
    private String token;

    public NotificationMessage() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public NotificationMessage(String title, String body, String token) {
        this.title = title;
        this.body = body;
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private String body;
}
