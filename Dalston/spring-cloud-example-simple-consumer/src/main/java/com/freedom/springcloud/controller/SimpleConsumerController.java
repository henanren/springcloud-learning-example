package com.freedom.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SimpleConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/simpleConsumer/{name}")
    public String simpleConsumer(@PathVariable String name){
        return restTemplate.getForObject("http://127.0.0.1:10001/simpleProvider/" + name, String.class);
    }

}
