package com.integracao.sistemas.api.trabalho01api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Trabalho01apiApplication {

	public static void main(String[] args) {
		System.out.println("DB_USERNAME: " + System.getenv("DB_USERNAME"));
		System.out.println("DB_PASSWORD: " + System.getenv("DB_PASSWORD"));
		SpringApplication.run(Trabalho01apiApplication.class, args);
	}

}
