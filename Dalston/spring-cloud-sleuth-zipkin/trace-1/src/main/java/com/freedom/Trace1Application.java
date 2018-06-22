package com.freedom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class Trace1Application {
	private static final Logger logger = LoggerFactory.getLogger(Trace1Application.class);

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/trace-1", method = RequestMethod.GET)
	public String trace() {
		logger.info("===<call trace-1> service begin===");
		try {
			Thread.sleep(2 * 1000);  //模拟业务处理
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("===<call trace-1> service end===");
		return restTemplate().getForEntity("http://trace-2/trace-2", String.class).getBody();
	}




	public static void main(String[] args) {
		SpringApplication.run(Trace1Application.class, args);
	}

}
