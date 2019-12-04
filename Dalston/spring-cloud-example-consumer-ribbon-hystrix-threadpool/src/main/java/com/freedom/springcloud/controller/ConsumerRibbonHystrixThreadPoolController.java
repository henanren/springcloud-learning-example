package com.freedom.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freedom.springcloud.service.ConsumerRibbonHystrixThreadPoolService;

@RestController
public class ConsumerRibbonHystrixThreadPoolController {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerRibbonHystrixThreadPoolController.class);

    @Autowired
    ConsumerRibbonHystrixThreadPoolService service;

    /**
     * 1、测试默认的 HystrixCommand的threadPoolKey是什么？ threadPoolKey是否就代表一个线程池 测试1：
     * 配置线程池大小为2，服务提供方sleep，通过chrome多窗口调用/testDefaultThreadPoolKey端点
     * 或同时调用/testDefaultThreadPoolKey、/testDefaultThreadPoolKey2端点
     * 通过大于线程池最大值的请求被线程池拒绝进入fallback，判断线程池大小是方法级，还是类级别，threadPoolKey默认值 测试2： 通过@DefaultProperties(
     * threadPoolKey="defaultThreadPoolKeyTest" ) 在类上指定threadPoolKey默认值 测试3：
     * 通过配置hystrix.command.testDefaultThreadPoolKey.threadPoolKeyOverride=threadPoolKeyOverrideTest
     * 动态设置线程池Key，并重新创建线程池 通过 hystrix.threadpool.threadPoolKeyOverrideTest.coreSize=4
     * hystrix.threadpool.threadPoolKeyOverrideTest.maximumSize=4 动态设置线程池大小，并动态生效
     */
    @RequestMapping("/testDefaultThreadPoolKey")
    public String testDefaultThreadPoolKey() {
        logger.error("testDefaultThreadPoolKey controller invoke, main thread is " + Thread.currentThread().getName());
        System.err.println("testDefaultThreadPoolKey controller invoke, main thread is " + Thread.currentThread().getName());
        return service.testDefaultThreadPoolKey("zhangsan");
    }

    @RequestMapping("/testDefaultThreadPoolKey2")
    public String testDefaultThreadPoolKey2() {
        logger.error("testDefaultThreadPoolKey2 controller invoke, main thread is " + Thread.currentThread().getName());
        System.err.println("testDefaultThreadPoolKey2 controller invoke, main thread is " + Thread.currentThread().getName());
        return service.testDefaultThreadPoolKey2("zhangsan");
    }

}
