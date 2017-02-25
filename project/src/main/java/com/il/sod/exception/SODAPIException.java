package com.il.sod.exception;

import javax.ws.rs.core.Response;

public class SODAPIException extends Exception {

	private static final long serialVersionUID = 1L;

	Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
	String message;

	// TODO implement formatter
	public SODAPIException(Response.Status status, String message, Object... messageSubstitutions) {
		this.message = String.format(message, messageSubstitutions);
		this.status = status;
	}

	public SODAPIException(Response.Status status, String message) {
		this.message = message;
		this.status = status;
	}
	
	public SODAPIException(Response.Status status, String message, Exception e) {
		super(e);
		this.message = message;
		this.status = status;
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

	public Response.Status getStatus() {
		return status;
	}

	public void setStatus(Response.Status status) {
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