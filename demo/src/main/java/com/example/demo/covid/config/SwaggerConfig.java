package com.example.demo.covid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile("!prod")
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket configureSwagger() {
        
    	return new Docket(DocumentationType.SWAGGER_2).select().paths(Predicates.not(PathSelectors.regex("/actuator")))
				.paths(Predicates.not(PathSelectors.regex("/error"))).build();
    }
}
