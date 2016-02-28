package com.il.sod.db.model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


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

	//bi-directional many-to-one association to AssetTaskService
	@OneToMany(mappedBy="serviceTask", fetch=FetchType.EAGER)
	private List<AssetTaskService> assetTaskServices;

	//bi-directional many-to-one association to EmployeeTaskService
	@OneToMany(mappedBy="serviceTask", fetch=FetchType.EAGER)
	private List<EmployeeTaskService> employeeTaskServices;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="idService")
	private Service service;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="idTask")
	private Task task;

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

	public List<AssetTaskService> getAssetTaskServices() {
		return this.assetTaskServices;
	}

	public void setAssetTaskServices(List<AssetTaskService> assetTaskServices) {
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

	public List<EmployeeTaskService> getEmployeeTaskServices() {
		return this.employeeTaskServices;
	}

	public void setEmployeeTaskServices(List<EmployeeTaskService> employeeTaskServices) {
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
}
