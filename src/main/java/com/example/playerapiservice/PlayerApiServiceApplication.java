package com.example.playerapiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PlayerApiServiceApplication is the main class for the Player API service.
 * This application provides REST endpoints for managing player data.
 * Author: Mor Gutman
 *
 * If I had infinite time, I would consider the following:
 * Writing integration tests to ensure that components work correctly together.
 * Cache frequently accessed data if applicable to reduce the load on the system
 * Docker and Kubernetes (I would create deployment.yaml and service.yaml files)
 */
@SpringBootApplication
public class PlayerApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayerApiServiceApplication.class, args);
	}

}
