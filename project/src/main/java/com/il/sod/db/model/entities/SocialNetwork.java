package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SocialNetworks database table.
 * 
 */
@Entity
@Table(name="SocialNetworks")
@NamedQuery(name="SocialNetworks.findAll", query="SELECT s FROM SocialNetworks s")
public class SocialNetwork implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idSocialNetworks;

	private String domain;

	private String name;

	//bi-directional many-to-one association to AccessKey
	@OneToMany(mappedBy="socialNetwork")
	private List<AccessKey> accessKeys;

	public SocialNetwork() {
	}

	public int getIdSocialNetworks() {
		return this.idSocialNetworks;
	}

	public void setIdSocialNetworks(int idSocialNetworks) {
		this.idSocialNetworks = idSocialNetworks;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AccessKey> getAccessKeys() {
		return this.accessKeys;
	}

	public void setAccessKeys(List<AccessKey> accessKeys) {
		this.accessKeys = accessKeys;
	}

	public AccessKey addAccessKey(AccessKey accessKey) {
		getAccessKeys().add(accessKey);
		accessKey.setSocialNetwork(this);

		return accessKey;
	}

	public AccessKey removeAccessKey(AccessKey accessKey) {
		getAccessKeys().remove(accessKey);
		accessKey.setSocialNetwork(null);

		return accessKey;
	}

}