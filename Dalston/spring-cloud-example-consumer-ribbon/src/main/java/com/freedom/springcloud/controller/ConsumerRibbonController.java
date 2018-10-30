package com.freedom.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerRibbonController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/consumerRibbon/{name}")
    public String simpleConsumer(@PathVariable String name){
        return restTemplate.getForObject("http://simple-provider/simpleProvider/" + name, String.class);
    }

}
