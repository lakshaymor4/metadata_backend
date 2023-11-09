package com.metadata.metadata_crud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages = {"com.metadata.metadata_crud.repository.sourcesys", "com.metadata.metadata_crud.repository.user"})


@EntityScan({"com.metadata.metadata_crud.entity.sourcesys", "com.metadata.metadata_crud.entity.user","com.metadata.metadata_crud.config.metadatconfig","com.metadata.metadata_crud.config.syssourceconfig"})

@SpringBootApplication
public class MetadataCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetadataCrudApplication.class, args);
	}

}
