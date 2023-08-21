package com.mateusulrich.codefood;

import com.mateusulrich.codefood.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class CodefoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodefoodApplication.class, args);
	}

}

