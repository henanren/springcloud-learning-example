package com.freedom.springcloud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@DefaultProperties(threadPoolKey = "defaultThreadPoolKeyTest") // 指定默认配置
@Service
public class ConsumerRibbonHystrixThreadPoolService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerRibbonHystrixThreadPoolService.class);

    @Autowired
    RestTemplate restTemplate;

    /**
     * 1、测试默认的 HystrixCommand的threadPoolKey是什么？ threadPoolKey是否就代表一个线程池
     */
    @HystrixCommand(fallbackMethod = "testDefaultThreadPoolKeyFallback")
    public String testDefaultThreadPoolKey(String name) {
        logger.error("testDefaultThreadPoolKey service invoke, thread is " + Thread.currentThread().getName());
        System.err.println("testDefaultThreadPoolKey service invoke, thread is " + Thread.currentThread().getName());
        return restTemplate.getForObject("http://simple-provider/simpleProvider/" + name, String.class);
    }

    private String testDefaultThreadPoolKeyFallback(String name, Throwable t) {
        logger.error("testDefaultThreadPoolKey fallback invoke, thread is " + Thread.currentThread().getName() + ",exception is " + t);
        System.err.println("testDefaultThreadPoolKey fallback invoke, thread is " + Thread.currentThread().getName() + ",exception is " + t);
        return "This is a fallback, " + name;
    }

    @HystrixCommand(fallbackMethod = "testDefaultThreadPoolKeyFallback2")
    public String testDefaultThreadPoolKey2(String name) {
        logger.info("testDefaultThreadPoolKey2 service invoke, thread is " + Thread.currentThread().getName());
        System.err.println("testDefaultThreadPoolKey2 service invoke, thread is " + Thread.currentThread().getName());
        return restTemplate.getForObject("http://simple-provider/simpleProvider/" + name, String.class);
    }

    private String testDefaultThreadPoolKeyFallback2(String name, Throwable t) {
        logger.info("testDefaultThreadPoolKeyFallback2 fallback invoke, thread is " + Thread.currentThread().getName() + ",exception is " + t);
        System.err.println("testDefaultThreadPoolKeyFallback2 fallback invoke, thread is " + Thread.currentThread().getName() + ",exception is " + t);
        return "This is a fallback, " + name;
    }

}
