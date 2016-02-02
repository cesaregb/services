package com.il.sod.rest.dto;

public class SimpleResponseMessage {
	public static final String GENERIC_MESSAGE_SUCCES = "Success";
	public static final String GENERIC_MESSAGE_ERROR = "Error";
	public static final int TYPE_SUCCES = 0;
	public static final int TYPE_ERROR = 1;
	private String code; 
	private String message; 
	private int type;
	
	public SimpleResponseMessage(){}
	
	public SimpleResponseMessage(int type){
		if (type == TYPE_SUCCES){
			this.message = GENERIC_MESSAGE_SUCCES;
		}else if(type == TYPE_ERROR){
			this.message = GENERIC_MESSAGE_ERROR;
		}
	}
	
	public SimpleResponseMessage(int type, String code){
		this.code = code;
		if (type == TYPE_SUCCES){
			this.message = GENERIC_MESSAGE_SUCCES;
		}else if(type == TYPE_ERROR){
			this.message = GENERIC_MESSAGE_ERROR;
		}
	}
	
	public SimpleResponseMessage(int type, String code, String message){
		this.code = code;
		if (type == TYPE_SUCCES){
			this.message = GENERIC_MESSAGE_SUCCES;
		}else if(type == TYPE_ERROR){
			this.message = GENERIC_MESSAGE_ERROR;
		}
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	} 
}
