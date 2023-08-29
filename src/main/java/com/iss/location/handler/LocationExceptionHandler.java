package com.iss.location.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This is a Global Exception Handler class for handling exception in multiple
 * controller classes.
 * 
 * @author Aditya Dongre
 * @version 1.0
 *
 */
@RestControllerAdvice
public class LocationExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handle(Exception ex) {
		if (ex instanceof NullPointerException) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("server is down. please try again later..", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
