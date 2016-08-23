package com.il.sod.db.model.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the Task database table.
 *
 */
@Entity
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTask;

	private String description;

	private String name;

	//bi-directional many-to-one association to OrderTask
	@OneToMany(mappedBy="task", fetch=FetchType.EAGER)
	private Set<OrderTask> orderTasks;

	//bi-directional many-to-one association to OrderTemplateTask
	@OneToMany(mappedBy="task", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<OrderTypeTask> orderTemplateTasks;

	//bi-directional many-to-one association to ServiceTask
	@OneToMany(mappedBy="task", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<ServiceTask> serviceTasks;

	//bi-directional many-to-one association to ServiceTypeTask
	@OneToMany(mappedBy="task", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<ServiceTypeTask> serviceTypeTasks;

	//bi-directional many-to-one association to TaskType
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idTaskType")
	@JsonBackReference
	private TaskType taskType;

	public Task() {
	}

	public int getIdTask() {
		return this.idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
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

	public Set<OrderTask> getOrderTasks() {
		return this.orderTasks;
	}

	public void setOrderTasks(Set<OrderTask> orderTasks) {
		this.orderTasks = orderTasks;
	}

	public OrderTask addOrderTask(OrderTask orderTask) {
		getOrderTasks().add(orderTask);
		orderTask.setTask(this);

		return orderTask;
	}

	public OrderTask removeOrderTask(OrderTask orderTask) {
		getOrderTasks().remove(orderTask);
		orderTask.setTask(null);

		return orderTask;
	}

	public Set<OrderTypeTask> getOrderTemplateTasks() {
		return this.orderTemplateTasks;
	}

	public void setOrderTemplateTasks(Set<OrderTypeTask> orderTemplateTasks) {
		this.orderTemplateTasks = orderTemplateTasks;
	}

	public OrderTypeTask addOrderTemplateTask(OrderTypeTask orderTemplateTask) {
		getOrderTemplateTasks().add(orderTemplateTask);
		orderTemplateTask.setTask(this);

		return orderTemplateTask;
	}

	public OrderTypeTask removeOrderTemplateTask(OrderTypeTask orderTemplateTask) {
		getOrderTemplateTasks().remove(orderTemplateTask);
		orderTemplateTask.setTask(null);

		return orderTemplateTask;
	}

	public Set<ServiceTask> getServiceTasks() {
		return this.serviceTasks;
	}

	public void setServiceTasks(Set<ServiceTask> serviceTasks) {
		this.serviceTasks = serviceTasks;
	}

	public ServiceTask addServiceTask(ServiceTask serviceTask) {
		getServiceTasks().add(serviceTask);
		serviceTask.setTask(this);

		return serviceTask;
	}

	public ServiceTask removeServiceTask(ServiceTask serviceTask) {
		getServiceTasks().remove(serviceTask);
		serviceTask.setTask(null);

		return serviceTask;
	}

	public Set<ServiceTypeTask> getServiceTypeTasks() {
		return this.serviceTypeTasks;
	}

	public void setServiceTypeTasks(Set<ServiceTypeTask> serviceTypeTasks) {
		this.serviceTypeTasks = serviceTypeTasks;
	}

	public ServiceTypeTask addServiceTypeTask(ServiceTypeTask serviceTypeTask) {
		getServiceTypeTasks().add(serviceTypeTask);
		serviceTypeTask.setTask(this);

		return serviceTypeTask;
	}

	public ServiceTypeTask removeServiceTypeTask(ServiceTypeTask serviceTypeTask) {
		getServiceTypeTasks().remove(serviceTypeTask);
		serviceTypeTask.setTask(null);

		return serviceTypeTask;
	}

	public TaskType getTaskType() {
		return this.taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}
	@Override
	public Integer getId() {
		return this.idTask;
	}

	@Override
	public Task setId(Integer id) {
		this.idTask = id;
		return this;
	}
}
