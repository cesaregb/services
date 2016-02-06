package com.il.sod.rest.dto.db;

import java.util.List;

public class SocialNetworkDTO {
	private int idSocialNetworks;
	private String domain;
	private String name;
	private List<AccessKeyDTO> accessKeys;
	public int getIdSocialNetworks() {
		return idSocialNetworks;
	}
	public void setIdSocialNetworks(int idSocialNetworks) {
		this.idSocialNetworks = idSocialNetworks;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AccessKeyDTO> getAccessKeys() {
		return accessKeys;
	}
	public void setAccessKeys(List<AccessKeyDTO> accessKeys) {
		this.accessKeys = accessKeys;
	}
}
