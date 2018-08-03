package com.bridgelabz.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bridgelabz.interceptors.ValidationInterceptor;
import com.bridgelabz.utility.LabelHelper;
import com.bridgelabz.utility.MailProducer;
import com.bridgelabz.utility.NoteHelper;
import com.bridgelabz.utility.Producer;
import com.bridgelabz.utility.ResponseHelper;
import com.bridgelabz.utility.TokenHelper;

@Configuration
public class ApplicationConfig {

	@Bean
	public ResponseHelper responseHelper() {

		return new ResponseHelper();
	}
	
	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}
	
	@Bean
	public TokenHelper tokenHelper() {

		return new TokenHelper();
	}
	
	@Bean
	public Producer producer() {

		return new Producer();
	}
	
	@Bean
	public ValidationInterceptor validationInterceptor() {

		return new ValidationInterceptor();
	}
	
	@Bean
	public NoteRabbitMQConfig noteRabbitMQConfig() {

		return new NoteRabbitMQConfig();
	}
	
	@Bean
	public NoteHelper noteHelper() {

		return new NoteHelper();
	}
	
	@Bean
	public LabelHelper labelHelper() {

		return new LabelHelper();
	}
	
	@Bean
	public MailProducer mailProducer() {

		return new MailProducer();
	}
	
}