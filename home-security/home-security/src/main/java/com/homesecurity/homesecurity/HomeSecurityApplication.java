package com.homesecurity.homesecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HomeSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeSecurityApplication.class, args);
	}

}
