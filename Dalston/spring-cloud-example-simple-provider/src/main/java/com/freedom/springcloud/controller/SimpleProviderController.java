package com.freedom.springcloud.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleProviderController {

    @RequestMapping("/simpleProvider/{name}")
    public String simpleProvider(@PathVariable String name) {
        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name + ", this is simple provider.";
    }

}
