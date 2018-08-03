package com.bridgelabz.user.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bridgelabz.user.utility.Producer;
import com.bridgelabz.user.utility.ProducerEmail;
import com.bridgelabz.user.utility.ResponseHelper;
import com.bridgelabz.user.utility.TokenHelper;
import com.bridgelabz.user.utility.UserHelper;

@Configuration
public class ApplicationConfig {

	@Bean
	public TokenHelper tokenConfig() {

		return new TokenHelper();
	}

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ResponseHelper responseHelper() {

		return new ResponseHelper();
	}	
	
	@Bean
	public ProducerEmail producerEmail() {

		return new ProducerEmail();
	}	
	
	@Bean
	public Producer producer() {

		return new Producer();
	}
	
	@Bean
	public UserHelper userHelper() {

		return new UserHelper();
	}
}