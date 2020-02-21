package com.udd.udd.modelES;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Document(indexName = "nc",type = "work")
public class WorkES {

    @Id
    private Long id;

//    @MultiField(mainField = @Field(type = FieldType.Text, fielddata = true),
//    otherFields = {
//            @MultiField(mainField = @Field(type = FieldType.Text, name = "sr", fielddata = true),
//                    otherFields = {
//                            @InnerField(suffix = "verbatim",type = FieldType.Text, analyzer = "serbian")
//                    }
//            )
//    })
    @Field(type = FieldType.Text, searchAnalyzer = "serbian",analyzer = "serbian")
    private String title;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian")
    private String keyTerms;

    @Field(type=FieldType.Text)
    private String authors;

    @Field(type=FieldType.Text)
    private String workAbstract;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian")
    private String workContent;

    @Field(type=FieldType.Long)
    private Long scienceArea;

    @Field(type=FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian")
    private String magazineName;

    @Field(type = FieldType.Text)
    private String status;

//    @MultiField(
//            mainField = @Field(type = FieldType.Text, fielddata = true),
//            otherFields = {
//                    @InnerField(suffix = "verbatim", type = FieldType.Text,searchAnalyzer = "serbian")
//            }
//    )


    public WorkES(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getKeyTerms() {
        return keyTerms;
    }

    public void setKeyTerms(String keyTerms) {
        this.keyTerms = keyTerms;
    }

    public String getMagazineName() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }

    public Long getScienceArea() {
        return scienceArea;
    }

    public void setScienceArea(Long scienceArea) {
        this.scienceArea = scienceArea;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getWorkAbstract() {
        return workAbstract;
    }

    public void setWorkAbstract(String workAbstract) {
        this.workAbstract = workAbstract;
    }
}

