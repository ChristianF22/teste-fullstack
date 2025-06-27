package br.com.testefullstack.testefullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TestefullstackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestefullstackApplication.class, args);
	}

}
