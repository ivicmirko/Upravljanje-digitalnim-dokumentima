package com.udd.udd.repository;

import com.udd.udd.model.ScienceArea;
import com.udd.udd.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

    SystemUser findByUsername(String username);
    SystemUser findOneByEmail(String email);
    SystemUser findOneById(Long id);

//    @Query(value="SELECT  * FROM systemuser u, user_areas usa WHERE u.id=usa.user_id and u.reviewer='yes' and usa.area_id IN :areas",nativeQuery = true)
//    Set<SystemUser> findReviewersByScienceAreas(@Param("areas") Set<Long> areas);
    Set<SystemUser> findSystemUsersByReviewerAndScienceAreasIn(String reviewer, Set<ScienceArea> areas);
    Set<SystemUser> findSystemUsersByReviewerAndScienceAreasContaining(String reviewer, ScienceArea area);
    List<SystemUser> findAllByReviewer(String isReviewer);


}
