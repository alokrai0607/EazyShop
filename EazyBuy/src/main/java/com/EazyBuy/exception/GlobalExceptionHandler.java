package com.EazyBuy.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {

	

	
	
//	ORDER EXCEPTION HANDLER 
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorDetails> OrderExcetionHandler(OrderException e, WebRequest req){
		
		MyErrorDetails er= new MyErrorDetails();
		er.setTimeStamp(LocalDateTime.now());
		er.setMessage(e.getMessage());
		er.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(er,HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	
	
	
//	CART EXCEPTION HANDLER 
	
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<MyErrorDetails> CartExcetionHandler(CartException e, WebRequest req){
		
		MyErrorDetails er= new MyErrorDetails();
		er.setTimeStamp(LocalDateTime.now());
		er.setMessage(e.getMessage());
		er.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(er,HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	
	
//	ALL EXCEPTION HANDLER 
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> AllExcetionHandler(Exception e,WebRequest req){
		
		MyErrorDetails er= new MyErrorDetails();
		er.setTimeStamp(LocalDateTime.now());
		er.setMessage(e.getMessage());
		er.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(er,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> customerExceptionHandller(CustomerException ce, WebRequest req ) {
		MyErrorDetails err = new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ce.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InputInvalidException.class)
	public ResponseEntity<MyErrorDetails> inputInvalidExceptionHandller(InputInvalidException ce, WebRequest req ) {
		MyErrorDetails err = new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ce.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler(AlreadyExistedException.class)
	public ResponseEntity<MyErrorDetails> alreadyExistedExceptionHandller(AlreadyExistedException ce, WebRequest req ) {
		MyErrorDetails err = new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ce.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.NOT_ACCEPTABLE);
	}
	
	//to handle Not found exception 
		@ExceptionHandler(NoHandlerFoundException.class)
		public ResponseEntity<MyErrorDetails> mynotFoundHandler(NoHandlerFoundException nfe,WebRequest req)  {
				
		
			MyErrorDetails er= new MyErrorDetails();
			er.setTimeStamp(LocalDateTime.now());
			er.setMessage(nfe.getMessage());
			er.setDetails(req.getDescription(false));
		
			return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
						
		}
	
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyErrorDetails> ProductExcetionHandler(ProductException e, WebRequest req){
		
		MyErrorDetails er= new MyErrorDetails();
		er.setTimeStamp(LocalDateTime.now());
		er.setMessage(e.getMessage());
		er.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(er,HttpStatus.BAD_REQUEST);
		
	}
	
	//ADMIN EXCEPTION CLASS
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> adminExceptionHandler(AdminException e, WebRequest req){
	    MyErrorDetails er= new MyErrorDetails();
	    er.setTimeStamp(LocalDateTime.now());
	    er.setMessage(e.getMessage());
	    er.setDetails(req.getDescription(false));

	    return new ResponseEntity<MyErrorDetails>(er,HttpStatus.BAD_REQUEST);
	}

	
}
