package com.lang.spring_boot_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
// Can implement ApplicationRunner or CommandLineRunner
public class App {
	// @Value("${spring.application.name}")
	private String appName = "Spring Boot Example";

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
