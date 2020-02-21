package com.udd.udd.repository;


import com.udd.udd.model.ScienceArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScienceAreaRepository extends JpaRepository<ScienceArea, Long> {

	List<ScienceArea> findAll();
	ScienceArea findOneById(Long id);
}
