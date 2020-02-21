package com.udd.udd.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="magazine")
public class Magazine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String issn;

    @Column
    private String name;

    @Column
    private boolean openAccess;

    @Column
    private boolean active;

    @Column
    private String status; //request/fix/approved

    @OneToOne
    @JoinColumn(name = "maineditor_id", referencedColumnName = "id")
    private Editor mainEditor;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "magazine")
    private List<Editor> editors=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "magazine_reviewer",
            joinColumns = @JoinColumn(name = "magazine_id"),
            inverseJoinColumns = @JoinColumn(name = "reviewer_id"))
    private List<SystemUser> reviewers=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Work> works=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<ScienceArea> scienceAreas = new ArrayList<>();


    public Magazine(){

    }

    public Magazine(String issn, String name, boolean openAccess, boolean active, String status, Editor mainEditor, List<Editor> editors, List<SystemUser> reviewers, List<Work> works, List<ScienceArea> scienceAreas) {
        this.issn = issn;
        this.name = name;
        this.openAccess = openAccess;
        this.active = active;
        this.status = status;
        this.mainEditor = mainEditor;
        this.editors = editors;
        this.reviewers = reviewers;
        this.works = works;
        this.scienceAreas = scienceAreas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SystemUser> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<SystemUser> reviewers) {
        this.reviewers = reviewers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpenAccess() {
        return openAccess;
    }

    public void setOpenAccess(boolean openAccess) {
        this.openAccess = openAccess;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Editor getMainEditor() {
        return mainEditor;
    }

    public void setMainEditor(Editor mainEditor) {
        this.mainEditor = mainEditor;
    }

    public List<Editor> getEditors() {
        return editors;
    }

    public void setEditors(List<Editor> editors) {
        this.editors = editors;
    }

    public List<SystemUser> getReviewes() {
        return reviewers;
    }

    public void setReviewes(List<SystemUser> reviewes) {
        this.reviewers = reviewes;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

    public List<ScienceArea> getScienceAreas() {
        return scienceAreas;
    }

    public void setScienceAreas(List<ScienceArea> scienceAreas) {
        this.scienceAreas = scienceAreas;
    }
}
