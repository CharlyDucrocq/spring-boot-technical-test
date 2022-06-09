package com.supralog.recruitment.springexamusercrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.supralog.recruitment.springexamusercrud")
public class SpringExamUserCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExamUserCrudApplication.class, args);
	}

}
