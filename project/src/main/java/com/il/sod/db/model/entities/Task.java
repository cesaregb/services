package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Task database table.
 * 
 */
@Entity
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTask;

	private String description;

	private String name;

	//bi-directional many-to-one association to OrderTask
	@OneToMany(mappedBy="task", fetch=FetchType.EAGER)
	private List<OrderTask> orderTasks;

	//bi-directional many-to-one association to OrderTemplateTask
	@OneToMany(mappedBy="task", fetch=FetchType.EAGER)
	private List<OrderTemplateTask> orderTemplateTasks;

	//bi-directional many-to-one association to ServiceTask
	@OneToMany(mappedBy="task", fetch=FetchType.EAGER)
	private List<ServiceTask> serviceTasks;

	//bi-directional many-to-one association to ServiceTypeTask
	@OneToMany(mappedBy="task", fetch=FetchType.EAGER)
	private List<ServiceTypeTask> serviceTypeTasks;

	//bi-directional many-to-one association to TaskType
	@ManyToOne
	@JoinColumn(name="idTaskType")
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

	public List<OrderTask> getOrderTasks() {
		return this.orderTasks;
	}

	public void setOrderTasks(List<OrderTask> orderTasks) {
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

	public List<OrderTemplateTask> getOrderTemplateTasks() {
		return this.orderTemplateTasks;
	}

	public void setOrderTemplateTasks(List<OrderTemplateTask> orderTemplateTasks) {
		this.orderTemplateTasks = orderTemplateTasks;
	}

	public OrderTemplateTask addOrderTemplateTask(OrderTemplateTask orderTemplateTask) {
		getOrderTemplateTasks().add(orderTemplateTask);
		orderTemplateTask.setTask(this);

		return orderTemplateTask;
	}

	public OrderTemplateTask removeOrderTemplateTask(OrderTemplateTask orderTemplateTask) {
		getOrderTemplateTasks().remove(orderTemplateTask);
		orderTemplateTask.setTask(null);

		return orderTemplateTask;
	}

	public List<ServiceTask> getServiceTasks() {
		return this.serviceTasks;
	}

	public void setServiceTasks(List<ServiceTask> serviceTasks) {
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

	public List<ServiceTypeTask> getServiceTypeTasks() {
		return this.serviceTypeTasks;
	}

	public void setServiceTypeTasks(List<ServiceTypeTask> serviceTypeTasks) {
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

}