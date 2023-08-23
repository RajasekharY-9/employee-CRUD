package com.emp.util;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.emp.exception.EmployeeException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandlerClass {

	@Autowired
	Environment env;
	
	@ExceptionHandler(EmployeeException.class)
	public ResponseEntity<ErrorInfo> employeExceptionHandler(EmployeeException ex){
		
		ErrorInfo er=new ErrorInfo();
		er.setErrorMsg(env.getProperty(ex.getMessage()));
		er.setErrorCode(HttpStatus.NOT_FOUND.value());
		er.setTimeStamp(LocalDate.now());
		return new ResponseEntity<>(er,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> ExceptionHandler(Exception ex){
		
		ErrorInfo er=new ErrorInfo();
		er.setErrorMsg(env.getProperty("GENARAL_EXCEPTION"));
		er.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		er.setTimeStamp(LocalDate.now());
		return new ResponseEntity<>(er,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//Bean validation related exception
	@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ErrorInfo>MethodArgumentValidation(MethodArgumentNotValidException ex){
		
		ErrorInfo er=new ErrorInfo();
		
		String msg=ex.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(", "));
		er.setErrorMsg(msg);
		
		
		er.setErrorCode(HttpStatus.BAD_REQUEST.value());
		er.setTimeStamp(LocalDate.now());
		return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
		
	}
	//Path Variable relatd exception
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorInfo>ConstraintViolationException(ConstraintViolationException ex){
			
			ErrorInfo er=new ErrorInfo();
			
			String msg=ex.getConstraintViolations().stream().map(x -> x.getMessage())
					.collect(Collectors.joining(", "));
			er.setErrorMsg(msg);
			
			
			er.setErrorCode(HttpStatus.BAD_REQUEST.value());
			er.setTimeStamp(LocalDate.now());
			return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
			
		}
		
	
	
	
}
