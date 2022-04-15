package com.roberto.taPronto;

import com.roberto.taPronto.service.DBService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class TaProntoApiApplication implements CommandLineRunner {

	private final DBService dbService;

	public static void main(String[] args) {
		SpringApplication.run(TaProntoApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dbService.init();
	}

}
