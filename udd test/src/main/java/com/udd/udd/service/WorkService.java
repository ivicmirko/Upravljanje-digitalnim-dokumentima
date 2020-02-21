package com.udd.udd.service;


import com.udd.udd.model.*;

import java.util.List;

public interface WorkService {

    public Work saveWork(Work work);
    public List<Work> findAll();
    public Work findById(Long id);
    public List<Work> findByAuthor(Author author);
    public List<Work> findByStatus(WorkStatus workStatus);
    public List<Work> findByMagazineAndStatus(Magazine magazine, WorkStatus workStatus);
    public void deleteWork(Work work);
    public List<Work> findByAuthorAndStatus(Author author, WorkStatus workStatus);
    public List<Work> findByEditorAndStatus(Editor editor, WorkStatus workStatus);
    public boolean isEnoughReviewers(Work work);
    public Work setEditorOfSA(Work work);
}
