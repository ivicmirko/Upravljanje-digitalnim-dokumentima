//package com.udd.udd.controller;
//
//
//import com.udd.udd.service.MagazineService;
//import com.udd.udd.service.SystemUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@RestController
//@RequestMapping(path = "/adminNC")
//public class AdminNCController {
//
//    @Autowired
//    SystemUserService systemUserService;
//
//    @Autowired
//    MagazineService magazineService;
//
////    @Autowired
////    private ScienceAreaService scienceAreaService;
//
//    @GetMapping(path="/getReviewerRequests",
//            produces="application/json")
//    @SuppressWarnings("Duplicates")
//    public @ResponseBody ResponseEntity<?> getReviewerRequests(){
//
//        System.out.println("Taskovi za admina");
//        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("adminNC").list();
//
//        List<FormFieldsDto> formFieldsDtos = new ArrayList<>();
//        for(Task task: tasks){
//            if(task.getName().equals("Confirm As Reviewer")) {
//                FormFieldsDto formFieldsDto = new FormFieldsDto();
//                formFieldsDto.setTaskId(task.getId());
//
//                TaskFormData taskFormData = formService.getTaskFormData(task.getId());
//                List<FormField> properties = taskFormData.getFormFields();
//
//                formFieldsDto.setFormFields(properties);
//                formFieldsDtos.add(formFieldsDto);
//            }
//        }
//        return new ResponseEntity(formFieldsDtos,  HttpStatus.OK);
//
//    }
//
//    @GetMapping(path="/approveReviewer/{taskId}",
//            produces="application/json")
//    public @ResponseBody ResponseEntity<?> confirmingReviewer(@PathVariable String taskId){
//
//        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//        String processInstanceId = task.getProcessInstanceId();
//
//        runtimeService.setVariable(processInstanceId, "decision", "yes");
//
//        taskService.complete(taskId);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping(path="/denyReviewer/{taskId}",
//            produces="application/json")
//    public @ResponseBody ResponseEntity<?> denyReviewer(@PathVariable String taskId){
//
//        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//        String processInstanceId = task.getProcessInstanceId();
//
//        runtimeService.setVariable(processInstanceId, "decision", "no");
//
//        taskService.complete(taskId);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping(path="/getMagazineRequests",
//    produces = "application/json")
//    public @ResponseBody ResponseEntity getMagazinesRequests(){
//
//        Set<Magazine> magazines=this.magazineService.findMagazinesByStatus("request");
//        System.out.println("aaaaaaa"+magazines.size());
//        return new ResponseEntity<>(magazines,HttpStatus.OK);
//    }
//
//    @GetMapping(path="approveMagazine/{id}")
//    public  @ResponseBody ResponseEntity approveMagazine(@PathVariable Long id){
//        Magazine magazine=new Magazine();
//        try {
//            System.out.println("Id casopisa"+id);
//             magazine=this.magazineService.findOneById(id);
//        }catch (NullPointerException e){
//            System.out.println("nije pronasao magazine");
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//
//        }
//
//        magazine.setStatus("approved");
//        magazineService.saveMagazine(magazine);
//    //approved
//        return new ResponseEntity(HttpStatus.OK);
//    };
//
//    @GetMapping(path = "correctionMagazine/{id}")
//    public @ResponseBody ResponseEntity correctionMagazine(@PathVariable Long id){
//        Magazine magazine=this.magazineService.findOneById(id);
//        if(magazine==null){
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//        magazine.setStatus("fix");
//        magazineService.saveMagazine(magazine);
//        //approved
//        return new ResponseEntity(HttpStatus.OK);
//    }
//}
