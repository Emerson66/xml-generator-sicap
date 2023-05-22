package com.buritiscript.XMLGeneratorForSICAP;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.buritiscript.XMLGeneratorForSICAP.service.StorageService;
import com.buritiscript.XMLGeneratorForSICAP.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class XmlGeneratorForSicapApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlGeneratorForSicapApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}
