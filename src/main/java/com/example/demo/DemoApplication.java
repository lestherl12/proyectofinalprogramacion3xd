package com.example.demo;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
public class DemoApplication {
	@Value("${SPRING_DATASOURCE_URL}")
	private String datasourceUrl;
	@Value("${EMAIL_USERNAME}")
	private String emailUsername;


	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		System.out.println(System.getProperty("SPRING_DATASOURCE_URL"));
		System.out.println(System.getProperty("SPRING_DATASOURCE_PASSWORD"));
		SpringApplication.run(DemoApplication.class, args);
	}
	@PostConstruct
	public void printEnv() {
		System.out.println("SPRING_DATASOURCE_URL: " + datasourceUrl);
		System.out.println("EMAIL_USERNAME: " + emailUsername);
	}

}
