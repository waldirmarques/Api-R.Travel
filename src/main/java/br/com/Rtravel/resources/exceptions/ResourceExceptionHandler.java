package br.com.Rtravel.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.Rtravel.services.exceptions.AuthorizationException;
import br.com.Rtravel.services.exceptions.DataIntegrityException;
import br.com.Rtravel.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotfound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(),System.currentTimeMillis());
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> objectNotfound(DataIntegrityException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),e.getMessage(),System.currentTimeMillis());
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),"Erro de Validação",System.currentTimeMillis());
		
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	
}