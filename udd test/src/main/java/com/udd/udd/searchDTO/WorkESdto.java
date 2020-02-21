package com.udd.udd.searchDTO;

import com.udd.udd.modelES.WorkES;

public class WorkESdto {

    private Long id;
    private String title;
    private String workAbstract;
    private String highlight;
    private boolean openAcess;
    private String magazineName;
    private String authors;
    public WorkESdto(){

    }

    public boolean isOpenAcess() {
        return openAcess;
    }

    public void setOpenAcess(boolean openAcess) {
        this.openAcess = openAcess;
    }

    public String getMagazineName() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkAbstract() {
        return workAbstract;
    }

    public void setWorkAbstract(String workAbstract) {
        this.workAbstract = workAbstract;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }
}
