package com.springtest.demoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoappApplication {

	public static void main(String[] args) {
		// SpringApplication.run(DemoappApplication.class, args);
		ApplicationContext context = SpringApplication.run(DemoappApplication.class, args);

	}

}
