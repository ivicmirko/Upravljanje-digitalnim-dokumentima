package com.udd.udd.controller;


import com.udd.udd.dto.*;
import com.udd.udd.jwt.JwtTokenProvider;
import com.udd.udd.model.*;
import com.udd.udd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="/work")
public class WorkController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    SystemUserService systemUserService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ScienceAreaService scienceAreaService;

    @Autowired
    private MagazineService magazineService;

    @Autowired
    private WorkService workService;

    @Autowired
    private EditorService editorService;



    @GetMapping(path = "/setWorkMagazine/{magazineId}",
            produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody
    ResponseEntity<?> setWorkMagazine(@PathVariable Long magazineId) {

        Magazine magazine=this.magazineService.findOneById(magazineId);
        List<ScienceArea> scienceAreas=magazine.getScienceAreas();

        List<ScienceAreaDTO> scienceAreaDTOS=new ArrayList<>();

        for(ScienceArea sa:scienceAreas){
            ScienceAreaDTO scienceAreaDTO=new ScienceAreaDTO();
            scienceAreaDTO.setId(sa.getId());
            scienceAreaDTO.setName(sa.getName());
            scienceAreaDTOS.add(scienceAreaDTO);
        }

        return new ResponseEntity<>(scienceAreaDTOS,HttpStatus.OK);

    }

    @PostMapping(path = "/setWorkDetails/{magazineId}",
            consumes = "application/json",
            produces = "application/json")
    @PreAuthorize("hasAuthority('author')")
    @SuppressWarnings("Duplicates")
    public @ResponseBody
    ResponseEntity setWorkDetails(@PathVariable Long magazineId, @RequestBody NewWorkDTO newWorkDTO) {

        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        Author author=authorService.findOneByUsername(username);

        Magazine magazine=this.magazineService.findOneById(magazineId);

        Work work=new Work();
        work.setAuthor(author);
        work.setTitle(newWorkDTO.getTitle());
        work.setWorkAbstract(newWorkDTO.getWorkAbstract());
        work.setKeyTerms(newWorkDTO.getKeyTerms());
        work.setWorkStatus(WorkStatus.requested);
        ScienceArea scienceArea=this.scienceAreaService.findOneById(newWorkDTO.getScienceArea());

        work.setScienceArea(scienceArea);
        work.setMagazine(magazine);
        work=workService.saveWork(work);

        ArrivedWorkDTO arrivedWorkDTO=new ArrivedWorkDTO();
        arrivedWorkDTO.setAuthorName(author.getName());
        arrivedWorkDTO.setAuthorSurname(author.getSurname());
        arrivedWorkDTO.setId(work.getId());
        arrivedWorkDTO.setTitle(work.getTitle());

        return new ResponseEntity(arrivedWorkDTO,HttpStatus.OK);
    }

    @PostMapping(path = "/postPdfFile/{workId}")
    @SuppressWarnings("Duplicates")
    @PreAuthorize("hasAuthority('author')")
    public @ResponseBody
    ResponseEntity<?> postPdfFile(@PathVariable Long workId, @RequestParam("pdfWork") MultipartFile file) {

        Work work=this.workService.findById(workId);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        String fileBasePath = "C:/Users/Mirko/Desktop/MasterProjekat/proposalWorks/" + username;
        File directory = new File(fileBasePath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        Path path = Paths.get(fileBasePath + "/" + fileName);

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();

        String pdfFilePath = fileBasePath + "/" + fileName;
        System.out.println("PDF=" + pdfFilePath);
        work.setProposalWorkPath(pdfFilePath);

        work=workService.saveWork(work);

        ArrivedWorkDTO arrivedWorkDTO=new ArrivedWorkDTO();
        arrivedWorkDTO.setAuthorName(work.getAuthor().getName());
        arrivedWorkDTO.setAuthorSurname(work.getAuthor().getSurname());
        arrivedWorkDTO.setId(work.getId());
        arrivedWorkDTO.setTitle(work.getTitle());

        return new ResponseEntity<>(arrivedWorkDTO,HttpStatus.OK);
    }

    @PostMapping(path = "/addCoauthor/{workId}",
            produces = "application/json")
    @SuppressWarnings("Duplicates")
    @PreAuthorize("hasAuthority('author')")
    public @ResponseBody
    ResponseEntity<?> addCoauthor(@RequestBody CoAuthorDTO coAuthorDTO, @PathVariable Long workId) {
        Work work=this.workService.findById(workId);
        CoAuthor coAuthor=new CoAuthor();
        coAuthor.setName(coAuthorDTO.getName());
        coAuthor.setSurname(coAuthorDTO.getSurname());
        coAuthor.setEmail(coAuthorDTO.getEmail());
        coAuthor.setCountry(coAuthorDTO.getCountry());
        coAuthor.setCity(coAuthorDTO.getCity());
        coAuthor.setLatitude(coAuthorDTO.getLatitude());
        coAuthor.setLongitude(coAuthorDTO.getLongitude());
        work.getCoAuthors().add(coAuthor);
        coAuthor.setWork(work);
        workService.saveWork(work);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    @GetMapping(path="/getWorksForPdfFormatFix",
//    produces = "application/json")
//    @SuppressWarnings("Duplicates")
//    public @ResponseBody ResponseEntity getWorksForPdfFix(){
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Author author = new Author();
//        try {
//            author=authorService.findOneByUsername(username);
//        } catch (NullPointerException e) {
//            System.out.println("Ne moze da najde autora!");
//        }
//
//        List<Work> retWorks = new ArrayList<>();
//
//        try {
//            retWorks = this.workService.findByAuthorAndStatus(author, WorkStatus.fix);
//        } catch (NullPointerException e) {
//            System.out.println("Ne moze da najde radove!");
//        }
//
//        List<ArrivedWorkDTO> retVal = new ArrayList<>();
//        for (Work work : retWorks) {
//            ArrivedWorkDTO arrivedWorkDTO = new ArrivedWorkDTO();
//            arrivedWorkDTO.setAuthorName(work.getAuthor().getName());
//            arrivedWorkDTO.setAuthorSurname(work.getAuthor().getSurname());
//            arrivedWorkDTO.setId(work.getId());
//            arrivedWorkDTO.setTitle(work.getTitle());
//            arrivedWorkDTO.setProcessId(work.getProcessId());
//            retVal.add(arrivedWorkDTO);
//
//        }
//        return new ResponseEntity(retVal, HttpStatus.OK);
//    }
//
//    @GetMapping(path="/getWorkForPdfFormatFix/{processId}",
//            produces = "application/json")
//    @SuppressWarnings("Duplicates")
//    public @ResponseBody FormFieldsDto getWorkForPdfFix(@PathVariable String processId){
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        try {
//            Author author = this.authorService.findOneByUsername(username);
//        } catch (Exception e) {
//            System.out.println("NIKO NIJE ULOGOVAN");
//        }
//
//        Task task = taskService.createTaskQuery().active().taskDefinitionKey("fixingPDFFormat").processInstanceId(processId).singleResult();
//        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
//        List<FormField> properties = taskFormData.getFormFields();
//
//        return new FormFieldsDto(task.getId(), processId, properties);
//    }
//
//    @PostMapping(path = "/sendFixedPdfFormatFile/{processId}/{taskId}")
//    @SuppressWarnings("Duplicates")
//    @PreAuthorize("hasAuthority('author')")
//    public @ResponseBody
//    ResponseEntity<?> sendFixedPdfFile(@PathVariable String taskId, @PathVariable String processId, @RequestParam("pdfWork") MultipartFile file) {
//        //System.out.println("Casopis: "+numberOfCoAuthors);
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        String fileBasePath = "C:/Users/Mirko/Desktop/MasterProjekat/proposalWorks/" + username;
//        File directory = new File(fileBasePath);
//        if (!directory.exists()) {
//            directory.mkdir();
//        }
//        Path path = Paths.get(fileBasePath + "/" + fileName);
//
//        try {
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/files/download/")
//                .path(fileName)
//                .toUriString();
//
//        String pdfFilePath = fileBasePath + "/" + fileName;
//        System.out.println("PDF=" + pdfFilePath);
//        runtimeService.setVariable(processId, "proposalPdfFilePath", pdfFilePath);
//
//        List<FormSubmissionDto> dto = (List<FormSubmissionDto>) runtimeService.getVariable(processId, "workDetails");
//        HashMap<String, Object> map = Utils.mapListToDto(dto);
//        formService.submitTaskForm(taskId, map);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}