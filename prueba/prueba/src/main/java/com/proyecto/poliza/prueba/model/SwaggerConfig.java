package com.proyecto.poliza.prueba.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Proyecto Póliza Seguros")
				.version("1.00")
				.description("Solución tecnológica que le permita gestionar de forma ágil y eficaz todas\r\n"
						+ "sus pólizas de seguro, incluyendo seguros de autos, inmuebles y bienes\r\n"
						+ "como celulares.")
				.termsOfService("termino del servicio")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	} 
	
}
