package com.udd.udd.controller;


import com.udd.udd.dto.ReviewedWorkDTO;
import com.udd.udd.dto.WorkForReviewingDTO;
import com.udd.udd.model.*;
import com.udd.udd.service.ReviewerWorkService;
import com.udd.udd.service.SystemUserService;
import com.udd.udd.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path="/reviewer")
public class ReviewerController {
    @Autowired
    SystemUserService systemUserService;

    @Autowired
    ReviewerWorkService reviewerWorkService;

    @Autowired
    WorkService workService;


    @GetMapping(path = "getWorksForReviewing",
            produces = "application/json")
    @SuppressWarnings("Duplicates")
    public @ResponseBody
    ResponseEntity getWorksForReviewing() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        SystemUser systemUser = this.systemUserService.findByUsername(username);

        Set<Work> works = this.reviewerWorkService.getWorksForReviewing(systemUser);
        List<WorkForReviewingDTO> retVal=new ArrayList<>();
        for(Work work:works){
            WorkForReviewingDTO temp=new WorkForReviewingDTO();
            temp.setId(work.getId());
            temp.setTitle(work.getTitle());
            temp.setKeyTerms(work.getKeyTerms());
            temp.setWorkAbstract(work.getWorkAbstract());
            retVal.add(temp);
        }

        return new ResponseEntity(retVal, HttpStatus.OK);
    }
//
//    @GetMapping(value = "/getReviewingForm/{processId}",
//            produces = "application/json")
//    public @ResponseBody
//    FormFieldsDto getReviewongForm(@PathVariable String processId) {
//
//        Long workId=(Long) runtimeService.getVariable(processId,"workId");
//        String username=SecurityContextHolder.getContext().getAuthentication().getName();
//
//        Task task = taskService.createTaskQuery().active().taskDefinitionKey("ReviewingWork").taskAssignee(username).processInstanceId(processId).singleResult();
//        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
//        List<FormField> properties = taskFormData.getFormFields();
//
//        return new FormFieldsDto(task.getId(), processId, properties);
//    }
//
    @GetMapping(path = "/downloadPdf/{workId}",
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


        @PostMapping(value = "/postReviewingForm/{workId}",
            produces = "application/json")
    public @ResponseBody
    ResponseEntity postReviewingForm(@PathVariable Long workId,@RequestBody ReviewedWorkDTO reviewedWorkDTO) {

            String username=SecurityContextHolder.getContext().getAuthentication().getName();

            Work work=this.workService.findById(workId);
            SystemUser systemUser=this.systemUserService.findByUsername(username);

            ReviewerWork reviewerWork=new ReviewerWork();
            ReviewerWorkKey reviewerWorkKey=new ReviewerWorkKey();
            reviewerWorkKey.setReviewerId(systemUser.getId());
            reviewerWorkKey.setWorkId(work.getId());
            try {
                reviewerWork=this.reviewerWorkService.findByKey(reviewerWorkKey);
            }catch (NullPointerException e){
                e.printStackTrace();
            }

            reviewerWork.setReviewed(true);
            reviewerWork.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
            reviewerWork.setAuthorComment(reviewedWorkDTO.getAuthorComment());
            reviewerWork.setEditorComment(reviewedWorkDTO.getEditorComment());
            reviewerWork.setReccomendation(reviewedWorkDTO.getRecommendation());
            reviewerWorkService.saveReviewWork(reviewerWork);
            workService.saveWork(work);

            Set<ReviewerWork> temp=this.reviewerWorkService.findAllByWork(work);
            System.out.println("Recenzije:"+temp.size());
            boolean isAll=true;
            for(ReviewerWork rw:temp){
                System.out.println("Rec"+rw.getEditorComment());
                if(!rw.isReviewed()){
                    System.out.println("Recenzirano");
                    isAll=false;
                }
            }

            if(isAll){
                System.out.println("Odradio="+isAll);
                work.setWorkStatus(WorkStatus.makingDecision);
                workService.saveWork(work);
            }
            return new ResponseEntity(HttpStatus.OK);

    }
}
