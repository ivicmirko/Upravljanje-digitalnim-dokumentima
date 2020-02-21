package com.udd.udd.searchDTO;

public class AdvancedSearchDTO {

    private String magazineName;
    private boolean magazineCheck;

    private String title;
    private boolean titleCheck;

    private String keyTerms;
    private boolean keyTermsCheck;

    private String content;
    private boolean contentCheck;

    private String authors;
    private boolean authorsCheck;

    private String scienceAreass;

    public AdvancedSearchDTO(){

    }

    public String getMagazineName() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }

    public boolean isMagazineCheck() {
        return magazineCheck;
    }

    public void setMagazineCheck(boolean magazineCheck) {
        this.magazineCheck = magazineCheck;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTitleCheck() {
        return titleCheck;
    }

    public void setTitleCheck(boolean titleCheck) {
        this.titleCheck = titleCheck;
    }

    public String getKeyTerms() {
        return keyTerms;
    }

    public void setKeyTerms(String keyTerms) {
        this.keyTerms = keyTerms;
    }

    public boolean isKeyTermsCheck() {
        return keyTermsCheck;
    }

    public void setKeyTermsCheck(boolean keyTermsCheck) {
        this.keyTermsCheck = keyTermsCheck;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isContentCheck() {
        return contentCheck;
    }

    public void setContentCheck(boolean contentCheck) {
        this.contentCheck = contentCheck;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public boolean isAuthorsCheck() {
        return authorsCheck;
    }

    public void setAuthorsCheck(boolean authorsCheck) {
        this.authorsCheck = authorsCheck;
    }

    public String getScienceAreass() {
        return scienceAreass;
    }

    public void setScienceAreass(String scienceAreass) {
        this.scienceAreass = scienceAreass;
    }
}
