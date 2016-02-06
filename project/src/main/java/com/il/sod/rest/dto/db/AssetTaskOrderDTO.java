package com.il.sod.rest.dto.db;

public class AssetTaskOrderDTO {
	private int idAssetTaskOrder;
	private String comments;
	private AssetDTO asset;
	private OrderTaskDTO orderTask;
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
	public AssetDTO getAsset() {
		return asset;
	}
	public void setAsset(AssetDTO asset) {
		this.asset = asset;
	}
	public OrderTaskDTO getOrderTask() {
		return orderTask;
	}
	public void setOrderTask(OrderTaskDTO orderTask) {
		this.orderTask = orderTask;
	}

}
