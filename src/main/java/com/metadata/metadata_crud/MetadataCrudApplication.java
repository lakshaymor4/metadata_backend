package com.metadata.metadata_crud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.metadata.metadata_crud.repository")

@EntityScan("com.metadata.metadata_crud.entity")
@SpringBootApplication
public class MetadataCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetadataCrudApplication.class, args);
	}

}
