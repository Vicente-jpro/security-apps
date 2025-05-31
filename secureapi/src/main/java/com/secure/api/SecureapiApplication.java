package com.secure.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class SecureapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureapiApplication.class, args);
	}

}
