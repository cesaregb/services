package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AssetTaskOrder database table.
 * 
 */
@Entity
@NamedQuery(name="AssetTaskOrder.findAll", query="SELECT a FROM AssetTaskOrder a")
public class AssetTaskOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAssetTaskOrder;

	private String comments;

	//bi-directional many-to-one association to Asset
	@ManyToOne
	private Asset asset;

	//bi-directional many-to-one association to OrderTask
	@ManyToOne
	@JoinColumn(name="idOrderTask")
	private OrderTask orderTask;

	public AssetTaskOrder() {
	}

	public int getIdAssetTaskOrder() {
		return this.idAssetTaskOrder;
	}

	public void setIdAssetTaskOrder(int idAssetTaskOrder) {
		this.idAssetTaskOrder = idAssetTaskOrder;
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

	public OrderTask getOrderTask() {
		return this.orderTask;
	}

	public void setOrderTask(OrderTask orderTask) {
		this.orderTask = orderTask;
	}

}