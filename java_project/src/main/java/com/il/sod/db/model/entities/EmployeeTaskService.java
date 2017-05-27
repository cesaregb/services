package com.il.sod.db.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the EmployeeTaskService database table.
 *
 */
@Entity
@NamedQuery(name="EmployeeTaskService.findAll", query="SELECT e FROM EmployeeTaskService e")
public class EmployeeTaskService implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idEmployeeTaskService;

	private String comments;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="idEmployee")
	private Employee employee;

	//bi-directional many-to-one association to ServiceTask
	@ManyToOne
	@JoinColumn(name="idServiceTask")
	private ServiceTask serviceTask;

	public EmployeeTaskService() {
	}

	public int getIdEmployeeTaskService() {
		return this.idEmployeeTaskService;
	}

	public void setIdEmployeeTaskService(int idEmployeeTaskService) {
		this.idEmployeeTaskService = idEmployeeTaskService;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ServiceTask getServiceTask() {
		return this.serviceTask;
	}

	public void setServiceTask(ServiceTask serviceTask) {
		this.serviceTask = serviceTask;
	}
	@Override
	public Integer getId() {
		return this.idEmployeeTaskService;
	}

	@Override
	public EmployeeTaskService setId(Integer id) {
		this.idEmployeeTaskService = id;
		return this;
	}
}
