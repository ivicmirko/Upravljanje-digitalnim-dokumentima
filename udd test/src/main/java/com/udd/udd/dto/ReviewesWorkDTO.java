package com.udd.udd.dto;

import java.util.ArrayList;
import java.util.List;

public class ReviewesWorkDTO {

    private List<ReviewedWorkDTO> reviewedWorkDTOS=new ArrayList<>();
    private String answer;

    public ReviewesWorkDTO(){

    }

    public List<ReviewedWorkDTO> getReviewedWorkDTOS() {
        return reviewedWorkDTOS;
    }

    public void setReviewedWorkDTOS(List<ReviewedWorkDTO> reviewedWorkDTOS) {
        this.reviewedWorkDTOS = reviewedWorkDTOS;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
