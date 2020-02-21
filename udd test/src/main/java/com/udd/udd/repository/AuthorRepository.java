package com.udd.udd.repository;

import com.udd.udd.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    Author findOneById(Long id);
    Author findOneByUsername(String username);

}
