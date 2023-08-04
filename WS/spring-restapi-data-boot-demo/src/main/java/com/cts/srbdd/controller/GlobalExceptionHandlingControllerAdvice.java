package com.cts.srbdd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.srbdd.exception.InvalidEmployeeDetailsException;

@RestControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {

	@ExceptionHandler(InvalidEmployeeDetailsException.class)
	public ResponseEntity<String> handleInvalidEmployeeDetailsException(InvalidEmployeeDetailsException exception){
		String errMsg = exception.
				getBindingResult().
				getAllErrors().
				stream().
				map(err -> err.getDefaultMessage()).
				reduce("", (m1,m2) -> m1+","+m2);
		
		return new ResponseEntity<String>(errMsg,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleUnhandledException(Exception exception){
		return new ResponseEntity<String>("Something went wrong! Please try again later!",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
