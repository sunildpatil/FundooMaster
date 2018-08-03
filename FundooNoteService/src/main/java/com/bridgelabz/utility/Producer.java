package com.bridgelabz.utility;

import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Producer {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${noteexchange}")
	private String exchange;

	@Value("${noteroutingkey}")
	private String routingkey;
	
	public void store(Map<String , Object> map) {
		
		rabbitTemplate.convertAndSend(exchange, routingkey, map);
	}
}