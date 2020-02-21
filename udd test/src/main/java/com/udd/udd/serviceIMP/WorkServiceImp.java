package com.udd.udd.serviceIMP;


import com.udd.udd.model.*;
import com.udd.udd.repository.EditorRepository;
import com.udd.udd.repository.ReviewerWorkRepository;
import com.udd.udd.repository.SystemUserRepository;
import com.udd.udd.repository.WorkRepository;
import com.udd.udd.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class WorkServiceImp implements WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private EditorRepository editorRepository;

    @Autowired
    private ReviewerWorkRepository reviewerWorkRepository;

    @Override
    public Work saveWork(Work work) {
        return this.workRepository.save(work);
    }

    @Override
    public List<Work> findAll() {
        return this.workRepository.findAll();
    }

    @Override
    public Work findById(Long id) {
        return this.workRepository.findOneById(id);
    }

    @Override
    public List<Work> findByAuthor(Author author) {
        return this.workRepository.findAllByAuthor(author);
    }

    @Override
    public List<Work> findByStatus(WorkStatus workStatus) {
        return this.workRepository.findAllByWorkStatus(workStatus);
    }

    @Override
    public List<Work> findByMagazineAndStatus(Magazine magazine, WorkStatus workStatus) {
        return this.workRepository.findAllByMagazineAndWorkStatus(magazine,workStatus);
    }

    @Override
    public void deleteWork(Work work) {
        this.workRepository.delete(work);
    }

    @Override
    public List<Work> findByAuthorAndStatus(Author author, WorkStatus workStatus) {
        return this.workRepository.findAllByAuthorAndWorkStatus(author,workStatus);
    }

    @Override
    public List<Work> findByEditorAndStatus(Editor editor, WorkStatus workStatus) {
//        List<WorkES> retVal=new ArrayList<>();
//        List<WorkES> temp=this.workRepository.findAllByWorkStatus(workStatus);
//        for(WorkES work:temp){
//            System.out.println("Urednik="+work.getEditor().getUsername());
//            if(work.getEditor().getUsername().equals(editor.getUsername())){
//                retVal.add(work);
//            }
//        }
        List<Work> retVal=this.workRepository.findAllByEditorAndWorkStatus(editor,workStatus);
        List<Work> temp=retVal;
        System.out.println("AA="+temp.size());
        return retVal;
    }

    @Override
    public Work setEditorOfSA(Work work) {
        System.out.println("USAO U DODELU UREDNIKA NAUCNE OBLASTI");

        Magazine magazine=new Magazine();
        try{
            magazine=work.getMagazine();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        ScienceArea scienceArea=work.getScienceArea();
        List<Editor> editors=this.editorRepository.findAllByMagazineAndScienceAreasIsContaining(magazine,scienceArea);

        Editor editor=new Editor();
        if(editors.size()==0){
            editor=magazine.getMainEditor();
        }else{
            editor=editors.get(0);
            for(Editor e:editors){
                if(!e.getUsername().equals(magazine.getMainEditor().getUsername())){
                    editor=e;
                }
            }
            for(Editor e:editors){
                if(e.getWorks().size()<editor.getWorks().size() && !e.getUsername().equals(magazine.getMainEditor().getUsername())){
                    editor=e;
                }
            }
        }


        work.setEditor(editor);
//        if(editors.size()>0){
//            work.setEditor(editor);
//        }else{
//            work.setEditor(magazine.getMainEditor());
//        }
        work=workRepository.save(work);
        return work;
    }


    @Override
    public boolean isEnoughReviewers(Work work) {

        ScienceArea scienceArea=work.getScienceArea();

        Set<SystemUser> reviewers=new HashSet<>();
        reviewers=this.systemUserRepository.findSystemUsersByReviewerAndScienceAreasContaining("yes",scienceArea);
        Magazine magazine=work.getMagazine();
        //proveriti da li strogo manje od 2, a sta raditi ako ima 1
        if(reviewers.size()<2){

            SystemUser systemUser=this.systemUserRepository.findByUsername(work.getEditor().getUsername());

            ReviewerWork reviewerWork=new ReviewerWork();
            reviewerWork.setWork(work);
            reviewerWork.setSystemUser(systemUser);
            this.reviewerWorkRepository.save(reviewerWork);
            return false;
        }else{
            return true;
        }

    }
}
