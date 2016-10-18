package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the BagType database table.
 * 
 */
@Entity
@NamedQuery(name="BagSize.findAll", query="SELECT b FROM BagType b")
public class BagType implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idBagType;

	private String name;

	private int size;

	//bi-directional many-to-one association to ClientBag
	@OneToMany(mappedBy="bagType")
	private Set<ClientBag> clientBags;

	public BagType() {
	}

	public int getIdBagType() {
		return idBagType;
	}

	public void setIdBagType(int idBagType) {
		this.idBagType = idBagType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Set<ClientBag> getClientBags() {
		return this.clientBags;
	}

	public void setClientBags(Set<ClientBag> clientBags) {
		this.clientBags = clientBags;
	}

	public ClientBag addClientBag(ClientBag clientBag) {
		getClientBags().add(clientBag);
		clientBag.setBagType(this);

		return clientBag;
	}

	public ClientBag removeClientBag(ClientBag clientBag) {
		getClientBags().remove(clientBag);
		clientBag.setBagType(null);

		return clientBag;
	}

	@Override
	public Integer getId() {
		return this.idBagType;
	}

	@Override
	public BagType setId(Integer id) {
		this.idBagType = id;
		return this;
	}
}