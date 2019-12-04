package com.freedom.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableDiscoveryClient
public class SimpleProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleProviderApplication.class, args);
    }
}
