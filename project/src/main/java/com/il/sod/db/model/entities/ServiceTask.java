package com.il.sod.db.model.entities;

import java.util.Date;
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
 * The persistent class for the ServiceTask database table.
 *
 */
@Entity
@NamedQuery(name="ServiceTask.findAll", query="SELECT s FROM ServiceTask s")
public class ServiceTask implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idServiceTask;

	private String comments;
	
	private int sortingOrder;

	//bi-directional many-to-one association to AssetTaskService
	@OneToMany(mappedBy="serviceTask", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<AssetTaskService> assetTaskServices;

	//bi-directional many-to-one association to EmployeeTaskService
	@OneToMany(mappedBy="serviceTask", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<EmployeeTaskService> employeeTaskServices;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="idService")
	@JsonBackReference
	private Service service;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="idTask")
	@JsonBackReference
	private Task task;
	
	private Date started;
	
	private Date ended;
	
	private int status;
	
	private int time;

	public ServiceTask() {
	}

	public int getIdServiceTask() {
		return this.idServiceTask;
	}

	public void setIdServiceTask(int idServiceTask) {
		this.idServiceTask = idServiceTask;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Set<AssetTaskService> getAssetTaskServices() {
		return this.assetTaskServices;
	}

	public void setAssetTaskServices(Set<AssetTaskService> assetTaskServices) {
		this.assetTaskServices = assetTaskServices;
	}

	public AssetTaskService addAssetTaskService(AssetTaskService assetTaskService) {
		getAssetTaskServices().add(assetTaskService);
		assetTaskService.setServiceTask(this);

		return assetTaskService;
	}

	public AssetTaskService removeAssetTaskService(AssetTaskService assetTaskService) {
		getAssetTaskServices().remove(assetTaskService);
		assetTaskService.setServiceTask(null);

		return assetTaskService;
	}

	public Set<EmployeeTaskService> getEmployeeTaskServices() {
		return this.employeeTaskServices;
	}

	public void setEmployeeTaskServices(Set<EmployeeTaskService> employeeTaskServices) {
		this.employeeTaskServices = employeeTaskServices;
	}

	public EmployeeTaskService addEmployeeTaskService(EmployeeTaskService employeeTaskService) {
		getEmployeeTaskServices().add(employeeTaskService);
		employeeTaskService.setServiceTask(this);

		return employeeTaskService;
	}

	public EmployeeTaskService removeEmployeeTaskService(EmployeeTaskService employeeTaskService) {
		getEmployeeTaskServices().remove(employeeTaskService);
		employeeTaskService.setServiceTask(null);

		return employeeTaskService;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	@Override
	public Integer getId() {
		return this.idServiceTask;
	}

	@Override
	public ServiceTask setId(Integer id) {
		this.idServiceTask = id;
		return this;
	}

	public Date getStarted() {
		return started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public Date getEnded() {
		return ended;
	}

	public void setEnded(Date ended) {
		this.ended = ended;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSortingOrder() {
		return sortingOrder;
	}

	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
