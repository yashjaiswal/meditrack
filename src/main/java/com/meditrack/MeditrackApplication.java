package com.meditrack;

import com.meditrack.app.config.MeditrackAppParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(MeditrackAppParameters.class)
public class MeditrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeditrackApplication.class, args);
	}

}
