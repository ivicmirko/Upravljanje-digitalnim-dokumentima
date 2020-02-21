package com.udd.udd.controller;


import com.udd.udd.ElasticSearchRepository.WorkESRepository;
import com.udd.udd.modelES.WorkES;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/test")
//@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

    @Autowired
    WorkESRepository workESRepository;




    @GetMapping("/test1")
    //@PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> test(){

        System.out.println("usaooo");
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "testES/{title}",produces = "application/json")
    public @ResponseBody ResponseEntity getValue(@PathVariable String title){
        List<WorkES> workES=new ArrayList<>();

        workES=this.workESRepository.findByTitle(title);

        return new ResponseEntity(workES,HttpStatus.OK);

    }

    @GetMapping(value = "testES2/{title}",produces = "application/json")
    public @ResponseBody ResponseEntity getValue2(@PathVariable String title) throws Exception{
        List<WorkES> workES=new ArrayList<>();
        IndexRequest indexRequest=new IndexRequest("post");
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));        //workES=this.workESRepository.findAllByTitleLike(title);

        client.close();
        return new ResponseEntity(workES,HttpStatus.OK);

    }

    @GetMapping(value = "testAddES",produces = "application/json")
    public @ResponseBody ResponseEntity addWorkES(){
        List<WorkES> workESS=new ArrayList<>();
        WorkES workES=new WorkES();
        long val=1;
        workES.setAuthors("Mujo Mujic, Mate Matic");
        workES.setId(val);
        workES.setKeyTerms("regulatori");
        workES.setMagazineName("Mali tesla");
        //workES.setScienceArea("elektrotehnik");
        workES.setScienceArea((long)1);
        workES.setTitle("Frekventni regulatori naizmenicnih struja u industriji");
        workES=workESRepository.save(workES);

        val=2;
        workES.setAuthors("Mate Matic");
        workES.setId(val);
        workES.setKeyTerms("regulatori protoka");
        workES.setMagazineName("Mali tesla");
        //workES.setScienceArea("elektrotehnika");
        workES.setTitle("Regulatori protoka u industriji");
        workES.setScienceArea((long)3);
        workES=workESRepository.save(workES);
        return new ResponseEntity(workES,HttpStatus.OK);

    }




}
