package com.dacky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaProducerConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaProducerConsumerApplication.class, args);
	}

}
