package com.il.sod.rest.dto.web;

import java.util.Date;
import java.util.Set;

import com.il.sod.rest.dto.db.ClientDTO;
import com.il.sod.rest.dto.db.OrderPickNDeliverDTO;
import com.il.sod.rest.dto.db.OrderTaskDTO;
import com.il.sod.rest.dto.db.ServiceDTO;

public class OrderDetailsDTO {
	private int idOrder;
	private double price;
	private int status;
	private ClientDTO client;
	private Set<OrderTaskDTO> orderTasks;
	private Set<OrderPickNDeliverDTO> orderPickNdelivers;
	private Date created;
	private int time;
	private Date pickUpDate;
	private Date deliverDate;
	private Set<ServiceDTO> services;
	
}
