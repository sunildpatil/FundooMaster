package com.bridgelabz.user.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.user.models.User;

public class UserHelper {
	
	@Autowired
	private Producer producer;
	
	public void insert(String key, String id, User user) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("KEY", key);
		map.put("HK", id);
		map.put("object", user);
		map.put("operation", "insert");
		
		producer.store(map);
	}
		
	public void delete(String key, String id) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("KEY", key);
		map.put("HK", id);
		map.put("operation", "delete");
		
		producer.store(map);
	}
	
	public void update(String key, String id, User user) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("KEY", key);
		map.put("HK", id);
		map.put("object", user);
		map.put("operation", "update");
		
		producer.store(map);
	}
}