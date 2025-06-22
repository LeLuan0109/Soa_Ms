package com.project.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = RedisReactiveAutoConfiguration.class)
@EnableFeignClients
public class DaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaAppApplication.class, args);
	}
}
