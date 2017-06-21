package com.il.sod.rest.dto.db;

public class ServiceCommentDTO {
	private int idServiceComments;
	private String comment;
	private int idService;
	
	public int getIdServiceComments() {
		return idServiceComments;
	}
	public void setIdServiceComments(int idServiceComments) {
		this.idServiceComments = idServiceComments;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getIdService() {
		return idService;
	}
	public void setIdService(int idService) {
		this.idService = idService;
	}
}