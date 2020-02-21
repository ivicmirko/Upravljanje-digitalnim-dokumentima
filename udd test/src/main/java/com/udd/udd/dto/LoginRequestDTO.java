package com.udd.udd.dto;

public class LoginRequestDTO {

    private String username;
    private String password;

    public LoginRequestDTO() {
        // TODO Auto-generated constructor stub
    }

    public LoginRequestDTO(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
