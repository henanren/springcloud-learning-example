package com.freedom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import zipkin.server.EnableZipkinServer;
import zipkin.storage.mysql.MySQLStorage;

import javax.sql.DataSource;

@EnableZipkinServer
@SpringBootApplication
public class ZipkinServerStorageMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinServerStorageMysqlApplication.class, args);
	}

}
