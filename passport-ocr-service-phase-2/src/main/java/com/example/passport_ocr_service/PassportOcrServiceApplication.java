package com.example.passport_ocr_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PassportOcrServiceApplication {

	public static void main(String[] args) {
        System.out.println("=== App starting ===");
        SpringApplication.run(PassportOcrServiceApplication.class, args);
        System.out.println("=== App started ===");
	}

}
