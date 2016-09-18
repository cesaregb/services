package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the BagSize database table.
 * 
 */
@Entity
@NamedQuery(name="BagSize.findAll", query="SELECT b FROM BagSize b")
public class BagSize implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idBagSize;

	private String name;

	private int size;

	//bi-directional many-to-one association to ClientBag
	@OneToMany(mappedBy="bagSize")
	private Set<ClientBag> clientBags;

	public BagSize() {
	}

	public int getIdBagSize() {
		return this.idBagSize;
	}

	public void setIdBagSize(int idBagSize) {
		this.idBagSize = idBagSize;
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
		clientBag.setBagSize(this);

		return clientBag;
	}

	public ClientBag removeClientBag(ClientBag clientBag) {
		getClientBags().remove(clientBag);
		clientBag.setBagSize(null);

		return clientBag;
	}

	@Override
	public Integer getId() {
		return this.getIdBagSize();
	}

	@Override
	public BagSize setId(Integer id) {
		this.idBagSize = id;
		return this;
	}
}