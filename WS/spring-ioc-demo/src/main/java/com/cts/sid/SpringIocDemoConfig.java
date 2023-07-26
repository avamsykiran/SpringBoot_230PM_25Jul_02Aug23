package com.cts.sid;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.cts.sid")
public class SpringIocDemoConfig {

	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}
}
