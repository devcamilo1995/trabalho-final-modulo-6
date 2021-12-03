package com.dbc.logapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class EmailConsumidorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailConsumidorApplication.class, args);
	}

}
