package com.devtcc.tccback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/usuario")
		.allowedOrigins("http://localhost:3000")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
		
		registry.addMapping("/ativo")
		.allowedOrigins("http://localhost:3000")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
		
		registry.addMapping("/avaliacao")
		.allowedOrigins("http://localhost:3000")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
		
		registry.addMapping("/aprovador")
		.allowedOrigins("http://localhost:3000")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
		
		registry.addMapping("/checklist")
		.allowedOrigins("http://localhost:3000")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
	}
}
