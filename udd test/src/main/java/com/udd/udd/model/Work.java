package com.udd.udd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="work")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String workAbstract;

    @Column
    private String keyTerms;

    @Column
    private String proposalWorkPath;

    @Column
    private String workPath;

    @Column
    private WorkStatus workStatus;

    @ManyToOne
    @JoinColumn(name="science_area_id")
    private ScienceArea scienceArea;

    @ManyToOne
    @JoinColumn(name="author_id")
    @JsonIgnore
    private Author author;

    @ManyToOne
    @JoinColumn(name = "magazine_id")
    @JsonIgnore
    private Magazine magazine;

    @ManyToOne
    @JoinColumn(name="editor_id")
    @JsonIgnore
    private Editor editor;

    @Column
    private String answer;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "work_reviewer",
//            joinColumns = @JoinColumn(name = "work_id"),
//            inverseJoinColumns = @JoinColumn(name = "reviewer_id"))
//    private List<SystemUser> reviewers=new ArrayList<>();

    @OneToMany(mappedBy = "work")
    private List<ReviewerWork> reviewerWorks=new ArrayList<>();

    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL)
    private List<CoAuthor> coAuthors=new ArrayList<>();

    public Work(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyTerms() {
        return keyTerms;
    }

    public void setKeyTerms(String keyTerms) {
        this.keyTerms = keyTerms;
    }

    public WorkStatus getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(WorkStatus workStatus) {
        this.workStatus = workStatus;
    }

    public ScienceArea getScienceArea() {
        return scienceArea;
    }

    public void setScienceArea(ScienceArea scienceArea) {
        this.scienceArea = scienceArea;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getProposalWorkPath() {
        return proposalWorkPath;
    }

    public void setProposalWorkPath(String proposalWorkPath) {
        this.proposalWorkPath = proposalWorkPath;
    }

    public String getWorkAbstract() {
        return workAbstract;
    }

    public void setWorkAbstract(String workAbstract) {
        this.workAbstract = workAbstract;
    }

    public String getWorkPath() {
        return workPath;
    }

    public void setWorkPath(String workPath) {
        this.workPath = workPath;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public List<CoAuthor> getCoAuthors() {
        return coAuthors;
    }

    public void setCoAuthors(List<CoAuthor> coAuthors) {
        this.coAuthors = coAuthors;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

//    public List<SystemUser> getReviewers() {
//        return reviewers;
//    }
//
//    public void setReviewers(List<SystemUser> reviewers) {
//        this.reviewers = reviewers;
//    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<ReviewerWork> getReviewerWorks() {
        return reviewerWorks;
    }

    public void setReviewerWorks(List<ReviewerWork> reviewerWorks) {
        this.reviewerWorks = reviewerWorks;
    }
}
