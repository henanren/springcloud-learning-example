package com.freedom.springcloud;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableApolloConfig
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerRibbonHystrixThreadPoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerRibbonHystrixThreadPoolApplication.class, args);
	}


	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
