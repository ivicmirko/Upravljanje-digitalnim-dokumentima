package com.udd.udd.repository;

import com.udd.udd.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    Work findOneById(Long id);
    List<Work> findAll();
    List<Work> findAllByWorkStatus(WorkStatus workStatus);
    List<Work> findAllByAuthor(Author author);
    List<Work> findAllByMagazine(Magazine magazine);
    List<Work> findAllByMagazineAndWorkStatus(Magazine magazine, WorkStatus workStatus);
    List<Work> findAllByAuthorAndWorkStatus(Author author, WorkStatus workStatus);
    List<Work> findAllByEditorAndWorkStatus(Editor editor, WorkStatus workStatus);
    List<Work> findAllByEditor(Editor editor);
}
