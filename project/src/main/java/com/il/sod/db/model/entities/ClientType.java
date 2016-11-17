package com.il.sod.db.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the ClientType database table.
 * 
 */
@Entity
@NamedQuery(name="ClientType.findAll", query="SELECT c FROM ClientType c")
public class ClientType implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idClientType;

	private String description;

	private String name;

	//bi-directional many-to-one association to Client
	@OneToMany(mappedBy="clientType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Client> clients;

	public ClientType() {
	}

	public int getIdClientType() {
		return this.idClientType;
	}

	public void setIdClientType(int idClientType) {
		this.idClientType = idClientType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Client> getClients() {
		return this.clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	public Client addClient(Client client) {
		getClients().add(client);
		client.setClientType(this);

		return client;
	}

	public Client removeClient(Client client) {
		getClients().remove(client);
		client.setClientType(null);

		return client;
	}

	@Override
	public Integer getId() {
		return this.idClientType;
	}

	@Override
	public ClientType setId(Integer id) {
		this.idClientType = id;
		return this;
	}
}