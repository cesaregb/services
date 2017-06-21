package com.il.sod.rest.dto.db;

public class AssetTaskOrderDTO {
	private int idAssetTaskOrder;
	private String comments;
	private int idAsset;
	private int idOrderTask;
	
	public int getIdAssetTaskOrder() {
		return idAssetTaskOrder;
	}
	public void setIdAssetTaskOrder(int idAssetTaskOrder) {
		this.idAssetTaskOrder = idAssetTaskOrder;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getIdAsset() {
		return idAsset;
	}
	public void setIdAsset(int idAsset) {
		this.idAsset = idAsset;
	}
	public int getIdOrderTask() {
		return idOrderTask;
	}
	public void setIdOrderTask(int idOrderTask) {
		this.idOrderTask = idOrderTask;
	}

}
