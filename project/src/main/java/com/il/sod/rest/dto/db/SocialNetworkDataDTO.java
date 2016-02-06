package com.il.sod.rest.dto.db;

public class SocialNetworkDataDTO {
	private int idSocialNetworkData;
	private String data;
	private AccessKeyDTO accessKey;
	public int getIdSocialNetworkData() {
		return idSocialNetworkData;
	}
	public void setIdSocialNetworkData(int idSocialNetworkData) {
		this.idSocialNetworkData = idSocialNetworkData;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public AccessKeyDTO getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(AccessKeyDTO accessKey) {
		this.accessKey = accessKey;
	}
}
