package com.quartz.jobschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class JobscheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobscheduleApplication.class, args);
	}

}
