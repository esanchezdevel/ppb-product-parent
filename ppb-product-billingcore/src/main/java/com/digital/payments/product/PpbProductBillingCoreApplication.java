package com.digital.payments.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PpbProductBillingCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PpbProductBillingCoreApplication.class, args);
	}
}
