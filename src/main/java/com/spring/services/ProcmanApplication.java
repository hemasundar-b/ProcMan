package com.spring.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.spring.process.controller"})
public class ProcmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcmanApplication.class, args);
	}

}
