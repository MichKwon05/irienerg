package com.ineel.ifm;

import com.ineel.ifm.controller.rol.RolController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class IfmApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfmApplication.class, args);
	}

	@Bean
	public CommandLineRunner crearRoles(RolController controller) {
		return args -> {
			try {
				controller.createRolIfNotExist();
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}
}
