package com.udd.udd.service;



import com.udd.udd.model.Editor;
import com.udd.udd.model.Magazine;
import com.udd.udd.model.ScienceArea;

import java.util.List;
import java.util.Set;

public interface EditorService {

    public Set<Editor> findFreeEditors();
    public Editor findEditorByUsername(String username);
    public List<Editor> findByMagazineAndScienceArea(Magazine magazine, ScienceArea scienceArea);
}
