package com.castro.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MicroservicesApplication {


	/*
	* me genera problema si no aplico en el arhivo
	* application.properties lo siguiente
	* spring.mvc.pathmatch.matching-strategy=ant-path-matcher
	* el error presentado era este
	* Failed to start bean 'documentationPluginsBootstrapper';
	*  nested exception is java.lang.NullPointerException: Cannot invoke
	* "org.springframework.web.servlet.mvc.condition.PatternsRequestCondition.toString()"
	*  because the return value of
	* "springfox.documentation.spi.service.contexts.Orderings.patternsCondition(springfox.documentation.RequestHandler)" is null
	* */
	@Bean
	public Docket schoolApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesApplication.class, args);
	}

}