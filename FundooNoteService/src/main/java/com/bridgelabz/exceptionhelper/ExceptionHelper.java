package com.bridgelabz.exceptionhelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.exceptions.LabelException;
import com.bridgelabz.exceptions.NoteColorValidationException;
import com.bridgelabz.exceptions.NoteException;
import com.bridgelabz.exceptions.NoteValidationException;
import com.bridgelabz.exceptions.TokenException;
import com.bridgelabz.models.Response;
import com.bridgelabz.utility.ResponseHelper;

@ControllerAdvice
@PropertySource("classpath:error.properties")
public class ExceptionHelper {
	
	@Autowired
    private Environment environment;
	
	@Autowired
	private ResponseHelper responseHelper;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Response> handleException(Exception exception) {
		
		Response response = responseHelper.responseStatus(Integer.parseInt(environment.getProperty("statusexceptioncode")), environment.getProperty("errormessage"));
		logger.error("Error : " +exception.getMessage(), exception);
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	} 
	
	@ExceptionHandler(value = NoteValidationException.class)
	public ResponseEntity<Response> handleNoteValidationException(NoteValidationException exception) {
		
		Response response = responseHelper.responseStatus(-2, exception.getMessage());
		logger.error("Error : " +exception.getMessage(), exception);
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NoteColorValidationException.class)
	public ResponseEntity<Response> handleNoteValidationException(NoteColorValidationException exception) {
		
		Response response = responseHelper.responseStatus(-3, exception.getMessage());
		logger.error("Error : " +exception.getMessage(), exception);
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NoteException.class)
	public ResponseEntity<Response> handleNoteException(NoteException exception) {
		
		Response response = responseHelper.responseStatus(-4, exception.getMessage());
		logger.error("Error : " +exception.getMessage(), exception);
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	} 
	
	@ExceptionHandler(value = TokenException.class)
	public ResponseEntity<Response> handleTokenException(TokenException exception) {
		
		Response response = responseHelper.responseStatus(-5, exception.getMessage());
		logger.error("Error : " +exception.getMessage(), exception);
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	} 
	
	@ExceptionHandler(value = LabelException.class)
	public ResponseEntity<Response> handleLabelException(LabelException exception) {
		
		Response response = responseHelper.responseStatus(-6, exception.getMessage());
		logger.error("Error : " +exception.getMessage(), exception);
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	} 
	
}