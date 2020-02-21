package com.udd.udd.dto;

public class MessageResponeDTO {

    private String message;

    public MessageResponeDTO(){

    }

    public MessageResponeDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
