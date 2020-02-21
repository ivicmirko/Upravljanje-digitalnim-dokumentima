package com.udd.udd.serviceIMP;


import com.udd.udd.model.*;
import com.udd.udd.repository.ReviewerWorkRepository;
import com.udd.udd.repository.WorkRepository;
import com.udd.udd.service.ReviewerWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReviewerWorkServiceImp implements ReviewerWorkService {

    @Autowired
    ReviewerWorkRepository reviewerWorkRepository;

    @Autowired
    WorkRepository workRepository;

    @Override
    public ReviewerWork saveReviewWork(ReviewerWork reviewerWork) {
        return this.reviewerWorkRepository.save(reviewerWork);
    }

    @Override
    public Set<Work> getWorksForReviewing(SystemUser systemUser) {
        Set<ReviewerWork> revWorks=this.reviewerWorkRepository.findAllBySystemUser(systemUser);
        Set<Work> retVal=new HashSet<>();
        for(ReviewerWork rw:revWorks){
            if(rw.getWork().getWorkStatus().equals(WorkStatus.reviewing) && !rw.isReviewed()){
                retVal.add(rw.getWork());
            }
        }
        return retVal;
    }

    @Override
    public ReviewerWork findByKey(ReviewerWorkKey reviewerWorkKey) {
        return this.reviewerWorkRepository.findOneByReviewerWorkKey(reviewerWorkKey);
    }

    @Override
    public Set<ReviewerWork> findAllByWork(Work work) {
        return this.reviewerWorkRepository.findAllByWork(work);
    }
}
