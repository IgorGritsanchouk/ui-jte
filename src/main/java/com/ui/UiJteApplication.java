package com.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportRuntimeHints;

@ImportRuntimeHints(ResourceRuntimeHints.class)
@EnableCaching
@SpringBootApplication
public class UiJteApplication {

	public static void main(String[] args) {
		SpringApplication.run(UiJteApplication.class, args);
	}

}
