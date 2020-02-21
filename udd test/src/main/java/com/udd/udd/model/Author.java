package com.udd.udd.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="author")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName="id")
public class Author extends SystemUser {

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "author")
    private List<Work> works=new ArrayList<>();

    public Author(){
        super();
    }

    public  Author(SystemUser systemUser){
        this.setId(systemUser.getId());
        this.setName(systemUser.getName());
        this.setSurname(systemUser.getSurname());
        this.setReviewer("no");
        this.setEnabled(systemUser.isEnabled());
        this.setPassword(systemUser.getPassword());
        this.setUsername(systemUser.getUsername());
        this.setEmail(systemUser.getEmail());
        this.setCity((systemUser.getCity()));
        this.setCountry(systemUser.getCountry());
        this.setLongitude(systemUser.getLongitude());
        this.setLatitude(systemUser.getLatitude());
        List<Authority> authorities=(List<Authority>) systemUser.getAuthorities();
        this.setAuthorities(authorities);
        this.setScienceAreas(systemUser.getScienceAreas());
        this.setTitle(systemUser.getTitle());
        this.setRemoved(systemUser.isRemoved());
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }
}
