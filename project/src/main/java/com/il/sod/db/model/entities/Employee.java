package com.il.sod.db.model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the Employee database table.
 *
 */
@Entity
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idEmployee;

	private String lastname;

	private String name;

	@Lob
	private String password;

	private int status;

	private String username;

	//bi-directional many-to-one association to EmployeeType
	@ManyToOne
	@JoinColumn(name="idEmployeeType")
	private EmployeeType employeeType;

	//bi-directional many-to-one association to EmployeeTaskOrder
	@OneToMany(mappedBy="employee", fetch=FetchType.EAGER)
	private List<EmployeeTaskOrder> employeeTaskOrders;

	//bi-directional many-to-one association to EmployeeTaskService
	@OneToMany(mappedBy="employee", fetch=FetchType.EAGER)
	private List<EmployeeTaskService> employeeTaskServices;

	public Employee() {
	}

	public int getIdEmployee() {
		return this.idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public EmployeeType getEmployeeType() {
		return this.employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public List<EmployeeTaskOrder> getEmployeeTaskOrders() {
		return this.employeeTaskOrders;
	}

	public void setEmployeeTaskOrders(List<EmployeeTaskOrder> employeeTaskOrders) {
		this.employeeTaskOrders = employeeTaskOrders;
	}

	public EmployeeTaskOrder addEmployeeTaskOrder(EmployeeTaskOrder employeeTaskOrder) {
		getEmployeeTaskOrders().add(employeeTaskOrder);
		employeeTaskOrder.setEmployee(this);

		return employeeTaskOrder;
	}

	public EmployeeTaskOrder removeEmployeeTaskOrder(EmployeeTaskOrder employeeTaskOrder) {
		getEmployeeTaskOrders().remove(employeeTaskOrder);
		employeeTaskOrder.setEmployee(null);

		return employeeTaskOrder;
	}

	public List<EmployeeTaskService> getEmployeeTaskServices() {
		return this.employeeTaskServices;
	}

	public void setEmployeeTaskServices(List<EmployeeTaskService> employeeTaskServices) {
		this.employeeTaskServices = employeeTaskServices;
	}

	public EmployeeTaskService addEmployeeTaskService(EmployeeTaskService employeeTaskService) {
		getEmployeeTaskServices().add(employeeTaskService);
		employeeTaskService.setEmployee(this);

		return employeeTaskService;
	}

	public EmployeeTaskService removeEmployeeTaskService(EmployeeTaskService employeeTaskService) {
		getEmployeeTaskServices().remove(employeeTaskService);
		employeeTaskService.setEmployee(null);

		return employeeTaskService;
	}
	@Override
	public Integer getId() {
		return this.idEmployee;
	}

	@Override
	public Employee setId(Integer id) {
		this.idEmployee = id;
		return this;
	}
}
