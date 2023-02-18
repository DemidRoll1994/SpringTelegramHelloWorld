package com.example.springtelegramhelloworld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringTelegramHelloWorldApplication {

	public static void main(String[] args) {
		log.error("hello");
		SpringApplication.run(SpringTelegramHelloWorldApplication.class, args);

	}

}
