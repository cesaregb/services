package com.il.sod.db.model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the OrderTemplate database table.
 *
 */
@Entity
@NamedQuery(name="OrderTemplate.findAll", query="SELECT o FROM OrderTemplate o")
public class OrderTemplate implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrderTemplate;

	private String description;

	private String name;

	//bi-directional many-to-one association to OrderTemplateTask
	@OneToMany(mappedBy="orderTemplate", fetch=FetchType.EAGER)
	private List<OrderTemplateTask> orderTemplateTasks;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="orderTemplate", fetch=FetchType.EAGER)
	private List<Order> orders;

	public OrderTemplate() {
	}

	public int getIdOrderTemplate() {
		return this.idOrderTemplate;
	}

	public void setIdOrderTemplate(int idOrderTemplate) {
		this.idOrderTemplate = idOrderTemplate;
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

	public List<OrderTemplateTask> getOrderTemplateTasks() {
		return this.orderTemplateTasks;
	}

	public void setOrderTemplateTasks(List<OrderTemplateTask> orderTemplateTasks) {
		this.orderTemplateTasks = orderTemplateTasks;
	}

	public OrderTemplateTask addOrderTemplateTask(OrderTemplateTask orderTemplateTask) {
		getOrderTemplateTasks().add(orderTemplateTask);
		orderTemplateTask.setOrderTemplate(this);

		return orderTemplateTask;
	}

	public OrderTemplateTask removeOrderTemplateTask(OrderTemplateTask orderTemplateTask) {
		getOrderTemplateTasks().remove(orderTemplateTask);
		orderTemplateTask.setOrderTemplate(null);

		return orderTemplateTask;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setOrderTemplate(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setOrderTemplate(null);

		return order;
	}
	@Override
	public Integer getId() {
		return this.idOrderTemplate;
	}

	@Override
	public OrderTemplate setId(Integer id) {
		this.idOrderTemplate = id;
		return this;
	}
}
