package com.assignments.organization.exception;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {
      
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> paymentfail(ResourceIdNotFoundException ex){
	ErrorMessage exceptionResponse=new ErrorMessage(ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
}
class ErrorMessage{
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ErrorMessage(String message) {
		super();
		this.message = message;
	
	}
}