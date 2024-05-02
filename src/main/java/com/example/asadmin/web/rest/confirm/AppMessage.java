package com.example.asadmin.web.rest.confirm;

public class AppMessage {
    private String message;

    public AppMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}