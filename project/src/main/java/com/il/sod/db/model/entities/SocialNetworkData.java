package com.il.sod.db.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the SocialNetworkData database table.
 *
 */
@Entity
@NamedQuery(name="SocialNetworkData.findAll", query="SELECT s FROM SocialNetworkData s")
public class SocialNetworkData implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSocialNetworkData;

	@Lob
	private String data;

	//bi-directional many-to-one association to AccessKey
	@ManyToOne
	@JoinColumn(name="idAccessKey")
	private AccessKey accessKey;

	public SocialNetworkData() {
	}

	public int getIdSocialNetworkData() {
		return this.idSocialNetworkData;
	}

	public void setIdSocialNetworkData(int idSocialNetworkData) {
		this.idSocialNetworkData = idSocialNetworkData;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public AccessKey getAccessKey() {
		return this.accessKey;
	}

	public void setAccessKey(AccessKey accessKey) {
		this.accessKey = accessKey;
	}
	@Override
	public Integer getId() {
		return this.idSocialNetworkData;
	}

	@Override
	public SocialNetworkData setId(Integer id) {
		this.idSocialNetworkData = id;
		return this;
	}
}
