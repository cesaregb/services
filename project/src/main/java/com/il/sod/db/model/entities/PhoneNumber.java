package com.il.sod.db.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the PhoneNumber database table.
 *
 */
@Entity
@NamedQuery(name="PhoneNumber.findAll", query="SELECT p FROM PhoneNumber p")
public class PhoneNumber implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPhoneNumber;

	private String number;
	
	private int prefered;

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
	@Override
	public Integer getId() {
		return this.idPhoneNumber;
	}

	@Override
	public PhoneNumber setId(Integer id) {
		this.idPhoneNumber = id;
		return this;
	}

	public int getPrefered() {
		return prefered;
	}

	public void setPrefered(int prefered) {
		this.prefered = prefered;
	}
}
