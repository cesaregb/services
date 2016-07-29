package com.il.sod.db.model.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the TaskType database table.
 *
 */
@Entity
@NamedQuery(name="TaskType.findAll", query="SELECT t FROM TaskType t")
public class TaskType implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTaskType;

	private String description;

	private String name;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="taskType", fetch=FetchType.EAGER)
	private Set<Task> tasks;
	
	private boolean ordersOnly;

	public TaskType() {
	}

	public int getIdTaskType() {
		return this.idTaskType;
	}

	public void setIdTaskType(int idTaskType) {
		this.idTaskType = idTaskType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setTaskType(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setTaskType(null);

		return task;
	}
	@Override
	public Integer getId() {
		return this.idTaskType;
	}

	@Override
	public TaskType setId(Integer id) {
		this.idTaskType = id;
		return this;
	}

	public boolean isOrdersOnly() {
		return ordersOnly;
	}

	public void setOrdersOnly(boolean ordersOnly) {
		this.ordersOnly = ordersOnly;
	}
}
