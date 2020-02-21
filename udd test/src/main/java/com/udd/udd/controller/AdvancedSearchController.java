package com.udd.udd.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udd.udd.searchDTO.AdvancedSearchDTO;
import com.udd.udd.searchDTO.WorkESdto;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/advancedSearch")
@SuppressWarnings("Duplicates")
public class AdvancedSearchController {

    @PostMapping(path = "/search",
    consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity advancedSearch(@RequestBody AdvancedSearchDTO dto) throws Exception{

        String must="\"must\" : [\n" ;
//                "        { \"term\" : { \"tag\" : \"wow\" } },\n" +
//                "        { \"term\" : { \"tag\" : \"elasticsearch\" } }\n" +
//                "      ],";

        String should="\"should\" : [\n";
//                "        { \"term\" : { \"tag\" : \"wow\" } },\n" +
//                "        { \"term\" : { \"tag\" : \"elasticsearch\" } }\n" +
//                "      ],";

        boolean shouldBe=false;
        boolean mustBe=false;
        if(dto.isMagazineCheck() && !dto.getMagazineName().equals("")){
            must+="{ \"match\" : { \"magazineName\" : \""+dto.getMagazineName()+"\" } },";
            mustBe=true;
        }else if(!dto.isMagazineCheck() && !dto.getMagazineName().equals("")){
            should+="{ \"match\" : { \"magazineName\" : \""+dto.getMagazineName()+"\" } },";
            shouldBe=true;
        }

        if(dto.isTitleCheck() && !dto.getTitle().equals("")){
            must+="{ \"match\" : { \"title\" : \""+dto.getTitle()+"\" } },";
            mustBe=true;

        }else if(!dto.isTitleCheck() && !dto.getTitle().equals("")){
            should+="{ \"match\" : { \"title\" : \""+dto.getTitle()+"\" } },";
            shouldBe=true;
        }

        if(dto.isKeyTermsCheck() && !dto.getKeyTerms().equals("")){
            must+="{ \"match\" : { \"keyTerms\" : \""+dto.getKeyTerms()+"\" } },";
            mustBe=true;

        }else if(!dto.isKeyTermsCheck() && !dto.getKeyTerms().equals("")){
            should+="{ \"match\" : { \"keyTerm\" : \""+dto.getKeyTerms()+"\" } },";
            shouldBe=true;
        }

        if(dto.isAuthorsCheck() && !dto.getAuthors().equals("")){
            must+="{ \"match\" : { \"authors\" : \""+dto.getAuthors()+"\" } },";
            mustBe=true;

        }else if(!dto.isAuthorsCheck() && !dto.getAuthors().equals("")){
            should+="{ \"match\" : { \"authors\" : \""+dto.getAuthors()+"\" } },";
            shouldBe=true;
        }

        if(dto.isContentCheck() && !dto.getContent().equals("")){
            must+="{ \"mathc\" : { \"workContent\" : \""+dto.getContent()+"\" } },";
            mustBe=true;

        }else if(!dto.isContentCheck() && !dto.getContent().equals("")){
            should+="{ \"match\" : { \"workContent\" : \""+dto.getContent()+"\" } },";
            shouldBe=true;
        }
        must= must.substring(0,  must.length() - 1);
        should=should.substring(0,should.length()-1);
        must+="],";
        should+="],";
        String query="{\n" +
                "  \"query\": {\n" +
                "    \"bool\" : {\n";
                if(mustBe){
                    query+=must;
                }

                if(shouldBe){
                    query+=should;
                }
//                "      \"must\" : [\n" +
//                "        \"term\" : { \"user\" : \"kimchy\" }\n" +
//                "      ],\n" +
//                "      \"should\" : [\n" +
//                "        { \"term\" : { \"tag\" : \"wow\" } },\n" +
//                "        { \"term\" : { \"tag\" : \"elasticsearch\" } }\n" +
//                "      ],\n" +
            query+=//"      \"minimum_should_match\" : 1,\n" +
                "         \"boost\" : 1.0\n" +
                "    }\n" +
                "  },\n" +
                "\"highlight\" : {\n" +
                "        \"fields\" : {\n" +
                "            \"magazineName\" : {" +
                "               \t\"type\":\"plain\"\n" +
                            "},\n" +
                    "        \"title\" : {" +
                    "           \t\"type\":\"plain\"\n" +
                            "},\n" +
                    "        \"keyTerms\" : {" +
                    "             \t\"type\":\"plain\"\n" +
                            "},\n" +
                    "       \"content\" : {" +
                    "               \t\"type\":\"plain\"\n" +
                            "},\n" +
                    "        \"authors\" : {" +
                    "               \t\"type\":\"plain\"\n" +
                            "}\n" +
                "        }\n" +
                "    }"+
                "}";


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonquery = objectMapper.readTree(query);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
        String fooResourceUrl
                = "http://localhost:9200/nc/work/_search?pretty";
        ResponseEntity<String> response
                = restTemplate.postForEntity(fooResourceUrl, request, String.class);
        JsonNode rootNode = objectMapper.readTree(response.getBody());
        JsonNode locatedNode = rootNode.path("hits").path("hits");
        List<WorkESdto> retVal = getRetVal(locatedNode);
        return new ResponseEntity<>(retVal, HttpStatus.OK);

    }


    public List<WorkESdto> getRetVal(JsonNode node){
        List<WorkESdto> retVal=new ArrayList<>();
        for(int i=0;i<node.size();i++){
            WorkESdto dto=new WorkESdto();
            Long workId=Long.parseLong(node.get(i).path("_source").path("id").asText());
//            Work work=workRepository.findOneById(workId);
            dto.setAuthors(node.get(i).path("_source").path("authors").asText());
            dto.setId(workId);
            dto.setMagazineName(node.get(i).path("_source").path("magazineName").asText());
//            dto.setOpenAcess(work.getMagazine().isOpenAccess());
            dto.setTitle(node.get(i).path("_source").path("title").asText());
            dto.setWorkAbstract(node.get(i).path("_source").path("workAbstract").asText());
//            System.out.println(node.get(i).path("highlight").path(highlight).toString());
            String highText=node.get(i).path("highlight").toString();
//            for(int j=0;j<node.get(i).path("highlight").path(highlight).size();j++){
//                highText+=node.get(i).path("highlight").path(highlight).get(j).asText()+"...";
//            }
            highText=highText.replace("\"","");
            highText=highText.replace("{"," ");
            highText=highText.replace("}"," ");
            highText=highText.replace("["," ");
            highText=highText.replace("]"," ");
            dto.setHighlight(highText);
            retVal.add(dto);
        }
        return retVal;

    }
}
