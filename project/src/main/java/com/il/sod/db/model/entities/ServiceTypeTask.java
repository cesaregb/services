package com.il.sod.db.model.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the ServiceTypeTask database table.
 *
 */
@Entity
@NamedQuery(name="ServiceTypeTask.findAll", query="SELECT s FROM ServiceTypeTask s")
public class ServiceTypeTask implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idServiceTypeTask;

	//bi-directional many-to-one association to ServiceType
	@ManyToOne
	@JoinColumn(name="idServiceType")
	private ServiceType serviceType;

	//bi-directional many-to-one association to Task
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idTask")
	private Task task;
	
	private int sortingOrder;
	
	private int time;

	public ServiceTypeTask() {
	}

	public int getIdServiceTypeTask() {
		return this.idServiceTypeTask;
	}

	public void setIdServiceTypeTask(int idServiceTypeTask) {
		this.idServiceTypeTask = idServiceTypeTask;
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
	@Override
	public Integer getId() {
		return this.idServiceTypeTask;
	}

	@Override
	public ServiceTypeTask setId(Integer id) {
		this.idServiceTypeTask = id;
		return this;
	}

	public int getSortingOrder() {
		return sortingOrder;
	}

	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
