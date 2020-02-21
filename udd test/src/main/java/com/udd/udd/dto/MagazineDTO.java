package com.udd.udd.dto;

import com.udd.udd.model.Magazine;

public class MagazineDTO {

    private Long id;
    private String name;

    public MagazineDTO(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
