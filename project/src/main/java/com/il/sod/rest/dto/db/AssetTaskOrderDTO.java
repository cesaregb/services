package com.il.sod.rest.dto.db;

public class AssetTaskOrderDTO {
	private int idAssetTaskOrder;
	private String comments;
	private Integer asset;
	private Integer orderTask;
	
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
	public Integer getAsset() {
		return asset;
	}
	public void setAsset(Integer asset) {
		this.asset = asset;
	}
	public Integer getOrderTask() {
		return orderTask;
	}
	public void setOrderTask(Integer orderTask) {
		this.orderTask = orderTask;
	}

}
