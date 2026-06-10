package com.example.demo;

public class ErrorResponse{

    private String message;
    private String name;

    public ErrorResponse(String message, String name) {
        this.message = message;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }
}
