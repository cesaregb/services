package com.il.sod.rest.dto;

public class GeneralResponseMessage {
	private static final String GENERIC_MESSAGE_SUCCES = "Success";
	private static final String GENERIC_MESSAGE_ERROR = "Error";
	
	private int code; 
	private String message; 
	private boolean status;
	
	private static class Holder {
        static final GeneralResponseMessage INSTANCE = new GeneralResponseMessage();
    }
	
	private GeneralResponseMessage(){}
	
	public static GeneralResponseMessage getInstance() {
		return Holder.INSTANCE.success();
	}
	
	public GeneralResponseMessage success(){
		this.code = 200;
		this.message = GENERIC_MESSAGE_SUCCES;
		this.status = true;
		return this;
	}
	
	public GeneralResponseMessage error(){
		this.code = 400;
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

	public int getCode() {
		return code;
	}

	public GeneralResponseMessage setCode(int code) {
		this.code = code;
		return this;
	}
}
