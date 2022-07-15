package br.com.cast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import br.com.cast.servicos.AuditingService;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditingService")
public class CastGroupApplication {
	private static final Logger LOGGER= LoggerFactory.getLogger(CastGroupApplication.class);

	public static void main(String[] args) {
		LOGGER.info("iniciando SpringBoot");
		
		SpringApplication.run(CastGroupApplication.class, args);
		
		LOGGER.info("SpringBoot iniciado com sucesso!");
	}
	@Bean
    AuditorAware<String> auditorProvider() {
     return new AuditingService();
    }
	
}
