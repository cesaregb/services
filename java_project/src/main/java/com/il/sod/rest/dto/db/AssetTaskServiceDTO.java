package com.il.sod.rest.dto.db;

public class AssetTaskServiceDTO {
	private int idAssetTaskService;
	private String comments;
	private int idAsset;
	private int idServiceTask;
	
	public int getIdAssetTaskService() {
		return idAssetTaskService;
	}
	public void setIdAssetTaskService(int idAssetTaskService) {
		this.idAssetTaskService = idAssetTaskService;
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
	public int getIdServiceTask() {
		return idServiceTask;
	}
	public void setIdServiceTask(int idServiceTask) {
		this.idServiceTask = idServiceTask;
	}

}
