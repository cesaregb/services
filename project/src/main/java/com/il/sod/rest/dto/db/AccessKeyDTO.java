package com.il.sod.rest.dto.db;

import java.util.Set;

public class AccessKeyDTO {
	private int idAccessKey;
	private String token;
	private String tokenSecre;
	private int clientId;
	private SocialNetworkDTO socialNetwork;
	private Set<SocialNetworkDataDTO> socialNetworkData;
	
	public int getIdAccessKey() {
		return idAccessKey;
	}
	public void setIdAccessKey(int idAccessKey) {
		this.idAccessKey = idAccessKey;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTokenSecre() {
		return tokenSecre;
	}
	public void setTokenSecre(String tokenSecre) {
		this.tokenSecre = tokenSecre;
	}
	public SocialNetworkDTO getSocialNetwork() {
		return socialNetwork;
	}
	public void setSocialNetwork(SocialNetworkDTO socialNetwork) {
		this.socialNetwork = socialNetwork;
	}
	public Set<SocialNetworkDataDTO> getSocialNetworkData() {
		return socialNetworkData;
	}
	public void setSocialNetworkData(Set<SocialNetworkDataDTO> socialNetworkData) {
		this.socialNetworkData = socialNetworkData;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

}
