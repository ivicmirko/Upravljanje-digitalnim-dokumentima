package com.udd.udd.serviceIMP;

import com.udd.udd.model.ScienceArea;
import com.udd.udd.repository.ScienceAreaRepository;
import com.udd.udd.service.ScienceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScienceAreaServiceImp implements ScienceAreaService {

	@Autowired
	ScienceAreaRepository scienceAreaRepository;

	@Override
	public List<ScienceArea> findAll(){
		return scienceAreaRepository.findAll();
	}
	
	public ScienceArea findOneById(long id) {
		return scienceAreaRepository.findOneById(id);
	}
}
