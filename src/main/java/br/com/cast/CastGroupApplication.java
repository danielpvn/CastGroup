package br.com.cast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CastGroupApplication {
	private static final Logger LOGGER= LoggerFactory.getLogger(CastGroupApplication.class);

	public static void main(String[] args) {
		LOGGER.info("iniciando SpringBoot");
		
		SpringApplication.run(CastGroupApplication.class, args);
		
		LOGGER.info("SpringBoot iniciado com sucesso!!!");
	}

	
}
