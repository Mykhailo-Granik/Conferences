package com.mgranik.conferences;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
public class ConferencesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferencesApplication.class, args);
	}

}
