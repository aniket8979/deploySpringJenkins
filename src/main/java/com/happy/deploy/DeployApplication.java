package com.happy.deploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DeployApplication {

	@GetMapping
	public String message(){
		return "jai siya ram";
	}

	public static void main(String[] args) {
		SpringApplication.run(DeployApplication.class, args);

		System.out.println("Jai shree ram");
	}

}
