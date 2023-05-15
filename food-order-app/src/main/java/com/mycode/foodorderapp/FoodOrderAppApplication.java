package com.mycode.foodorderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableRetry
@EnableTransactionManagement
public class FoodOrderAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderAppApplication.class, args);
	}

}
