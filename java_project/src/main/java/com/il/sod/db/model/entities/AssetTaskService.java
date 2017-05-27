package com.il.sod.db.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the AssetTaskService database table.
 *
 */
@Entity
@NamedQuery(name="AssetTaskService.findAll", query="SELECT a FROM AssetTaskService a")
public class AssetTaskService implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAssetTaskService;

	private String comments;

	//bi-directional many-to-one association to Asset
	@ManyToOne
	@JoinColumn(name="idAsset")
	private Asset asset;

	//bi-directional many-to-one association to ServiceTask
	@ManyToOne
	@JoinColumn(name="idServiceTask")
	private ServiceTask serviceTask;

	public AssetTaskService() {
	}

	public int getIdAssetTaskService() {
		return this.idAssetTaskService;
	}

	public void setIdAssetTaskService(int idAssetTaskService) {
		this.idAssetTaskService = idAssetTaskService;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Asset getAsset() {
		return this.asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public ServiceTask getServiceTask() {
		return this.serviceTask;
	}

	public void setServiceTask(ServiceTask serviceTask) {
		this.serviceTask = serviceTask;
	}
	@Override
	public Integer getId() {
		return this.idAssetTaskService;
	}

	@Override
	public AssetTaskService setId(Integer id) {
		this.idAssetTaskService = id;
		return this;
	}
}
