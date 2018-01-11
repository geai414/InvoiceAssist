package com.shareit.ocr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Dispatcher extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Dispatcher.class, args);
	}

}
