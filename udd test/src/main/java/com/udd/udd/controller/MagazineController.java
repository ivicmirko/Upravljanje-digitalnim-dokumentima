//package com.udd.udd.controller;
//
//import com.project.naucnacentrala.dto.MessageResponeDTO;
//import com.project.naucnacentrala.dto.NewMagazineDTO;
//import com.project.naucnacentrala.model.Magazine;
//import com.project.naucnacentrala.service.MagazineService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping(path = "/magazine")
//public class MagazineController {
//
//    @Autowired
//    private MagazineService magazineService;
//
//    @PostMapping(path = "/newMagazine",
//    produces = "application/json",
//    consumes = "application/json")
//    public @ResponseBody
//    ResponseEntity newMagazine(@RequestBody NewMagazineDTO magazineDTO){
//
//
//        if(!magazineService.checkNameUniqueness(magazineDTO.getName())){
//            return new ResponseEntity(new MessageResponeDTO("Vec postoji ceasopis s ovim imenom"),HttpStatus.BAD_REQUEST);
//        }
//
//        Magazine magazine=new Magazine();
//        magazine=magazineService.addNewMagazine(magazineDTO);
//        if(magazine == null){
//            return new ResponseEntity(new MessageResponeDTO("Neuspesno dodavenje casopisa"),HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity(magazine,HttpStatus.OK);
//    }
//
//    @PostMapping(path="/addEditorialBoard",
//    consumes = "application/json",
//    produces="application/json")
//    public @ResponseBody ResponseEntity addEditorialBoard(@RequestBody NewMagazineDTO magazineDTO){
//
//        Magazine magazine=this.magazineService.addEditorialBoard(magazineDTO);
//        if(magazine==null){
//            return new ResponseEntity(new MessageResponeDTO("Neuspesno dodavenje urednickog odbora"),HttpStatus.BAD_REQUEST);
//        }
//
//
//        return new ResponseEntity(HttpStatus.OK);
//    }
//}
