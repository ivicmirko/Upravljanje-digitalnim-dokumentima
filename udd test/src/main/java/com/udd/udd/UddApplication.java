package com.udd.udd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UddApplication {

    @Bean
    public RestTemplate restTemplate() throws Exception{
        RestTemplate template = new RestTemplate();
        return template;
    }

    public static void main(String[] args) {
        SpringApplication.run(UddApplication.class, args);
    }

}
