package com.udd.udd.controller;


import com.udd.udd.dto.*;
import com.udd.udd.jwt.JwtTokenProvider;
import com.udd.udd.model.*;
import com.udd.udd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(path = "/editorWork")
public class EditorWorkController {


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

    @Autowired
    ReviewerWorkService reviewerWorkService;

    @Autowired
    SearchService searchService;

    @GetMapping(path = "/getArrivedWorks",
            produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody
    ResponseEntity getArrivedWorks() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Editor editor = new Editor();
        try {
            editor = editorService.findEditorByUsername(username);
            System.out.println("Editor=" + editor.getUsername());
        } catch (NullPointerException e) {
            System.out.println("Ne moze da najde urednika!");
        }
        Magazine magazine = new Magazine();
        try {
            magazine = this.magazineService.getMagazineByMainEditor(editor);
            System.out.println("Casopis=" + magazine.getName() + " Broj radova=" + magazine.getWorks().size());
        } catch (NullPointerException e) {
            System.out.println("Ne moze da pronadje casopis");
        }
        List<Work> arrivedWorks = new ArrayList<>();

        try {
            arrivedWorks = this.workService.findByMagazineAndStatus(magazine, WorkStatus.requested);
            System.out.println("Broj radovaa=" + arrivedWorks.size());
        } catch (NullPointerException e) {
            System.out.println("Ne moze da najde radove!");
        }
        List<ArrivedWorkDTO> retVal = new ArrayList<>();
        for (Work work : arrivedWorks) {
            ArrivedWorkDTO arrivedWorkDTO = new ArrivedWorkDTO();
            arrivedWorkDTO.setAuthorName(work.getAuthor().getName());
            arrivedWorkDTO.setAuthorSurname(work.getAuthor().getSurname());
            arrivedWorkDTO.setId(work.getId());
            arrivedWorkDTO.setTitle(work.getTitle());
            retVal.add(arrivedWorkDTO);

        }
        return new ResponseEntity(retVal, HttpStatus.OK);
    }

