package com.il.sod.exception;

public class SODAPIException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public static final Integer BAD_REQUEST_CODE = 1;
	
	Integer code = 0;
	Integer status = 500; // default code server error 
	
	public SODAPIException(Integer code, String message) {
		super(message);
		this.code = code;
	}
	
	public SODAPIException(Integer code, String message, Exception e) {
		super(message, e);
		this.code = code;
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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}			
}