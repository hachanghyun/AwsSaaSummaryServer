package com.hotong.awsSaaSummaryServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AwsSaaSummaryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSaaSummaryServerApplication.class, args);
	}

}
