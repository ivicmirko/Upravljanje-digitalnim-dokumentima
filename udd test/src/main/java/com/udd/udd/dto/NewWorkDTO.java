package com.udd.udd.dto;

public class NewWorkDTO {

    private Long id;
    private String title;
    private String workAbstract;
    private String keyTerms;
    private Long scienceArea;

    public NewWorkDTO(){

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

    public String getKeyTerms() {
        return keyTerms;
    }

    public void setKeyTerms(String keyTerms) {
        this.keyTerms = keyTerms;
    }

    public Long getScienceArea() {
        return scienceArea;
    }

    public void setScienceArea(Long scienceAreas) {
        this.scienceArea = scienceAreas;
    }
}
