package com.udd.udd.model;

import javax.persistence.*;

@Entity
@Table(name="reviewer_work")
public class ReviewerWork {

    @EmbeddedId
    private ReviewerWorkKey reviewerWorkKey;

    @ManyToOne
    @MapsId("reviewer_id")
    @JoinColumn(name="reviewer_id")
    private SystemUser systemUser;


    @ManyToOne
    @MapsId("work_id")
    @JoinColumn(name="work_id")
    private Work work;

    @Column
    private String authorComment;

    @Column
    private String editorComment;

    @Column
    private String date;

    @Column
    private String reccomendation;

    @Column
    private boolean reviewed;

    public ReviewerWork(){
        this.reviewed=false;
    }

    public ReviewerWorkKey getReviewerWorkKey() {
        return reviewerWorkKey;
    }

    public void setReviewerWorkKey(ReviewerWorkKey reviewerWorkKey) {
        this.reviewerWorkKey = reviewerWorkKey;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public String getAuthorComment() {
        return authorComment;
    }

    public void setAuthorComment(String authorComment) {
        this.authorComment = authorComment;
    }

    public String getEditorComment() {
        return editorComment;
    }

    public void setEditorComment(String editorComment) {
        this.editorComment = editorComment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReccomendation() {
        return reccomendation;
    }

    public void setReccomendation(String reccomendation) {
        this.reccomendation = reccomendation;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }
}
