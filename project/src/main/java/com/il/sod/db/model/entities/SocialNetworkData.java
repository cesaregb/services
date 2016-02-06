package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SocialNetworkData database table.
 * 
 */
@Entity
@NamedQuery(name="SocialNetworkData.findAll", query="SELECT s FROM SocialNetworkData s")
public class SocialNetworkData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
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

}