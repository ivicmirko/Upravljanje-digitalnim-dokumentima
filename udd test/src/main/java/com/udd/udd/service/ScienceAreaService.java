package com.udd.udd.service;


import com.udd.udd.model.ScienceArea;

import java.util.List;

public interface ScienceAreaService {

    public List<ScienceArea> findAll();

    public ScienceArea findOneById(long id);
}
