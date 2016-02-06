package com.il.sod.rest.dto.db;

public class AssetTaskServiceDTO {
	private int idAssetTaskService;
	private String comments;
	private AssetDTO asset;
	private ServiceTaskDTO serviceTask;
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
	public AssetDTO getAsset() {
		return asset;
	}
	public void setAsset(AssetDTO asset) {
		this.asset = asset;
	}
	public ServiceTaskDTO getServiceTask() {
		return serviceTask;
	}
	public void setServiceTask(ServiceTaskDTO serviceTask) {
		this.serviceTask = serviceTask;
	}

}
