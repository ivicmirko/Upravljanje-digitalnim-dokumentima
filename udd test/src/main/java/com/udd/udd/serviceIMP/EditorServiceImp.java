package com.udd.udd.serviceIMP;


import com.udd.udd.model.Editor;
import com.udd.udd.model.Magazine;
import com.udd.udd.model.ScienceArea;
import com.udd.udd.repository.EditorRepository;
import com.udd.udd.repository.MagazineRepository;
import com.udd.udd.repository.SystemUserRepository;
import com.udd.udd.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EditorServiceImp implements EditorService {


    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private MagazineRepository magazineRepository;

    @Autowired
    private EditorRepository editorRepository;

    @Override
    public Set<Editor> findFreeEditors() {
        return this.editorRepository.findEditorsByMagazineIsNull();
    }

    @Override
    public Editor findEditorByUsername(String username) {
        return this.editorRepository.findOneByUsername(username);
    }

    @Override
    public List<Editor> findByMagazineAndScienceArea(Magazine magazine, ScienceArea scienceArea) {
        return this.editorRepository.findAllByMagazineAndScienceAreasIsContaining(magazine,scienceArea);
    }
}
