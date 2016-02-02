package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AccessKey database table.
 * 
 */
@Entity
@NamedQuery(name="AccessKey.findAll", query="SELECT a FROM AccessKey a")
public class AccessKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAccessKey;

	private String token;

	private String tokenSecre;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;

	//bi-directional many-to-one association to SocialNetwork
	@ManyToOne
	@JoinColumn(name="idSocialNetworks")
	private SocialNetwork socialNetwork;

	//bi-directional many-to-one association to SocialNetworkData
	@OneToMany(mappedBy="accessKey")
	private List<SocialNetworkData> socialNetworkData;

	public AccessKey() {
	}

	public int getIdAccessKey() {
		return this.idAccessKey;
	}

	public void setIdAccessKey(int idAccessKey) {
		this.idAccessKey = idAccessKey;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenSecre() {
		return this.tokenSecre;
	}

	public void setTokenSecre(String tokenSecre) {
		this.tokenSecre = tokenSecre;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public SocialNetwork getSocialNetwork() {
		return this.socialNetwork;
	}

	public void setSocialNetwork(SocialNetwork socialNetwork) {
		this.socialNetwork = socialNetwork;
	}

	public List<SocialNetworkData> getSocialNetworkData() {
		return this.socialNetworkData;
	}

	public void setSocialNetworkData(List<SocialNetworkData> socialNetworkData) {
		this.socialNetworkData = socialNetworkData;
	}

	public SocialNetworkData addSocialNetworkData(SocialNetworkData socialNetworkData) {
		getSocialNetworkData().add(socialNetworkData);
		socialNetworkData.setAccessKey(this);

		return socialNetworkData;
	}

	public SocialNetworkData removeSocialNetworkData(SocialNetworkData socialNetworkData) {
		getSocialNetworkData().remove(socialNetworkData);
		socialNetworkData.setAccessKey(null);

		return socialNetworkData;
	}

}