package com.il.sod.exception;

public class SODAPIException extends Exception {

	private static final long serialVersionUID = 1L;

	Integer status = 500; // default code server error 
	
	public SODAPIException(Integer status, String message) {
		super(message);
		this.status = status;
	}
	
	public SODAPIException(Integer status, String message, Exception e) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}			
}