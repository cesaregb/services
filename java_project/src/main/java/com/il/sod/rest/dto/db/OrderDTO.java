package com.il.sod.rest.dto.db;

import com.google.common.base.MoreObjects;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Data
@Accessors(fluent = true)
public class OrderDTO extends DeletableDTO {
	private int idOrder;
	private String comments;
	private int idAddressDeliver;
	private int idAddressPickup;
	private Date pickUpDate;
	private Date deliverDate;
	private double totalServices;
	private double total;
	private int status;
	private ClientDTO client;
	private int idOrderType;
	private Set<OrderTaskDTO> orderTasks;
	private PaymentInfoDTO paymentInfo;
	private Date created;
	private Date updated;
	private int time;
	private Set<ServiceDTO> services;
	private int createdBy;
	private int deleted;
	
	// flat information.
	private String clientName;
	private String orderTypeName;
	private double completed;

	private double pickUpPrice;
	private double deliverPrice;
	private double discount;
	private int paymentStatus;
	private Set<OrderPriceAdjustmentDTO> orderPriceAdjustments;
	private int idCashOut;



	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("idOrder", idOrder)
				.add("comments", comments)
				.add("idAddressDeliver", idAddressDeliver)
				.add("idAddressPickup", idAddressPickup)
				.add("pickUpDate", pickUpDate)
				.add("deliverDate", deliverDate)
				.add("totalServices", totalServices)
				.add("total", total)
				.add("status", status)
				.add("client", client)
				.add("idOrderType", idOrderType)
				.add("orderTasks", orderTasks)
				.add("created", created)
				.add("updated", updated)
				.add("time", time)
				.add("createdBy", createdBy)
				.add("deleted", deleted)
				.add("clientName", clientName)
				.add("orderTypeName", orderTypeName)
				.add("completed", completed)
				.add("pickUpPrice", pickUpPrice)
				.add("deliverPrice", deliverPrice)
				.toString();
	}
}
