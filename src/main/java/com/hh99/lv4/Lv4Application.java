package com.hh99.lv4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Lv4Application {

	public static void main(String[] args) {
		SpringApplication.run(Lv4Application.class, args);
	}

}
