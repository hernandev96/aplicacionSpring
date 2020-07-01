package mx.uam.tsis.ejemplobackend;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class springFoxConfig {
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	private ApiInfo apiInfo(){
		return new ApiInfo(
				"Mi primer ApiRest",
				"Ejemplo de Api del curso tsis",
				"Api TDS",
				"Terms of Service",
				new Contact("Hector Hernan Dominguez Gonzalez","Whatsapp: +52 595 957 1482","dominguezgonzalezhectorhernan@gmail.com"),
				"License API","API License URL",Collections.emptyList()
				);
	}
}
