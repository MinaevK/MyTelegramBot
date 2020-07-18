package com.example.vobabot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class VobabotApplication {

	public static void main(String[] args) {
		//register bot
		ApiContextInitializer.init();

		SpringApplication.run(VobabotApplication.class, args);
	}

}
