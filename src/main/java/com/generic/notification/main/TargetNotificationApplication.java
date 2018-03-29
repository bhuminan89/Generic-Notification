package com.generic.notification.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.generic.notification")
public class TargetNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TargetNotificationApplication.class, args);
	}
}
