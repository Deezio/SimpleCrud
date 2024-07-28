package com.simple_crud.exception;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private HashMap<String, String> errMap=new HashMap<>();
	private ResponseEntity<HashMap<String, String>> error;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<HashMap<String, String>> allExceptionHandler(Exception ex) {
		if (ex instanceof RuntimeException) {
			errMap.put("Msg", ex.getMessage());
			error = new ResponseEntity<HashMap<String, String>>(errMap, HttpStatus.SERVICE_UNAVAILABLE);
		}
		else if (ex instanceof MethodArgumentNotValidException) {
			((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors().forEach(e -> {
				String fieldName = ((FieldError) e).getField();
				String errorMessage = e.getDefaultMessage();
				errMap.put(fieldName, errorMessage);
			});
			error = new ResponseEntity<HashMap<String, String>>(errMap, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return error;
	}
}