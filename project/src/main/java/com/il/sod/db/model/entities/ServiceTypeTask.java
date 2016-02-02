package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ServiceTypeTask database table.
 * 
 */
@Entity
@NamedQuery(name="ServiceTypeTask.findAll", query="SELECT s FROM ServiceTypeTask s")
public class ServiceTypeTask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idServiceTypeTask;

	private String comments;

	//bi-directional many-to-one association to ServiceType
	@ManyToOne
	@JoinColumn(name="idServiceType")
	private ServiceType serviceType;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="idTask")
	private Task task;

	public ServiceTypeTask() {
	}

	public int getIdServiceTypeTask() {
		return this.idServiceTypeTask;
	}

	public void setIdServiceTypeTask(int idServiceTypeTask) {
		this.idServiceTypeTask = idServiceTypeTask;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public ServiceType getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}