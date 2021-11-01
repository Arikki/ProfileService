package com.profileService.error;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.profileService.entity.ErrorMessage;

import org.springframework.dao.DuplicateKeyException;



@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		// TODO Auto-generated method stub
//		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getBindingResult().getFieldError().getDefaultMessage());
//		return handleExceptionInternal(ex, message, headers, HttpStatus.BAD_REQUEST, request);
//	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorMessage> badCredentialsException(UsernameNotFoundException exception, WebRequest request) {
		ErrorMessage message =null;
		
		
		switch (exception.getMessage()) {
		case "No value present":
			message = new ErrorMessage(HttpStatus.UNAUTHORIZED, "Invalid EmailId");
			break;
		
		case "Bad credentials":
			message = new ErrorMessage(HttpStatus.UNAUTHORIZED, "Invalid Password");
			break;
			
		default:
			message = new ErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
			break;
			
		}
		
	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
	
}
	
	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<ErrorMessage> dupKeyException(DuplicateKeyException exception, WebRequest request) {
		 
		
		 ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, "This Email id is already in use");
		
		
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	
} 

	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ErrorMessage> invalidDataException(InvalidDataException exception, WebRequest request) {

		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
		
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	
}
	

}
