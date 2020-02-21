package com.udd.udd.repository;


import com.udd.udd.model.ReviewerWork;
import com.udd.udd.model.ReviewerWorkKey;
import com.udd.udd.model.SystemUser;
import com.udd.udd.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReviewerWorkRepository extends JpaRepository<ReviewerWork,Long> {

    Set<ReviewerWork> findAllBySystemUser(SystemUser systemUser);
    ReviewerWork findOneByReviewerWorkKey(ReviewerWorkKey reviewerWorkKey);
    Set<ReviewerWork> findAllByWork(Work work);
}
