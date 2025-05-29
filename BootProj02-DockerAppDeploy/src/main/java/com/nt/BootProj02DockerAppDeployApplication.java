package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootProj02DockerAppDeployApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootProj02DockerAppDeployApplication.class, args);
		System.out.println("Welocme to Docker SpringBoot Application !!");
	}

}
