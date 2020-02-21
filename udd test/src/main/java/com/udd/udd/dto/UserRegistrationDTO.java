package com.udd.udd.dto;

public class UserRegistrationDTO {

    private String username;
    private String name;
    private String surname;
    private String city;
    private String country;
    private String title;
    private String email;
    private long[] scienceAreas;
    private String password;
    private boolean asReviewer;

//    private String address;
//    private String phoneNum;

    public UserRegistrationDTO(){

    }

    public UserRegistrationDTO(String username, String name, String surname, String city, String country, String title, String email, long[] scienceAreas, String password, boolean asReviewer) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.title = title;
        this.email = email;
        this.scienceAreas = scienceAreas;
        this.password = password;
        this.asReviewer = asReviewer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long[] getScienceAreas() {
        return scienceAreas;
    }

    public void setScienceAreas(long[] scienceAreas) {
        this.scienceAreas = scienceAreas;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAsReviewer() {
        return asReviewer;
    }

    public void setAsReviewer(boolean asReviewer) {
        this.asReviewer = asReviewer;
    }
}