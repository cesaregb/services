package com.il.sod.rest.dto.db;

import java.util.Set;


public class ClientTypeDTO{
	private int idClientType;
	private String description;
	private String name;
	private Set<ClientDTO> clients;

	public int getIdClientType() {
		return idClientType;
	}

	public void setIdClientType(int idClientType) {
		this.idClientType = idClientType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ClientDTO> getClients() {
		return clients;
	}

	public void setClients(Set<ClientDTO> clients) {
		this.clients = clients;
	}
}