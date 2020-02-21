package com.udd.udd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name=" sciencearea")
public class ScienceArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private int code;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "scienceAreas")
    private List<SystemUser> users;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "scienceArea")
    @JsonIgnore
    private List<Work> works=new ArrayList<>();

    public ScienceArea(){

    }

    public ScienceArea(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SystemUser> getUsers() {
        return users;
    }

    public void setUsers(List<SystemUser> users) {
        this.users = users;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }
}
