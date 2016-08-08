package com.il.sod.converter.services;

import org.springframework.stereotype.Service;

import com.il.sod.db.model.entities.Order;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.rest.dto.db.OrderDTO;

@Service
public class OrderConverterService {
	
	public OrderConverterService() {}
	
	public OrderDTO convert(Order entity){
		OrderDTO result = OrderMapper.INSTANCE.map(entity);
		System.out.println("****************");
		System.out.println(entity.getOrderTasks().size() + "======" + result.getOrderTasks().size());
		System.out.println("****************");
		
		// add flattering...
		result.setClientName(result.getClient().getName() + " " + result.getClient().getLastName());
		result.setOrderTypeName(entity.getOrderType().getName());
		int sumStatus = result.getOrderTasks().stream().mapToInt(ot -> {return ot.getStatus();}).sum();
		double completed = ((sumStatus * 100)/ result.getOrderTasks().size());
		result.setCompleted(completed);
		return result;
	}
	
}

