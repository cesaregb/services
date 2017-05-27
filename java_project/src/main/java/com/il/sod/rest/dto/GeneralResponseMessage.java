package com.il.sod.rest.dto;

public class GeneralResponseMessage {
	public static final String GENERIC_MESSAGE_SUCCES = "Success";
	public static final String GENERIC_MESSAGE_ERROR = "Error";
	
	private String message; 
	private String developerMessage;
	private String link;
	private boolean status;
	
	public GeneralResponseMessage(){}

	public GeneralResponseMessage(boolean status, String message){
		this.status = status;
		this.message = message;
	}

	public GeneralResponseMessage(String message, String developerMessage, String link, boolean status) {
		this.message = message;
		this.developerMessage = developerMessage;
		this.link = link;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
