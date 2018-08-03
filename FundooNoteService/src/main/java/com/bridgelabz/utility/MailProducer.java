package com.bridgelabz.utility;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.bridgelabz.models.Email;

public class MailProducer {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${exchange}")
	private String exchange;

	@Value("${routingkey}")
	private String routingkey;
		
	public void send(Email emailObj) {
		
		rabbitTemplate.convertAndSend(exchange, routingkey, emailObj);	
	}

}