package com.dbc.trabalhovemser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TrabalhoVemserApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoVemserApplication.class, args);
	}

}
