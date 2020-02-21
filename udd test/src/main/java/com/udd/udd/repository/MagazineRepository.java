package com.udd.udd.repository;


import com.udd.udd.model.Editor;
import com.udd.udd.model.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long> {

	Magazine findOneByName(String name);
    Magazine findOneById(Long id);
    List<Magazine> findAll();

    Magazine getMagazineByMainEditor(Editor editor);
    Set<Magazine> findAllByStatus(String status);
    Magazine findByEditorsContaining(Editor editor);
}
