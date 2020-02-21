package com.udd.udd.repository;

import com.udd.udd.model.CoAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoAuthorRepository extends JpaRepository<CoAuthor, Long> {
}
