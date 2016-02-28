package com.il.sod.db.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the AssetTaskOrder database table.
 *
 */
@Entity
@NamedQuery(name="AssetTaskOrder.findAll", query="SELECT a FROM AssetTaskOrder a")
public class AssetTaskOrder implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	@Override
	public Integer getId() {
		return this.idAssetTaskOrder;
	}

	@Override
	public AssetTaskOrder setId(Integer id) {
		this.idAssetTaskOrder = id;
		return this;
	}
}
