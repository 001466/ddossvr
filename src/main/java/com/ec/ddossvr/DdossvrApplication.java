package com.ec.ddossvr;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = { "com.ec" })
@Configuration
@EnableAutoConfiguration
@ConfigurationProperties
public class DdossvrApplication {
	
	 

	
	public static void main(String[] args) throws IOException {

		SpringApplication.run(DdossvrApplication.class, args);
		
		System.err.println("启动完成！");

	}
}
