package com.starwarsapis.starwarscharacters;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Star Wars Characters API", version = "1.0", description = "Microservicio para obtener información de personajes de Star Wars"))
@EnableFeignClients(basePackages = "com.starwarsapis.starwarscharacters.service") // Escanea este paquete para encontrar clientes Feign
public class StarwarsCharactersApplication {
	public static void main(String[] args) {
		SpringApplication.run(StarwarsCharactersApplication.class, args);
	}
}