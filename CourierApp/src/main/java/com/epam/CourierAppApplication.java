package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class CourierAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourierAppApplication.class, args);
	}

}
