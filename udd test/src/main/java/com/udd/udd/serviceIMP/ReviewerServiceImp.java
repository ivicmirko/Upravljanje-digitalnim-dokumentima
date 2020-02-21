package com.udd.udd.serviceIMP;

import com.udd.udd.model.ScienceArea;
import com.udd.udd.model.SystemUser;
import com.udd.udd.repository.ScienceAreaRepository;
import com.udd.udd.repository.SystemUserRepository;
import com.udd.udd.service.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReviewerServiceImp implements ReviewerService {

    @Autowired
    private ScienceAreaRepository scienceAreaRepository;

    @Autowired
    private SystemUserRepository systemUserRepository;


    @Override
    public Set<SystemUser> findReviewersOfAreas(String[] areas) {
        Set<ScienceArea> areass=new HashSet<>();
        for(int i=0;i<areas.length;i++){
            ScienceArea scienceArea=this.scienceAreaRepository.findOneById(Long.parseLong(areas[i]));
            areass.add(scienceArea);
        }
        return systemUserRepository.findSystemUsersByReviewerAndScienceAreasIn("yes",areass);
    }
}
