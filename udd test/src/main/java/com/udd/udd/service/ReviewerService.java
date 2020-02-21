package com.udd.udd.service;


import com.udd.udd.model.SystemUser;

import java.util.Set;

public interface ReviewerService {

    public Set<SystemUser> findReviewersOfAreas(String[] areas);

}
