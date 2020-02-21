package com.udd.udd.service;


import com.udd.udd.model.ReviewerWork;
import com.udd.udd.model.ReviewerWorkKey;
import com.udd.udd.model.SystemUser;
import com.udd.udd.model.Work;

import java.util.Set;

public interface ReviewerWorkService {

    ReviewerWork saveReviewWork(ReviewerWork reviewerWork);

    Set<Work> getWorksForReviewing(SystemUser systemUser);
    ReviewerWork findByKey(ReviewerWorkKey reviewerWorkKey);
    Set<ReviewerWork> findAllByWork(Work work);
}
