package com.il.sod.db.dao.impl;

import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.repositories.OrderRepository;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersDAO {
	@Autowired
	OrderRepository orderRepository;

	public List<Order> findByStatus(int status) {
		return orderRepository.findByStatus(status);
	}

	public double getCompletedPercent(Order order){
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
				.mapToLong(s-> s.getServiceTasks()
						.stream()
						.filter(t-> t.getStatus() == 2).count())
				.sum();

		if (nTasks == 0d) {
			return nTasks;
		}
		return  ((sumStatus / nTasks) * 100);
	}

	public List<Order> findByCashOut(int idCashOut){
		return orderRepository.findByIdCashOut(idCashOut);
	}


	public List<Order> findOrderNotCashedOut(){
		return orderRepository.findByIdCashOut(0);
	}

}
