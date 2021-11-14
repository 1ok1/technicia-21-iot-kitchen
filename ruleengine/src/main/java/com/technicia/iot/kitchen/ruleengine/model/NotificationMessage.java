package com.technicia.iot.kitchen.ruleengine.model;


public class NotificationMessage {
    private String title;

    public NotificationMessage() {
    }

    public NotificationMessage(String title, String body) {
        this.title = title;
        this.body = body;
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
