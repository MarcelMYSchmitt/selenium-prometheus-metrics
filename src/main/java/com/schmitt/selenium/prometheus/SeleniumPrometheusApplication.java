package com.schmitt.selenium.prometheus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SeleniumPrometheusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeleniumPrometheusApplication.class, args);
	}
}
