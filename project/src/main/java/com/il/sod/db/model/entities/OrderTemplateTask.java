package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OrderTemplateTasks database table.
 * 
 */
@Entity
@Table(name="OrderTemplateTasks")
@NamedQuery(name="OrderTemplateTask.findAll", query="SELECT o FROM OrderTemplateTask o")
public class OrderTemplateTask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idOrderTemplateTasks;

	private String description;

	private int time;

	//bi-directional many-to-one association to OrderTemplate
	@ManyToOne
	@JoinColumn(name="idOrderTemplate")
	private OrderTemplate orderTemplate;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="idTask")
	private Task task;

	public OrderTemplateTask() {
	}

	public int getIdOrderTemplateTasks() {
		return this.idOrderTemplateTasks;
	}

	public void setIdOrderTemplateTasks(int idOrderTemplateTasks) {
		this.idOrderTemplateTasks = idOrderTemplateTasks;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTime() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public OrderTemplate getOrderTemplate() {
		return this.orderTemplate;
	}

	public void setOrderTemplate(OrderTemplate orderTemplate) {
		this.orderTemplate = orderTemplate;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}