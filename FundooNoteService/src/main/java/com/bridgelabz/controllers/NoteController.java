package com.bridgelabz.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.models.NoteCreateDTO;
import com.bridgelabz.models.NoteResponseDTO;
import com.bridgelabz.models.NoteUpdateDTO;
import com.bridgelabz.models.ReminderDTO;
import com.bridgelabz.models.Response;
import com.bridgelabz.services.NoteService;
import com.bridgelabz.utility.NoteHelper;
import com.bridgelabz.utility.ResponseHelper;

@RestController
@PropertySource("classpath:error.properties")
@RequestMapping(value="/notes")
public class NoteController {

	@Autowired
	private NoteService noteService;
	
	@Autowired
	private ResponseHelper responseHelper;
	
	@Autowired
    private Environment environment;
	
	@Autowired
    private NoteHelper noteHelper;
			
	@PostMapping("/createnote")
	public ResponseEntity<Response> createNote(HttpServletRequest request, @RequestBody NoteCreateDTO noteDTO) throws Exception {
		
		int userId = Integer.parseInt(request.getAttribute("userID").toString());
		noteHelper.noteValidations(noteDTO);
		
		noteService.createNote(noteDTO, userId);
		
		Response response = responseHelper.responseStatus(Integer.parseInt(environment.getProperty("statussuccesscode")), environment.getProperty("notesuccessmsg"));
			 				
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updatenote")
	public ResponseEntity<Response> updateNote(HttpServletRequest request, @Valid @RequestBody NoteUpdateDTO noteUpdateDTO) throws Exception {
		
		int userId = Integer.parseInt(request.getAttribute("userID").toString());
		noteService.updateNote(noteUpdateDTO, userId);
		
		Response response = responseHelper.responseStatus(Integer.parseInt(environment.getProperty("statussuccesscode")), environment.getProperty("noteupdatemsg"));
			 				
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updatenoteaddlabelid")
	public ResponseEntity<Response> updateNoteAddLabelId(HttpServletRequest request, @RequestParam int labelId, @RequestParam int noteId) throws Exception {
		
		int userId = Integer.parseInt(request.getAttribute("userID").toString());
		noteService.updateNoteAddLabelId(noteId, labelId, userId);
		
		Response response = responseHelper.responseStatus(Integer.parseInt(environment.getProperty("statussuccesscode")), environment.getProperty("noteupdatemsg"));
			 				
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updatenotedellabelid")
	public ResponseEntity<Response> updateNoteDeleteLabelId(HttpServletRequest request, @RequestParam int labelId, @RequestParam int noteId) throws Exception {
		
		int userId = Integer.parseInt(request.getAttribute("userID").toString());
		noteService.updateNoteDeleteLabelId(noteId, labelId, userId);
		
		Response response = responseHelper.responseStatus(Integer.parseInt(environment.getProperty("statussuccesscode")), environment.getProperty("noteupdatemsg"));
			 				
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletenote/{noteId}")
	public ResponseEntity<Response> deleteNote(HttpServletRequest request, @Valid @PathVariable int noteId) throws Exception {
				
		int userId = Integer.parseInt(request.getAttribute("userID").toString());

		noteService.deleteNote(noteId, userId);
		
		Response response = responseHelper.responseStatus(Integer.parseInt(environment.getProperty("statussuccesscode")), environment.getProperty("notedeletemsg"));
			 				
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/displaynote")
	public List<NoteResponseDTO> displayNotes(HttpServletRequest request) throws Exception {
				
		int userId = Integer.parseInt(request.getAttribute("userID").toString());
		List<NoteResponseDTO> labelList = noteService.displayNotes(userId);

		return labelList;
	}
	
	@PutMapping("/colornote")
	public ResponseEntity<Response> colorNote(HttpServletRequest request, @RequestParam int noteId, @RequestParam String color) throws Exception {
		
		noteHelper.noteColorValidations(noteId, color);
		
		int userId = Integer.parseInt(request.getAttribute("userID").toString());
		noteService.colorNote(noteId, color, userId);
		
		Response response = responseHelper.responseStatus(Integer.parseInt(environment.getProperty("statussuccesscode")), environment.getProperty("noteupdatecolormsg"));
			 				
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/addreminder")
	public ResponseEntity<Response> addreminder(HttpServletRequest request, @RequestBody ReminderDTO reminderDTO) throws Exception {
		
		int userId = Integer.parseInt(request.getAttribute("userID").toString());
		noteService.addreminder(reminderDTO, userId);
		
		Response response = responseHelper.responseStatus(Integer.parseInt(environment.getProperty("statussuccesscode")), environment.getProperty("addremindermsg"));
			 				
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/deletereminder")
	public ResponseEntity<Response> deletereminder(HttpServletRequest request, @RequestParam int noteId) throws Exception {
		
		int userId = Integer.parseInt(request.getAttribute("userID").toString());
		noteService.deletereminder(noteId, userId);
		
		Response response = responseHelper.responseStatus(Integer.parseInt(environment.getProperty("statussuccesscode")), environment.getProperty("deleteremindermsg"));
			 				
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/displayreminder")
	public List<NoteResponseDTO> displayReminder(HttpServletRequest request) throws Exception {
				
		int userId = Integer.parseInt(request.getAttribute("userID").toString());
		List<NoteResponseDTO> labelList = noteService.displayNotesWithReminder(userId);

		return labelList;
	}
	
	@PostMapping("/collaborator")
	public ResponseEntity<Response> collaborator(HttpServletRequest request, @RequestParam int noteId, @RequestParam Set<String> emailId, 
			@RequestParam String userEmailId, @RequestParam String userName) throws Exception {
		
		int userId = Integer.parseInt(request.getAttribute("userID").toString());
		noteService.collaborator(noteId, emailId, userEmailId, userName, userId);
		
		Response response = responseHelper.responseStatus(Integer.parseInt(environment.getProperty("statussuccesscode")), environment.getProperty("noteupdatemsg"));
			 				
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/updatecollaborator")
	public ResponseEntity<Response> updateCollaborator(HttpServletRequest request, @RequestParam int noteId, @RequestParam Set<String> emailId, 
			@RequestParam String userEmailId, @RequestParam String userName) throws Exception {
		
		int userId = Integer.parseInt(request.getAttribute("userID").toString());
		noteService.updateCollaborator(noteId, emailId, userEmailId, userName, userId);
		
		Response response = responseHelper.responseStatus(Integer.parseInt(environment.getProperty("statussuccesscode")), environment.getProperty("noteupdatemsg"));
			 				
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	
	
}