package com.il.sod.rest.dto;

public class GeneralResponseMessage {
	private static final String GENERIC_MESSAGE_SUCCES = "Success";
	private static final String GENERIC_MESSAGE_ERROR = "Error";
	
	private String message; 
	private boolean status;
	
	public GeneralResponseMessage(){}

	public GeneralResponseMessage(boolean status, String message){
		this.status = status;
		this.message = message;
	}

	public GeneralResponseMessage success(){
		this.message = GENERIC_MESSAGE_SUCCES;
		this.status = true;
		return this;
	}
	
	public GeneralResponseMessage error(){
		this.message = GENERIC_MESSAGE_ERROR;
		this.status = false;
		return this;
	}
	
	public String getMessage() {
		return message;
	}
	public GeneralResponseMessage setMessage(String message) {
		this.message = message;
		return this;
	}

	public boolean isStatus() {
		return status;
	}

	public GeneralResponseMessage setStatus(boolean status) {
		this.status = status;
		return this;
	}
}
