package com.il.sod.db.model.entities;

import com.google.common.base.Objects;

import javax.persistence.*;


/**
 * The persistent class for the ServiceTypeTask database table.
 *
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@NamedQuery(name="ServiceTypeTask.findAll", query="SELECT s FROM ServiceTypeTask s")
public class ServiceTypeTask implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idServiceTypeTask;

	//bi-directional many-to-one association to ServiceType
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idServiceType")
	private ServiceType serviceType;

	//bi-directional many-to-one association to Task
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ServiceTypeTask that = (ServiceTypeTask) o;
		return Objects.equal(serviceType, that.serviceType) &&
				Objects.equal(task, that.task);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(serviceType, task);
	}
}
