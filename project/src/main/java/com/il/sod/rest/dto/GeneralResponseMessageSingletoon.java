package com.il.sod.rest.dto;

public class GeneralResponseMessageSingletoon {
	private static final String GENERIC_MESSAGE_SUCCES = "Success";
	private static final String GENERIC_MESSAGE_ERROR = "Error";

	private String message;
	private boolean status;

	private static class Holder {
        static final GeneralResponseMessageSingletoon INSTANCE = new GeneralResponseMessageSingletoon();
    }

	private GeneralResponseMessageSingletoon(){}
	
	public static GeneralResponseMessageSingletoon getInstance() {
		return Holder.INSTANCE.success();
	}
	
	public GeneralResponseMessageSingletoon success(){
		this.message = GENERIC_MESSAGE_SUCCES;
		this.status = true;
		return this;
	}
	
	public GeneralResponseMessageSingletoon error(){
		this.message = GENERIC_MESSAGE_ERROR;
		this.status = false;
		return this;
	}
	
	public String getMessage() {
		return message;
	}
	public GeneralResponseMessageSingletoon setMessage(String message) {
		this.message = message;
		return this;
	}

	public boolean isStatus() {
		return status;
	}

	public GeneralResponseMessageSingletoon setStatus(boolean status) {
		this.status = status;
		return this;
	}
}
