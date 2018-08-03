package com.bridgelabz.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.bridgelabz.models.Label;

@PropertySource("classpath:error.properties")
public class LabelHelper {
	
	@Autowired
	private Producer producer;
	
	@Value("${labeldbname}")
	private String indexDbName;
	
	@Value("${labeltype}")
	private String type;
	
	public void createLabel(String id, Map<String, Object> mapLabel) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("index", indexDbName);
		map.put("type", type);
		map.put("id", id);
		map.put("document", mapLabel);
		map.put("operation", "index");
		
		producer.store(map);
	}
	
	public void updateNote(String id, Map<String, Object> mapLabel) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("index", indexDbName);
		map.put("type", type);
		map.put("id", id);
		map.put("document", mapLabel);
		map.put("operation", "update");
		
		producer.store(map);
	}
		
	public void deleteNote(String id) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("index", indexDbName);
		map.put("type", type);
		map.put("id", id);
		map.put("operation", "delete");
		
		producer.store(map);
	}
	
	
}
