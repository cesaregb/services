package com.il.sod.exception;

import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;

public class SODAPIException extends Exception {

	private static final long serialVersionUID = 1L;

	HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	String message;

	// TODO implement formatter
	public SODAPIException(Response.Status status, String message, Object... messageSubstitutions) {
		this.message = String.format(message, messageSubstitutions);
		this.status = HttpStatus.valueOf(status.getStatusCode());
	}

	public SODAPIException(HttpStatus status, String message, Object... messageSubstitutions) {
		this.message = String.format(message, messageSubstitutions);
		this.status = status;
	}

	public SODAPIException(Response.Status status, String message) {
		this.message = message;
		this.status = HttpStatus.valueOf(status.getStatusCode());
	}
	
	public SODAPIException(Response.Status status, String message, Exception e) {
		super(e);
		this.message = message;
		this.status = HttpStatus.valueOf(status.getStatusCode());
	}
	
	public SODAPIException(String message) {
		super(message);
	}
	
	public SODAPIException(String message, Exception e) {
		super(message, e);
	}


	public SODAPIException(Exception e) { 
		super(e);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	// Overrides Exception's getMessage()
	@Override
	public String getMessage(){
		if (message != null){
			return message;
		}else {
			return "Error in the server.... ups";
		}
	}

}