package com.il.sod.rest.dto.db;

import com.google.common.base.MoreObjects;
import lombok.Data;

import java.util.Date;
import java.util.Set;

//@Accessors(fluent = true)
@Data
public class OrderDTO extends DeletableDTO {
	private Integer idOrder;
	private String comments;
	private Integer idAddressDeliver;
	private Integer idAddressPickup;
	private Date pickUpDate;
	private Date deliverDate;
	private Double totalServices;
	private Double total;
	private Integer status;
	private ClientDTO client;
	private Integer idOrderType;
	private Set<OrderTaskDTO> orderTasks;
	private PaymentInfoDTO paymentInfo;
	private Date created;
	private Date updated;
	private Integer time;
	private Set<ServiceDTO> services;
	private Integer createdBy;

	// flat information.
	private String clientName;
	private String orderTypeName;
	private Double completed;

	private Double pickUpPrice;
	private Double deliverPrice;
	private Double discount;
	private Integer paymentStatus;
	private Set<OrderPriceAdjustmentDTO> orderPriceAdjustments;
	private Integer idCashOut;



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
				.add("clientName", clientName)
				.add("orderTypeName", orderTypeName)
				.add("completed", completed)
				.add("pickUpPrice", pickUpPrice)
				.add("deliverPrice", deliverPrice)
				.toString();
	}
}
