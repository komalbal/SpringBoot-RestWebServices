package com.example.restWebService;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWebServiceApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeresolver() {
		SessionLocaleResolver sessionLocalResolver = new SessionLocaleResolver();
		sessionLocalResolver.setDefaultLocale(Locale.US);
		return sessionLocalResolver;
	}
	
	@Bean
	public ResourceBundleMessageSource bundleMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		return messageSource;
	}

}
