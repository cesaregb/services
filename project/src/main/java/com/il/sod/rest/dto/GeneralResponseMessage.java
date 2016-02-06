package com.il.sod.rest.dto;

public class GeneralResponseMessage {
	public static final String GENERIC_MESSAGE_SUCCES = "Success";
	public static final String GENERIC_MESSAGE_ERROR = "Error";
	public static final int TYPE_SUCCES = 0;
	public static final int TYPE_ERROR = 1;
	private String code; 
	private String message; 
	private int type;
	
	public GeneralResponseMessage(){}
	
	public GeneralResponseMessage(int type){
		if (type == TYPE_SUCCES){
			this.message = GENERIC_MESSAGE_SUCCES;
		}else if(type == TYPE_ERROR){
			this.message = GENERIC_MESSAGE_ERROR;
		}
	}
	
	public GeneralResponseMessage(int type, String code){
		this.code = code;
		if (type == TYPE_SUCCES){
			this.message = GENERIC_MESSAGE_SUCCES;
		}else if(type == TYPE_ERROR){
			this.message = GENERIC_MESSAGE_ERROR;
		}
	}
	
	public GeneralResponseMessage(int type, String code, String message){
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
	public GeneralResponseMessage setCode(String code) {
		this.code = code;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public GeneralResponseMessage setMessage(String message) {
		this.message = message;
		return this;
	}
	public int getType() {
		return type;
	}
	public GeneralResponseMessage setType(int type) {
		this.type = type;
		return this;
	} 
}
