package com.il.sod.db.model.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the OrderTypeTasks database table.
 *
 */
@Entity
@Table(name="OrderTypeTasks")
@NamedQuery(name="OrderTypeTask.findAll", query="SELECT o FROM OrderTypeTask o")
public class OrderTypeTask implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrderTypeTasks;

	//bi-directional many-to-one association to OrderType
	@ManyToOne
	@JoinColumn(name="idOrderType")
	@JsonBackReference
	private OrderType orderType;

	//bi-directional many-to-one association to Task
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idTask")
	@JsonBackReference
	private Task task;
	
	private int sortingOrder;

	public OrderTypeTask() {
	}

	public int getIdOrderTypeTasks() {
		return this.idOrderTypeTasks;
	}

	public void setIdOrderTypeTasks(int idOrderTypeTasks) {
		this.idOrderTypeTasks = idOrderTypeTasks;
	}

	public OrderType getOrderType() {
		return this.orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	@Override
	public Integer getId() {
		return this.idOrderTypeTasks;
	}

	@Override
	public OrderTypeTask setId(Integer id) {
		this.idOrderTypeTasks = id;
		return this;
	}

	public int getSortingOrder() {
		return sortingOrder;
	}

	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}
}
