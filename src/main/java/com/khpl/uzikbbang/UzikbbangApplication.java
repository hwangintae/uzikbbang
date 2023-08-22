package com.khpl.uzikbbang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.khpl.uzikbbang.config.AppConfig;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class UzikbbangApplication {

	public static void main(String[] args) {
		SpringApplication.run(UzikbbangApplication.class, args);
	}

}
