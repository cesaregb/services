package com.il.sod.db.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the OrderTemplateTasks database table.
 *
 */
@Entity
@Table(name="OrderTemplateTasks")
@NamedQuery(name="OrderTemplateTask.findAll", query="SELECT o FROM OrderTemplateTask o")
public class OrderTemplateTask implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	@Override
	public Integer getId() {
		return this.idOrderTemplateTasks;
	}

	@Override
	public OrderTemplateTask setId(Integer id) {
		this.idOrderTemplateTasks = id;
		return this;
	}
}
