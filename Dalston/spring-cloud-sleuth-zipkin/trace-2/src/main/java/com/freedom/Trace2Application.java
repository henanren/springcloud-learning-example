package com.freedom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class Trace2Application {
	private final Logger logger = LoggerFactory.getLogger(Trace2Application.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/trace-2", method = RequestMethod.GET)
	public String trace(HttpServletRequest request) {
		logger.info("===<call trace-2, TraceId={}, SpanId={}, ParentSpanId={}, Sampled={}, Span-Name={}>===",
				request.getHeader("X-B3-TraceId"),
				request.getHeader("X-B3-SpanId"),
				request.getHeader("X-B3-ParentSpanId"),
				request.getHeader("X-B3-Sampled"),
				request.getHeader("X-Span-Name"));
		try {
			Thread.sleep(3 * 1000);  //模拟业务处理
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		String name = jdbcTemplate.queryForObject( "SELECT CONFIG_NAME FROM sys_config WHERE id = 7", String.class );

		logger.info("===<call trace-2> service end===");
		return "Trace " + name;
	}

	public static void main(String[] args) {
		SpringApplication.run(Trace2Application.class, args);
	}

}
