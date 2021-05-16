package io.EnglishLevelGame.EnglishLevelGame;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.EnglishLevelGame.EnglishLevelGame.uploadFiles.StorageService;

@SpringBootApplication
public class EnglishLevelGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnglishLevelGameApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};

	}
}
