package com.udd.udd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="editor")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName="id")
public class Editor extends SystemUser {

    @ManyToOne
    @JoinColumn(name="magazine_id")
    @JsonIgnore
    private Magazine magazine;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "editor")
    private List<Work> works=new ArrayList<>();


    public Editor(){
        super();
    }


    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }
}
