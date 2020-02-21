//package com.udd.udd;
//
//
//import com.udd.udd.ElasticSearchRepository.WorkESRepository;
//import com.udd.udd.modelES.WorkES;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "/test")
//public class TestController {
//
//    @Autowired
//    WorkESRepository workESRepository;
//
//
//
//    RestHighLevelClient highLevelClient;
//
//    @GetMapping("/test1")
//    //@PreAuthorize("hasAuthority('admin')")
//    public ResponseEntity<?> test(){
//
//        System.out.println("usaooo");
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @GetMapping(value = "testES/{title}",produces = "application/json")
//    public @ResponseBody ResponseEntity getValue(@PathVariable String title){
//        System.out.println("USAO");
//        //List<WorkES> workES=this.workESRepository.findAll();
//
//        List<WorkES> workES=new ArrayList<>();
//        workES=this.workESRepository.findAllByTitleLike(title);
//
//        return new ResponseEntity(workES, HttpStatus.OK);
//
//    }
//
//    @GetMapping(value = "testAddES",produces = "application/json")
//    public @ResponseBody ResponseEntity addWorkES(){
//        WorkES workES=new WorkES();
//        long val=1;
//        workES.setAuthorName("Mujo Mujic");
//        workES.setId(val);
//        workES.setKeyTerms("regulatorima");
//        workES.setMagazineName("Mali tesla");
//        workES.setScienceArea("elektrotehnik");
//        workES.setTitle("regulatorima");
//        workES=workESRepository.save(workES);
//        return new ResponseEntity(workES,HttpStatus.OK);
//
//    }

//}
