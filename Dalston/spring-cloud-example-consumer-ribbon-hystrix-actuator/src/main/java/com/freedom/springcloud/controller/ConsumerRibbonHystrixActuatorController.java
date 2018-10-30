package com.freedom.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerRibbonHystrixActuatorController {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand( fallbackMethod = "simpleConsumerFallback")
    @RequestMapping("/consumerRibbonHystrix/{name}")
    public String simpleConsumer(@PathVariable String name){
        return restTemplate.getForObject("http://simple-provider/simpleProvider/" + name, String.class);
    }

    /**
     * fallback方法的入参和返回值要和原方法一致
     * @param name
     * @return
     */
    public String simpleConsumerFallback(@PathVariable String name){
        return "This is a fallback, " + name;
    }

}
