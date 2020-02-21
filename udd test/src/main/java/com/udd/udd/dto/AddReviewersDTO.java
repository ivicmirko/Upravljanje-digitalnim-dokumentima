package com.udd.udd.dto;

import java.util.ArrayList;
import java.util.List;

public class AddReviewersDTO {
    private List<Long> reviewers=new ArrayList<>();

    public AddReviewersDTO(){

    }

    public List<Long> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<Long> reviewers) {
        this.reviewers = reviewers;
    }
}