    @GetMapping(path = "/getCheckWorkForm/{workId}",
            produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody
    ResponseEntity getCheckWorkForm(@PathVariable Long workId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        try {
            Author author = this.authorService.findOneByUsername(username);
        } catch (Exception e) {
            System.out.println("NIKO NIJE ULOGOVAN");
        }

        Work work=this.workService.findById(workId);
        WorkForReviewingDTO workForReviewingDTO=new WorkForReviewingDTO();
        workForReviewingDTO.setId(work.getId());
        workForReviewingDTO.setKeyTerms(work.getKeyTerms());
        workForReviewingDTO.setWorkAbstract(work.getWorkAbstract());
        workForReviewingDTO.setTitle(work.getTitle());

        return new ResponseEntity(workForReviewingDTO,HttpStatus.OK);
    }


    @GetMapping(path = "/detailsCorrect/{workId}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @SuppressWarnings("Duplicates")
    public @ResponseBody
    ResponseEntity<InputStreamResource> detailsCorrect(@PathVariable Long workId, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Work work = new Work();
        try {
            work = this.workService.findById(workId);
        } catch (NullPointerException e) {
            System.out.println("Ne moze da nadje rad s ovim id");
        }
        work.setWorkStatus(WorkStatus.pdfFormatChecking);
        workService.saveWork(work);


        String[] path = work.getProposalWorkPath().split("/");
        String fileName = path[path.length - 1];

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        File file = new File(work.getProposalWorkPath());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @GetMapping(path = "/detailsDenied/{workId}",
            produces = "application/json")
    public @ResponseBody
    ResponseEntity detailsUncorrect(@PathVariable Long workId) {
        Work work = new Work();
        try {
            work = this.workService.findById(workId);
        } catch (NullPointerException e) {
            System.out.println("Ne moze da nadje rad s ovim id");
        }
        work.setWorkStatus(WorkStatus.denied);
        workService.saveWork(work);


        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping(path = "/worksForPdfCheck",
            produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody
    ResponseEntity getWorksForPdfCheck() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Editor editor = new Editor();
        try {
            editor = editorService.findEditorByUsername(username);
            System.out.println("Editor=" + editor.getUsername());
        } catch (NullPointerException e) {
            System.out.println("Ne moze da nadje urednika!");
        }
        Magazine magazine = new Magazine();
        try {
            magazine = this.magazineService.getMagazineByMainEditor(editor);
            System.out.println("Casopis=" + magazine.getName() + " Broj radova=" + magazine.getWorks().size());
        } catch (NullPointerException e) {
            System.out.println("Ne moze da pronadje casopis");
        }
        List<Work> arrivedWorks = new ArrayList<>();

        try {
            arrivedWorks = this.workService.findByMagazineAndStatus(magazine, WorkStatus.pdfFormatChecking);
            System.out.println("Broj radovaa=" + arrivedWorks.size());
        } catch (NullPointerException e) {
            System.out.println("Ne moze da najde radove!");
        }
        List<ArrivedWorkDTO> retVal = new ArrayList<>();
        for (Work work : arrivedWorks) {
            ArrivedWorkDTO arrivedWorkDTO = new ArrivedWorkDTO();
            arrivedWorkDTO.setAuthorName(work.getAuthor().getName());
            arrivedWorkDTO.setAuthorSurname(work.getAuthor().getSurname());
            arrivedWorkDTO.setId(work.getId());
            arrivedWorkDTO.setTitle(work.getTitle());
            retVal.add(arrivedWorkDTO);

        }
        return new ResponseEntity(retVal, HttpStatus.OK);
    }

    @GetMapping(path = "/approvePdf/{workId}")
    public @ResponseBody
    ResponseEntity approvePdf(@PathVariable Long workId) {
        Work work = new Work();
        try {
            work = workService.findById(workId);
        } catch (NullPointerException e) {
            System.out.println("Ne moze da nadje rad!");
        }

        work=this.workService.setEditorOfSA(work);
        boolean isEnoughReviewers=this.workService.isEnoughReviewers(work);
        if(isEnoughReviewers){
            work.setWorkStatus(WorkStatus.addReviewers);
        }else{
            work.setWorkStatus(WorkStatus.reviewing);
        }
        workService.saveWork(work);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @GetMapping(path = "/needToFixPdf/{workId}")
//    @SuppressWarnings("Duplicates")
//    public @ResponseBody
//    FormFieldsDto getCommentForm(@PathVariable Long workId) {
//
//        Work work = new Work();
//        try {
//            work = workService.findById(workId);
//        } catch (NullPointerException e) {
//            System.out.println("Ne moze da nadje rad!");
//        }
//        work.setWorkStatus(WorkStatus.fix);
//        workService.saveWork(work);
//
//        String processId = work.getProcessId();
//        runtimeService.setVariable(processId, "correctPdfFormtat", false);
//        Task task = taskService.createTaskQuery().active().taskDefinitionKey("CheckingPDFFormat").processInstanceId(processId).singleResult();
//        taskService.complete(task.getId());
//
//        //dovde
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//
//        Task task2 = taskService.createTaskQuery().active().taskDefinitionKey("CommentSettingEditTime").processInstanceId(processId).singleResult();
//        TaskFormData taskFormData = formService.getTaskFormData(task2.getId());
//        List<FormField> properties = taskFormData.getFormFields();
//
//        return new FormFieldsDto(task.getId(), processId, properties);
//    }
//
//    @PostMapping(path = "/sendCommentForPdf/{processId}/{taskId}")
//    public @ResponseBody ResponseEntity sendPdfToFix(@PathVariable String processId, @PathVariable String taskId,@RequestBody List<FormSubmissionDto> dto){
//
//        HashMap<String, Object> map = Utils.mapListToDto(dto);
//        Task task = taskService.createTaskQuery().active().taskDefinitionKey("CommentSettingEditTime").processInstanceId(processId).singleResult();
//        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
//
//        try {
//
//        }catch (NullPointerException e){
//            System.out.println("Kdo datuma puklo");
//        }
//
//        Object temp=null;
//
//        for (int i = 0; i < dto.size(); i++) {
//
//            if (dto.get(i).getFieldId().equals("editPdfDeadLine")) {
//                temp= DateTimeUtil.parseDate(dto.get(i).getFieldValue());
//            }
//        }
//
//        map.put("editPdfDeadLine",temp);
//
//        formService.submitTaskForm(task.getId(), map);
//
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
    @GetMapping(path="/worksWithoutReviewers",
    produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody ResponseEntity getWorksWithoutReviewersForm(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Editor editor = new Editor();
        try {
            editor = editorService.findEditorByUsername(username);
            System.out.println("Editor=" + editor.getUsername());
        } catch (NullPointerException e) {
            System.out.println("Ne moze da nadje urednika!");
        }
        Magazine magazine = new Magazine();
        try {
            magazine = this.magazineService.getMagazineByEditor(editor);
            System.out.println("Casopis=" + magazine.getName() + " Broj radova=" + magazine.getWorks().size());
        } catch (NullPointerException e) {
            System.out.println("Ne moze da pronadje casopis");
        }
        List<Work> worksForReviewing = new ArrayList<>();

        try {
            worksForReviewing = this.workService.findByMagazineAndStatus(magazine, WorkStatus.addReviewers);
            System.out.println("Broj radovaa=" + worksForReviewing.size());
        } catch (NullPointerException e) {
            System.out.println("Ne moze da najde radove!");
        }
        List<ArrivedWorkDTO> retVal = new ArrayList<>();
        for (Work work : worksForReviewing) {
            ArrivedWorkDTO arrivedWorkDTO = new ArrivedWorkDTO();
            arrivedWorkDTO.setAuthorName(work.getAuthor().getName());
            arrivedWorkDTO.setAuthorSurname(work.getAuthor().getSurname());
            arrivedWorkDTO.setId(work.getId());
            arrivedWorkDTO.setTitle(work.getTitle());
            retVal.add(arrivedWorkDTO);

        }
        return new ResponseEntity(retVal, HttpStatus.OK);
    }

    @GetMapping(path = "/getReviewersBySA/{workId}",
            produces = "application/json")
    @PreAuthorize("hasAuthority('editor')")
    @SuppressWarnings("Duplicates")
    public @ResponseBody
    ResponseEntity getReviewrsBySA(@PathVariable Long workId) {


        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Work work=this.workService.findById(workId);
        Magazine magazine=work.getMagazine();
        List<ChoseReviewersDTO> reviewers=new ArrayList<>();
        Set<SystemUser> systemUsers = this.systemUserService.findReviewersByScienceArea(work.getScienceArea());
        for (SystemUser su : systemUsers) {
            ChoseReviewersDTO choseReviewersDTO=new ChoseReviewersDTO();
            choseReviewersDTO.setId(su.getId());
            String retString = su.getName() + " " + su.getSurname();
            if (magazine.getReviewes().contains(su)) {
                retString += " (u nasem je casopisu)";
            }
            choseReviewersDTO.setName(retString);
            reviewers.add(choseReviewersDTO);
        }
        return new ResponseEntity(reviewers,HttpStatus.OK);
    }

    @PostMapping(path = "/addWorksReviewersForm/{workId}",
    consumes = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody ResponseEntity addWorksReviewersForm(@PathVariable Long workId,@RequestBody AddReviewersDTO reviewers){

        Work work = this.workService.findById(workId);

        work.setWorkStatus(WorkStatus.reviewing);
        SystemUser systemUser;

        for (int i = 0; i < reviewers.getReviewers().size(); i++) {
            Long temp=reviewers.getReviewers().get(i);
            systemUser = this.systemUserService.findOneById(temp);
            if (systemUser != null) {
                ReviewerWork reviewerWork = new ReviewerWork();
                reviewerWork.setSystemUser(systemUser);
                reviewerWork.setWork(work);
                ReviewerWorkKey reviewerWorkKey=new ReviewerWorkKey();
                reviewerWorkKey.setReviewerId(systemUser.getId());
                reviewerWorkKey.setWorkId(work.getId());
                reviewerWork.setReviewerWorkKey(reviewerWorkKey);
                reviewerWork.setReviewed(false);
                this.reviewerWorkService.saveReviewWork(reviewerWork);
            }

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
//
//    @PostMapping(path = "/setReviewingTimeForm/{processId}/{taskId}")
//    @SuppressWarnings("Duplicates")
//    public @ResponseBody ResponseEntity setReviewingTimeForm(@PathVariable String processId, @PathVariable String taskId,@RequestBody List<FormSubmissionDto> dto){
//
//        HashMap<String, Object> map = Utils.mapListToDto(dto);
//        Task task = taskService.createTaskQuery().active().taskDefinitionKey("SetReviewTime").processInstanceId(processId).singleResult();
//        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
//
//        String temp="P"; //P10D
//        for(FormSubmissionDto formField:dto) {
//            if (formField.getFieldId().equals("reviewTime")) {
//                temp += formField.getFieldValue()+"D";
//            }
//        }
//
//        runtimeService.setVariable(processId, "reviewTimeFormated", temp);
//
//        formService.submitTaskForm(task.getId(), map);
//
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
    @GetMapping(path="/getReviewedWorks",
            produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody ResponseEntity getReviewedWorks(){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        Editor editor=editorService.findEditorByUsername(username);
        System.out.println(editor.getUsername());
        List<Work> works = workService.findByEditorAndStatus(editor, WorkStatus.makingDecision);
        System.out.println(works.size());
        List<ArrivedWorkDTO> retVal = new ArrayList<>();
        for (Work work : works) {
            ArrivedWorkDTO arrivedWorkDTO = new ArrivedWorkDTO();
            arrivedWorkDTO.setAuthorName(work.getAuthor().getName());
            arrivedWorkDTO.setAuthorSurname(work.getAuthor().getSurname());
            arrivedWorkDTO.setId(work.getId());
            arrivedWorkDTO.setTitle(work.getTitle());
            retVal.add(arrivedWorkDTO);

        }
        return new ResponseEntity(retVal, HttpStatus.OK);
    }

    @GetMapping(path="openReviewedWork/{workId}",
    produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody ResponseEntity openReviewedWork(@PathVariable Long workId){

        Work work=this.workService.findById(workId);
        Set<ReviewerWork> reviewerWorks=new HashSet<>();
        reviewerWorks=this.reviewerWorkService.findAllByWork(work);
        List<ReviewedWorkDTO> retVal=new ArrayList<>();
        for(ReviewerWork reviewerWork:reviewerWorks){
            ReviewedWorkDTO reviewedWorkDTO=new ReviewedWorkDTO();
            reviewedWorkDTO.setAuthorComment(reviewerWork.getAuthorComment());
            reviewedWorkDTO.setEditorComment(reviewerWork.getEditorComment());
            reviewedWorkDTO.setDate(reviewerWork.getDate());
            reviewedWorkDTO.setRecommendation(reviewerWork.getReccomendation());
            reviewedWorkDTO.setId(reviewerWork.getWork().getId());
            retVal.add(reviewedWorkDTO);
        }
        ReviewesWorkDTO reviewesWorkDTO=new ReviewesWorkDTO();
        reviewesWorkDTO.setAnswer(work.getAnswer());
        reviewesWorkDTO.setReviewedWorkDTOS(retVal);

        return new ResponseEntity(reviewesWorkDTO,HttpStatus.OK);
    }

    @GetMapping(path="makeDecision/{workId}/{decision}",
    produces = "application/json")
    @PreAuthorize("hasAuthority('editor')")
    public @ResponseBody ResponseEntity postDecision(@PathVariable Long workId, @PathVariable int decision){
        Work work=workService.findById(workId);

        work.setWorkStatus(WorkStatus.needChages);
        this.workService.saveWork(work);

        if(decision==1){
            work.setWorkStatus(WorkStatus.approved);
            this.searchService.makeDOI(work);
        }
        workService.saveWork(work);

        return new ResponseEntity(HttpStatus.OK);
    }
//
//    @GetMapping(path = "/getSetNewReviewerForm/{processId}/{oldUsername}",
//            produces = "application/json")
//    //@PreAuthorize("hasAuthority('editor')")
//    @SuppressWarnings("Duplicates")
//    public @ResponseBody
//    FormFieldsDto getSetNewReviewerForm(@PathVariable String processId, @PathVariable String oldUsername) {
//
//        System.out.println("Usao daj formu za novog recc");
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Long workId=(Long) runtimeService.getVariable(processId,"workId");
//        Work work=this.workService.findById(workId);
//        Magazine magazine=work.getMagazine();
//        Task task = taskService.createTaskQuery().active().taskDefinitionKey("SetNewReviewer").processInstanceId(processId).singleResult();
//        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
//        List<FormField> properties = taskFormData.getFormFields();
//
//        Set<SystemUser> systemUsers = this.systemUserService.findReviewersByScienceArea(work.getScienceArea());
//        Set<ReviewerWork> reviewerWorks=this.reviewerWorkService.findAllByWork(work);
//        Set<SystemUser> retVal=this.systemUserService.findReviewersByScienceArea(work.getScienceArea());
//        for(SystemUser su:systemUsers){
//            for(ReviewerWork rw:reviewerWorks){
//                if(!su.getUsername().equals(oldUsername) && rw.getSystemUser().getUsername().equals(su.getUsername())){
//                    retVal.remove(su);
//                }
//            }
//        }
//        for (FormField element : properties) {
//            if (element.getId().equals("newrew")) {
//                for (SystemUser su : retVal) {
//                    String retString=su.getName()+" "+su.getSurname();
//                    if(magazine.getReviewes().contains(su)){
//                        retString+=" (u nasem je casopisu)";
//                    }
//                    element.getProperties().put(String.valueOf(su.getId()), retString);
//                }
//            }
//        }
//        return new FormFieldsDto(task.getId(), processId, properties);
//    }
//
//    @PostMapping(path = "/postSetNewReviewerForm/{processId}/{oldUsername}")
//    @SuppressWarnings("Duplicates")
//    public @ResponseBody ResponseEntity postSetNewReviewerForm(@PathVariable String processId,@PathVariable String oldUsername ,@RequestBody List<FormSubmissionDto> dto){
//
//        HashMap<String, Object> map = Utils.mapListToDto(dto);
//        Task task = taskService.createTaskQuery().active().taskDefinitionKey("SetNewReviewer").processInstanceId(processId).singleResult();
//        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
//        Long workId=(Long) runtimeService.getVariable(processId,"workId");
//        Work work=this.workService.findById(workId);
//        String newUsername="";
//        Task task2 = taskService.createTaskQuery().taskDefinitionKey("ReviewingWork").taskAssignee(oldUsername).processInstanceId(processId).singleResult();
//        SystemUser newSystemUser=new SystemUser();
//        for(FormSubmissionDto formField:dto) {
//            if (formField.getFieldId().equals("newRew")) {
//                System.out.println("nov="+formField.getFieldValue());
//                Long newRewId=Long.parseLong(formField.getFieldValue());
//                newSystemUser=this.systemUserService.findOneById(newRewId);
//                newUsername=newSystemUser.getUsername();
//
//                SystemUser oldSystemUser=this.systemUserService.findByUsername(oldUsername);
//                for(ReviewerWork reviewerWork:this.reviewerWorkService.findAllByWork(work)){
//                    if(reviewerWork.getSystemUser().getUsername().equals(oldUsername)){
//                        reviewerWork.setSystemUser(newSystemUser);
//                        reviewerWorkService.saveReviewWork(reviewerWork);
//                    }
//                }
//            }
//        }
//        System.out.println(newUsername+"asdaddad");
//        task2.setAssignee(newUsername);
//
//        runtimeService.setVariable(processId,"newUsername",newUsername);
//
//        runtimeService.setVariable(processId,"oldUsername",oldUsername);
//        formService.submitTaskForm(task.getId(), map);
//
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
////
////    @GetMapping(path = "/do/{processId}/{workId}",
////            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
////    public @ResponseBody
////    ResponseEntity<InputStreamResource> detailsCorrect(@PathVariable String processId, @PathVariable Long workId, HttpServletResponse response, HttpServletRequest request) throws IOException {
////        WorkES work = new WorkES();
////        try {
////            work = this.workService.findById(workId);
////        } catch (NullPointerException e) {
////            System.out.println("Ne moze da nadje rad s ovim id");
////        }
////        work.setProcessId(processId);
////        work.setWorkStatus(WorkStatus.pdfFormatChecking);
////        workService.saveWork(work);
////
////        runtimeService.setVariable(processId, "workRelevant", true);
////        Task task = taskService.createTaskQuery().active().taskDefinitionKey("MainEditorCheckWorkDetails").processInstanceId(processId).singleResult();
////        taskService.complete(task.getId());
////
////        String[] path = work.getProposalWorkPath().split("/");
////        String fileName = path[path.length - 1];
////
////        HttpHeaders headers = new HttpHeaders();
////        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
////        File file = new File(work.getProposalWorkPath());
////        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
////
////        return ResponseEntity.ok()
////                .headers(headers)
////                .contentLength(file.length())
////                .contentType(MediaType.parseMediaType("application/octet-stream"))
////                .body(resource);
////    }
}
