package com.il.sod.exception;

import javax.ws.rs.core.Response;

public class SODAPIException extends Exception {

	private static final long serialVersionUID = 1L;

	Response.Status status = Response.Status.INTERNAL_SERVER_ERROR; 
	
	public SODAPIException(Response.Status status, String message) {
		super(message);
		this.status = status;
	}
	
	public SODAPIException(Response.Status status, String message, Exception e) {
		super(message, e);
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

}