package com.npsolutions.cartserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CartServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartServerApplication.class, args);
	}

}
