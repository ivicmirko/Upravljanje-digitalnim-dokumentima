package com.udd.udd.controller;


import com.udd.udd.service.AuthorService;
import com.udd.udd.service.ReviewerWorkService;
import com.udd.udd.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.*;

@RestController
@RequestMapping(path="/author")
public class AuthorWorkController {

    @Autowired
    WorkService workService;

    @Autowired
    AuthorService authorService;

    @Autowired
    ReviewerWorkService reviewerWorkService;


//    @GetMapping(path="/getMyApprovedWorks",
//            produces = "application/json")
//    @SuppressWarnings("Duplicates")
//    public @ResponseBody
//    ResponseEntity getMyApprovedWorks() {
//        String username= SecurityContextHolder.getContext().getAuthentication().getName();
//
//        Author author=this.authorService.findOneByUsername(username);
//        List<Work> works=workService.findByAuthorAndStatus(author, WorkStatus.approved);
//        List<WorkForReviewingDTO> workForReviewingDTOS=new ArrayList<>();
//
//        for(Work work:works){
//            WorkForReviewingDTO temp=new WorkForReviewingDTO();
//            temp.setId(work.getId());
//            temp.setTitle(work.getTitle());
//            temp.setKeyTerms(work.getKeyTerms());
//            temp.setWorkAbstract(work.getWorkAbstract());
//            temp.setProcessId(work.getProcessId());
//            workForReviewingDTOS.add(temp);
//        }
//        return new ResponseEntity(workForReviewingDTOS, HttpStatus.OK);
//    }
//
//    @GetMapping(path="/getMyWorksForFix",
//    produces = "application/json")
//    @SuppressWarnings("Duplicates")
//    public @ResponseBody
//    ResponseEntity getMyWorksForFix() {
//        String username= SecurityContextHolder.getContext().getAuthentication().getName();
//
//        Author author=this.authorService.findOneByUsername(username);
//        List<Work> works=workService.findByAuthorAndStatus(author, WorkStatus.needChages);
//        List<WorkForReviewingDTO> workForReviewingDTOS=new ArrayList<>();
//
//        for(Work work:works){
//            WorkForReviewingDTO temp=new WorkForReviewingDTO();
//            temp.setId(work.getId());
//            temp.setTitle(work.getTitle());
//            temp.setKeyTerms(work.getKeyTerms());
//            temp.setWorkAbstract(work.getWorkAbstract());
//            temp.setProcessId(work.getProcessId());
//            workForReviewingDTOS.add(temp);
//        }
//        return new ResponseEntity(workForReviewingDTOS, HttpStatus.OK);
//    }
//
//    @GetMapping(path="openWorkForFix/{processId}/{workId}",
//            produces = "application/json")
//    @SuppressWarnings("Duplicates")
//    public @ResponseBody ResponseEntity openWorkForFix(@PathVariable String processId, @PathVariable Long workId){
//
//        Work work=this.workService.findById(workId);
//        Set<ReviewerWork> reviewerWorks=new HashSet<>();
//        reviewerWorks=this.reviewerWorkService.findAllByWork(work);
//        List<ReviewedWorkDTO> retVal=new ArrayList<>();
//        for(ReviewerWork reviewerWork:reviewerWorks){
//            ReviewedWorkDTO reviewedWorkDTO=new ReviewedWorkDTO();
//            reviewedWorkDTO.setAuthorComment(reviewerWork.getAuthorComment());
//            reviewedWorkDTO.setEditorComment(reviewerWork.getEditorComment());
//            reviewedWorkDTO.setDate(reviewerWork.getDate());
//            reviewedWorkDTO.setRecommendation(reviewerWork.getReccomendation());
//            reviewedWorkDTO.setId(reviewerWork.getWork().getId());
//            retVal.add(reviewedWorkDTO);
//        }
//        ReviewesWorkDTO reviewesWorkDTO=new ReviewesWorkDTO();
//        reviewesWorkDTO.setAnswer(work.getAnswer());
//        reviewesWorkDTO.setReviewedWorkDTOS(retVal);
//
//        return new ResponseEntity(reviewesWorkDTO,HttpStatus.OK);
//    }
//
//    @GetMapping(path = "/getWorkFixForm/{processId}",
//            produces = "application/json")
//    @PreAuthorize("hasAuthority('author')")
//    @SuppressWarnings("Duplicates")
//    public @ResponseBody
//    FormFieldsDto getDataInputForm(@PathVariable String processId) {
//
//
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Long workId=(Long) runtimeService.getVariable(processId,"workId");
//        Work work=this.workService.findById(workId);
//
//        Task task = taskService.createTaskQuery().active().taskDefinitionKey("ChangingWork").processInstanceId(processId).singleResult();
//        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
//        List<FormField> properties = taskFormData.getFormFields();
//
//        return new FormFieldsDto(task.getId(), processId, properties);
//    }
//
//    @PostMapping(path = "/postWorkFixForm/{processId}",
//            produces = "application/json")
//    @PreAuthorize("hasAuthority('author')")
//    @SuppressWarnings("Duplicates")
//    public @ResponseBody
//    ResponseEntity<?> postFormForWorkingDetails(@RequestBody List<FormSubmissionDto> dto,@PathVariable String processId) {
//
//        HashMap<String, Object> map = Utils.mapListToDto(dto);
//        Task task = taskService.createTaskQuery().active().taskDefinitionKey("ChangingWork").processInstanceId(processId).singleResult();
//        Long workId=(Long) runtimeService.getVariable(processId,"workId");
//        Work work=this.workService.findById(workId);
//
//        for (FormSubmissionDto formField : dto) {
//            if (formField.getFieldId().equals("answerForReviewer")) {
//                work.setAnswer(formField.getFieldValue());
//            }
//        }
//
//        workService.saveWork(work);
//        runtimeService.setVariable(processId, "taskFixPDF", task.getId());
//        runtimeService.setVariable(processId, "workFixing", dto);
//
//
//        MessageResponeDTO responeDTO = new MessageResponeDTO();
//        responeDTO.setMessage("ok");
//
//        return new ResponseEntity<>(responeDTO, HttpStatus.ACCEPTED);
//
//    }
//
//    @PostMapping(path = "/postPdfFile/{processId}")
//    @SuppressWarnings("Duplicates")
//    @PreAuthorize("hasAuthority('author')")
//    public @ResponseBody
//    ResponseEntity<?> postPdfFile(@PathVariable String processId, @RequestParam("pdfWork") MultipartFile file) {
//
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
//        List<FormSubmissionDto> dto = (List<FormSubmissionDto>) runtimeService.getVariable(processId, "workFixing");
//        HashMap<String, Object> map = Utils.mapListToDto(dto);
//        String taskId = (String) runtimeService.getVariable(processId, "taskFixPDF");
//
//        Long workId=(Long) runtimeService.getVariable(processId,"workId");
//        Work work=this.workService.findById(workId);
//        int decision=(int) runtimeService.getVariable(processId,"decision");
//
//        if(decision==3){
//            //small changes
//            work.setWorkStatus(WorkStatus.makingDecision);
//        }else if(decision==4){
//            work.setWorkStatus(WorkStatus.reviewing);
//            updateReviewerWork(work);
//
//        }
//        work.setProposalWorkPath(pdfFilePath);
//        workService.saveWork(work);
//        formService.submitTaskForm(taskId, map);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//
//    public void updateReviewerWork(Work work){
//        Set<ReviewerWork> reviewerWorks = this.reviewerWorkService.findAllByWork(work);
//        for(ReviewerWork rw:reviewerWorks){
//            rw.setReviewed(false);
//            this.reviewerWorkService.saveReviewWork(rw);
//        }
//
//    }


}
