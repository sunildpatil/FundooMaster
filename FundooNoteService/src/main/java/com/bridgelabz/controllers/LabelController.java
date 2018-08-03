package com.bridgelabz.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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

import com.bridgelabz.models.LabelCreateDTO;
import com.bridgelabz.models.LabelResponseDTO;
import com.bridgelabz.models.LabelUpdateDTO;
import com.bridgelabz.models.Response;
import com.bridgelabz.services.LabelService;
import com.bridgelabz.utility.ResponseHelper;

@RestController
@PropertySource("classpath:error.properties")
@RequestMapping(value="/labels")
public class LabelController {
	
		@Autowired
		private LabelService labelService;
		
		@Autowired
		private ResponseHelper responseHelper;
				
		@PostMapping("/createlabel")
		public ResponseEntity<Response> createLabel(HttpServletRequest request, @Valid @RequestBody LabelCreateDTO labelCreateDTO) throws Exception {
			
			int userId = Integer.parseInt(request.getAttribute("userID").toString());
			
			labelService.createLabel(userId, labelCreateDTO);
			
			Response response = responseHelper.responseStatus(1, "Label Created Successfully");
				 				
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		@PutMapping("/updatelabelname")
		public ResponseEntity<Response> updateLabelName(HttpServletRequest request, @Valid @RequestBody LabelUpdateDTO labelUpdateDTO) throws Exception {
			
			int userId = Integer.parseInt(request.getAttribute("userID").toString());
			labelService.updateLabel(userId, labelUpdateDTO);
			
			Response response = responseHelper.responseStatus(1, "Label Name Updated Successfully");
				 				
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		@PutMapping(value="/updatelabeladdnoteid")
		public ResponseEntity<Response> updateNoteId(HttpServletRequest request, @RequestParam int labelId, @RequestParam int noteId) throws Exception {
			
			int userId = Integer.parseInt(request.getAttribute("userID").toString());
			labelService.updateLabelNoteId(userId, labelId, noteId);
			
			Response response = responseHelper.responseStatus(1, "Update Label Note Id Added Successfully");
				 				
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		@PutMapping(value="/updatelabeldelnoteid")
		public ResponseEntity<Response> deleteNoteId(HttpServletRequest request, @RequestParam int labelId, @RequestParam int noteId) throws Exception {
			
			int userId = Integer.parseInt(request.getAttribute("userID").toString());
			labelService.updateLabelDeleteNoteId(userId, labelId, noteId);
			
			Response response = responseHelper.responseStatus(1, "Update Label Note Id  Deleted Successfully");
				 				
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		@DeleteMapping("/deletelabel/{labelId}")
		public ResponseEntity<Response> deleteNote(HttpServletRequest request, @PathVariable int labelId) throws Exception {
					
			int userId = Integer.parseInt(request.getAttribute("userID").toString());
			labelService.deleteLabel(userId, labelId);
			
			Response response = responseHelper.responseStatus(1, "Label Deleted Successfully");
				 				
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		@GetMapping("/displaylabel")
		public List<LabelResponseDTO> displayNotes(HttpServletRequest request) throws Exception {
					
			int userId = Integer.parseInt(request.getAttribute("userID").toString());
			List<LabelResponseDTO> noteList = labelService.displayLabel(userId);

			return noteList;
		}
}
