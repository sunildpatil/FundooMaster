package com.bridgelabz.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.bridgelabz.exceptions.NoteColorValidationException;
import com.bridgelabz.exceptions.NoteValidationException;
import com.bridgelabz.models.Note;
import com.bridgelabz.models.NoteCreateDTO;

@PropertySource("classpath:error.properties")
public class NoteHelper {

	@Autowired
    private Environment environment;
	
	@Autowired
	private Producer producer;
	
	@Value("${indexdbname}")
	private String indexDbName;
	
	@Value("${type}")
	private String type;
	
	public void noteValidations(NoteCreateDTO noteDTO) throws NoteValidationException {
		
		String title = noteDTO.getTitle();
		String description = noteDTO.getDescription();
			
		if(title==null && description==null) {

			throw new NoteValidationException(environment.getProperty("titlenullerrormsg"));
		}
		
//		if(title.isEmpty() || description.isEmpty()) {
//			
//			throw new NoteValidationException(environment.getProperty("titleemptyerrormsg"));
//		}		
	}
	
	public void noteColorValidations(int noteId, String color) throws NoteColorValidationException {
			
		if(noteId==0) {

			throw new NoteColorValidationException(environment.getProperty("noteidvalnullmsg"));
		}
		
		if(color==null) {

			throw new NoteColorValidationException(environment.getProperty("colorvalnullmsg"));
		}
		
		if(color.isEmpty()) {
			
			throw new NoteColorValidationException(environment.getProperty("colorvalemptymsg"));
		}
	}
	
	public void createNote(String id, Map<String, Object> createNoteMap) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("index", indexDbName);
		map.put("type", type);
		map.put("id", id);
		map.put("document", createNoteMap);
		map.put("operation", "index");
		
		producer.store(map);
	}
	
	public void updateNote(String id, Map<String, Object> updateNoteMap) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("index", indexDbName);
		map.put("type", type);
		map.put("id", id);
		map.put("document", updateNoteMap);
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