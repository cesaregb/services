package com.il.sod.db.dao.impl;

import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.repositories.OrderRepository;
import io.jsonwebtoken.lang.Collections;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class OrdersDAO {
	private final static Logger LOGGER = getLogger(OrdersDAO.class);

	@Autowired
	OrderRepository orderRepository;

	public List<Order> findByStatus(int status) {
		return orderRepository.findByStatus(status);
	}

	public double getCompletedPercent(int idOrder){
		Order order = orderRepository.findOne(idOrder);
		double nTasks = 0d;
		if (!Collections.isEmpty(order.getOrderTasks())){
			nTasks += order.getOrderTasks().size();
		}
		if (!Collections.isEmpty(order.getServices())){
			nTasks += order.getServices()
					.stream()
					.mapToInt(s-> s.getServiceTasks().size())
					.sum();
		}
		double sumStatus = order.getOrderTasks()
				.stream()
				.filter( t-> t.getStatus() == 2)
				.count();

		sumStatus += order.getServices()
				.stream()
				.mapToLong(s-> s.getServiceTasks().stream().filter(t-> t.getStatus() == 2).count())
				.sum();


		double completed = ((sumStatus / nTasks) * 100);
		LOGGER.info("Order: {} completed {}% ", idOrder, completed);
		return completed;
	}

}
