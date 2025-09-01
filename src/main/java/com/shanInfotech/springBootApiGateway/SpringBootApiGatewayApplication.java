package com.shanInfotech.springBootApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApiGatewayApplication.class, args);
	}

}
