package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the OrderTask database table.
 * 
 */
@Entity
@NamedQuery(name="OrderTask.findAll", query="SELECT o FROM OrderTask o")
public class OrderTask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idOrderTask;

	private String comments;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	//bi-directional many-to-one association to AssetTaskOrder
	@OneToMany(mappedBy="orderTask")
	private List<AssetTaskOrder> assetTaskOrders;

	//bi-directional many-to-one association to EmployeeTaskOrder
	@OneToMany(mappedBy="orderTask")
	private List<EmployeeTaskOrder> employeeTaskOrders;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="idOrder")
	private Order order;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="idTask")
	private Task task;

	public OrderTask() {
	}

	public int getIdOrderTask() {
		return this.idOrderTask;
	}

	public void setIdOrderTask(int idOrderTask) {
		this.idOrderTask = idOrderTask;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<AssetTaskOrder> getAssetTaskOrders() {
		return this.assetTaskOrders;
	}

	public void setAssetTaskOrders(List<AssetTaskOrder> assetTaskOrders) {
		this.assetTaskOrders = assetTaskOrders;
	}

	public AssetTaskOrder addAssetTaskOrder(AssetTaskOrder assetTaskOrder) {
		getAssetTaskOrders().add(assetTaskOrder);
		assetTaskOrder.setOrderTask(this);

		return assetTaskOrder;
	}

	public AssetTaskOrder removeAssetTaskOrder(AssetTaskOrder assetTaskOrder) {
		getAssetTaskOrders().remove(assetTaskOrder);
		assetTaskOrder.setOrderTask(null);

		return assetTaskOrder;
	}

	public List<EmployeeTaskOrder> getEmployeeTaskOrders() {
		return this.employeeTaskOrders;
	}

	public void setEmployeeTaskOrders(List<EmployeeTaskOrder> employeeTaskOrders) {
		this.employeeTaskOrders = employeeTaskOrders;
	}

	public EmployeeTaskOrder addEmployeeTaskOrder(EmployeeTaskOrder employeeTaskOrder) {
		getEmployeeTaskOrders().add(employeeTaskOrder);
		employeeTaskOrder.setOrderTask(this);

		return employeeTaskOrder;
	}

	public EmployeeTaskOrder removeEmployeeTaskOrder(EmployeeTaskOrder employeeTaskOrder) {
		getEmployeeTaskOrders().remove(employeeTaskOrder);
		employeeTaskOrder.setOrderTask(null);

		return employeeTaskOrder;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}