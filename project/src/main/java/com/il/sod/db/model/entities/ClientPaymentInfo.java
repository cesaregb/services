package com.il.sod.db.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the ClientPaymentInfo database table.
 * 
 */
@Entity
@NamedQuery(name="ClientPaymentInfo.findAll", query="SELECT c FROM ClientPaymentInfo c")
public class ClientPaymentInfo implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idClientPaymentInfo;

	private String token;

	private int type;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="idClient")
	@JsonBackReference
	private Client client;

	public ClientPaymentInfo() {
	}

	public int getIdClientPaymentInfo() {
		return this.idClientPaymentInfo;
	}

	public void setIdClientPaymentInfo(int idClientPaymentInfo) {
		this.idClientPaymentInfo = idClientPaymentInfo;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public Integer getId() {
		return this.idClientPaymentInfo;
	}

	@Override
	public ClientPaymentInfo setId(Integer id) {
		this.idClientPaymentInfo = id;
		return this;
	}

}