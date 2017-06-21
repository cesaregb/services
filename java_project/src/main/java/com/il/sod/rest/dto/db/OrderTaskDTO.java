package com.il.sod.rest.dto.db;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class OrderTaskDTO {	
	private int idOrderTask;
	private String comments;
	private String taskName;
	private int time;
	private Set<AssetTaskOrderDTO> assetTaskOrders;
	private Set<EmployeeTaskOrderDTO> employeeTaskOrders;
	private int idOrder;
	private int idTask;
	private TaskDTO task;
	private int status;
	private Date started;
	private Date ended;
	private int sortingOrder; 
}
