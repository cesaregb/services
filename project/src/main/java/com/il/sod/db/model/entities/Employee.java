package com.il.sod.db.model.entities;

import java.util.Date;
import java.util.Set;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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
	
	private String email;

	//bi-directional many-to-one association to EmployeeType
	@ManyToOne
	@JoinColumn(name="idEmployeeType")
	private EmployeeType employeeType;

	//bi-directional many-to-one association to EmployeeTaskOrder
	@OneToMany(mappedBy="employee", fetch=FetchType.EAGER)
	private Set<EmployeeTaskOrder> employeeTaskOrders;

	//bi-directional many-to-one association to EmployeeTaskService
	@OneToMany(mappedBy="employee", fetch=FetchType.EAGER)
	private Set<EmployeeTaskService> employeeTaskServices;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

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

	public Set<EmployeeTaskOrder> getEmployeeTaskOrders() {
		return this.employeeTaskOrders;
	}

	public void setEmployeeTaskOrders(Set<EmployeeTaskOrder> employeeTaskOrders) {
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

	public Set<EmployeeTaskService> getEmployeeTaskServices() {
		return this.employeeTaskServices;
	}

	public void setEmployeeTaskServices(Set<EmployeeTaskService> employeeTaskServices) {
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
