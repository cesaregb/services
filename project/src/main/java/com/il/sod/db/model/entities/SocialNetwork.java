package com.il.sod.db.model.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the SocialNetworks database table.
 *
 */
@Entity
@Table(name="SocialNetworks")
@NamedQuery(name="SocialNetwork.findAll", query="SELECT s FROM SocialNetwork s")
public class SocialNetwork implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSocialNetworks;

	private String domain;

	private String name;

	//bi-directional many-to-one association to AccessKey
	@OneToMany(mappedBy="socialNetwork", fetch=FetchType.EAGER)
	private Set<AccessKey> accessKeys;

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

	public Set<AccessKey> getAccessKeys() {
		return this.accessKeys;
	}

	public void setAccessKeys(Set<AccessKey> accessKeys) {
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
	@Override
	public Integer getId() {
		return this.idSocialNetworks;
	}

	@Override
	public SocialNetwork setId(Integer id) {
		this.idSocialNetworks = id;
		return this;
	}
}
