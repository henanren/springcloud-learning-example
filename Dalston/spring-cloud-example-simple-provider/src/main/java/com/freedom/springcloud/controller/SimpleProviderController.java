package com.freedom.springcloud.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleProviderController {

    @RequestMapping("/simpleProvider/{name}")
    public String simpleProvider(@PathVariable String name){
        return "Hello " + name + ", this is simple provider.";
    }

}
