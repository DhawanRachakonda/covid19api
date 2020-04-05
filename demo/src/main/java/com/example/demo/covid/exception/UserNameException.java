package com.example.demo.covid.exception;

import org.springframework.http.HttpStatus;

/**
 * @author explorervarun
 *
 */
public class UserNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;
	
	public UserNameException(String errorMsg,HttpStatus httpStatus) {
		super(errorMsg);
		this.setHttpStatus(httpStatus);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
