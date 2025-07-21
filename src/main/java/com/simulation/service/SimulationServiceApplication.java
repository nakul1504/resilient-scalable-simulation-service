package com.simulation.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SimulationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulationServiceApplication.class, args);
	}

}
