package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PhoneNumber database table.
 * 
 */
@Entity
@NamedQuery(name="PhoneNumber.findAll", query="SELECT p FROM PhoneNumber p")
public class PhoneNumber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPhoneNumber;

	private String number;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;

	public PhoneNumber() {
	}

	public int getIdPhoneNumber() {
		return this.idPhoneNumber;
	}

	public void setIdPhoneNumber(int idPhoneNumber) {
		this.idPhoneNumber = idPhoneNumber;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}