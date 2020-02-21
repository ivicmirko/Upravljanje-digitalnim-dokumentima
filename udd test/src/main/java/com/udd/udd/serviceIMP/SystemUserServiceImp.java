package com.udd.udd.serviceIMP;

import com.udd.udd.model.ScienceArea;
import com.udd.udd.model.SystemUser;
import com.udd.udd.repository.SystemUserRepository;
import com.udd.udd.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SystemUserServiceImp implements SystemUserService {

    @Autowired
    SystemUserRepository systemUserRepository;

    @Override
    public SystemUser findByEmail(String email) {
        return this.systemUserRepository.findOneByEmail(email);
    }

    @Override
    public SystemUser saveSystemUser(SystemUser systemUser) {
        return this.systemUserRepository.saveAndFlush(systemUser);
    }

    @Override
    public List<SystemUser> findAll() {
        return this.systemUserRepository.findAll();
    }

    @Override
    public int checkCredentials(String username, String email) {
        SystemUser systemUser=null;
        int retval=0;
        systemUser=this.systemUserRepository.findByUsername(username);
        if(systemUser != null){
            retval+=1;
        }

        systemUser=this.systemUserRepository.findOneByEmail(email);
        if(systemUser != null){
            retval+=0;
        }

        return retval;
    }

    @Override
    public Set<SystemUser> findReviewersByScienceArea(ScienceArea scienceArea) {
        return this.systemUserRepository.findSystemUsersByReviewerAndScienceAreasContaining("yes",scienceArea);
    }

    @Override
    public SystemUser findByUsername(String username) {
        return this.systemUserRepository.findByUsername(username);
    }

    @Override
    public void deleteSystemUser(SystemUser systemUser) {
        this.systemUserRepository.delete(systemUser);
    }

    @Override
    public SystemUser findOneById(Long id) {
        return this.systemUserRepository.findOneById(id);
    }

    @Override
    public List<SystemUser> findAllreviewers() {
        List<SystemUser> systemUsers=this.systemUserRepository.findAllByReviewer("yes");
        return  systemUsers;
    }
}
