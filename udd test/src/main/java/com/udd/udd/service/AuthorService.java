package com.udd.udd.service;



import com.udd.udd.model.Author;
import com.udd.udd.model.Work;

import java.util.List;

public interface AuthorService {

    public Author findOneById(Long id);
    public Author findOneByUsername(String username);
    public List<Work> findWorksByAuthor(Author author);
    public Author save(Author author);
}
