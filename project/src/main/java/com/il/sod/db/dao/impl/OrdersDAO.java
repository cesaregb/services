package com.il.sod.db.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.repositories.OrderRepository;

@Service
public class OrdersDAO {

	@Autowired
	OrderRepository orderRepository;

	public List<Order> findByStatus(int status) {
		return orderRepository.findByStatus(status);
	}

}
