package fitmama.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
		"fitmama.controller",
		"fitmama.service",
		"fitmama.app"
})
@EnableJpaRepositories("fitmama.repo")
@EnableAutoConfiguration
@EntityScan("fitmama.model")
public class FitmamaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitmamaBackendApplication.class, args);
	}

}
