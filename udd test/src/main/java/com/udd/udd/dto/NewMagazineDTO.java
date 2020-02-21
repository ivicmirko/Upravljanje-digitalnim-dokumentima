package com.udd.udd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.List;

public class NewMagazineDTO {

        private Long id;

        private String name;

        private String issn;

        private List<String> scienceAreas=new ArrayList<>();

        private String membershipType;

        private String username;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<String> editors=new ArrayList<>();

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<String> reviewers=new ArrayList<>();

        public NewMagazineDTO(){

        }

    public NewMagazineDTO(String name, String issn, List<String> scienceAreas, String membershipType, String username, List<String> editors, List<String> reviewers) {
        this.name = name;
        this.issn = issn;
        this.scienceAreas = scienceAreas;
        this.membershipType = membershipType;
        this.username = username;
        this.editors = editors;
        this.reviewers = reviewers;
    }

    public List<String> getEditors() {
        return editors;
    }

    public void setEditors(List<String> editors) {
        this.editors = editors;
    }

    public List<String> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<String> reviewers) {
        this.reviewers = reviewers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public List<String> getScienceAreas() {
        return scienceAreas;
    }

    public void setScienceAreas(List<String> scienceAreas) {
        this.scienceAreas = scienceAreas;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
