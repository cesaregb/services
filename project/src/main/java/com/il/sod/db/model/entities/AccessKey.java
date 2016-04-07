package com.il.sod.db.model.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the AccessKey database table.
 *
 */
@Entity
@NamedQuery(name="AccessKey.findAll", query="SELECT a FROM AccessKey a")
public class AccessKey implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	@OneToMany(mappedBy="accessKey", fetch=FetchType.EAGER)
	private Set<SocialNetworkData> socialNetworkData;

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

	public Set<SocialNetworkData> getSocialNetworkData() {
		return this.socialNetworkData;
	}

	public void setSocialNetworkData(Set<SocialNetworkData> socialNetworkData) {
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

	@Override
	public Integer getId() {
		return this.idAccessKey;
	}

	@Override
	public AccessKey setId(Integer id) {
		this.idAccessKey = id;
		return this;
	}
}
