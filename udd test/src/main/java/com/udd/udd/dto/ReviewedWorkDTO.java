package com.udd.udd.dto;

public class ReviewedWorkDTO {
    private Long id;
    private String authorComment;
    private String date;
    private String editorComment;
    private String reviewer;
    private String recommendation;

    public ReviewedWorkDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorComment() {
        return authorComment;
    }

    public void setAuthorComment(String authorComment) {
        this.authorComment = authorComment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEditorComment() {
        return editorComment;
    }

    public void setEditorComment(String editorComment) {
        this.editorComment = editorComment;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
