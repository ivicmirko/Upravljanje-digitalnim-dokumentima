package com.udd.udd.controller;


import com.udd.udd.dto.MagazineDTO;
import com.udd.udd.dto.NewMagazineDTO;
import com.udd.udd.dto.ScienceAreaDTO;
import com.udd.udd.model.Editor;
import com.udd.udd.model.Magazine;
import com.udd.udd.model.ScienceArea;
import com.udd.udd.model.SystemUser;
import com.udd.udd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/common")
public class CommonController {

    @Autowired
    private ScienceAreaService scienceAreaService;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private ReviewerService reviewerService;

    @Autowired
    private EditorService editorService;

    @Autowired
    private MagazineService magazineService;

    @GetMapping(path = "/getScienceAreas",
    produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody ResponseEntity getScienceAreeas(){
        List<ScienceArea> scienceAreas=this.scienceAreaService.findAll();
        List<ScienceAreaDTO> scienceAreaDTOS=new ArrayList<>();
        for(ScienceArea sa:scienceAreas){
            ScienceAreaDTO scienceAreaDTO=new ScienceAreaDTO();
            scienceAreaDTO.setId(sa.getId());
            scienceAreaDTO.setName(sa.getName());
            scienceAreaDTOS.add(scienceAreaDTO);
        }
        return new ResponseEntity(scienceAreaDTOS, HttpStatus.OK);
    }

    @PostMapping(path="/getSAReviewers",
    consumes = "application/json",
    produces = "application/json")
    public @ResponseBody ResponseEntity getReviewers(@RequestBody String[] choosenAreas){
        Set<SystemUser> reviewers=this.reviewerService.findReviewersOfAreas(choosenAreas);

        return new ResponseEntity(reviewers,HttpStatus.OK);
    }

    @GetMapping(path="/getFreeEditors",
    produces="application/json")
    public @ResponseBody ResponseEntity getFreeEditors(){
        Set<Editor> editors=this.editorService.findFreeEditors();

        return new ResponseEntity(editors,HttpStatus.OK);
    }

    @GetMapping(path="/getMyMagazine",
            produces = "application/json")
    public @ResponseBody ResponseEntity getMyMagazine(){

        Editor editor=this.editorService.findEditorByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if(editor==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Magazine magazine=this.magazineService.getMagazineByMainEditor(editor);

        if(magazine==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(magazine,HttpStatus.OK);
    }

    @PostMapping(path="/sendCorrection",
    consumes = "application/json")
    public @ResponseBody ResponseEntity fixMagazine(@RequestBody NewMagazineDTO magazineDTO){

        Magazine magazine=this.magazineService.findOneById(magazineDTO.getId());
        magazine.setStatus("request");
        magazine.setIssn(magazineDTO.getIssn());
        magazine.setName(magazineDTO.getName());
        if(magazineDTO.getMembershipType().equals("1")){
            magazine.setOpenAccess(true);
        }else if(magazineDTO.getMembershipType().equals("2")){
            magazine.setOpenAccess(false);
        }
        this.magazineService.saveMagazine(magazine);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path="/getAllMagazines",
    produces = "application/json")
    public @ResponseBody ResponseEntity getAllMagazines(){
        List<Magazine> magazines=this.magazineService.findAll();

        List<MagazineDTO> magazineDTOS=new ArrayList<>();

        for(Magazine magazine:magazines){
            MagazineDTO magazineDTO=new MagazineDTO();
            magazineDTO.setId(magazine.getId());
            magazineDTO.setName(magazine.getName());
            magazineDTOS.add(magazineDTO);
        }

        return new ResponseEntity(magazineDTOS,HttpStatus.OK);
    }
}
