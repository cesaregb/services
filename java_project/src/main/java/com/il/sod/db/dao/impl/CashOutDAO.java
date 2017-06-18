package com.il.sod.db.dao.impl;

import com.il.sod.db.model.entities.CashOut;
import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.repositories.CashOutRepository;
import com.il.sod.db.model.repositories.OrderRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class CashOutDAO {
	private final static Logger LOGGER = getLogger(CashOutDAO.class);

	@Autowired
	CashOutRepository cashOutRepository;

	@Autowired
	OrderRepository orderRepository;

	private void updateOrdersWithCashOut(List<Order> orders, CashOut cashOut) {
		final int idCashOut = cashOut.getId();
		for (Order o : orders) {
			o.setIdCashOut(idCashOut);
			orderRepository.save(o);
		}
	}

	public List<Order> findOrderNotCashedOut() {
		return orderRepository.findByIdCashOut(0);
	}

	public CashOut createCashOut(List<Order> orders) {
		CashOut cashOut = peekCashOut(orders);
		cashOut = cashOutRepository.save(cashOut);
		updateOrdersWithCashOut(orders, cashOut);
		return cashOut;
	}

	public CashOut peekCashOut(List<Order> orders) {
		double subtotal = 0d;
		double pending = 0d;
		double discount = 0d;

		for (Order o : orders) {
			subtotal += o.getTotal();
			pending += (o.getPaymentStatus() == 0) ? o.getTotal() : 0;
			discount += o.getDiscount();
		}

		CashOut cashOut = new CashOut();
		cashOut.setUser(1); //TODO fixme
		cashOut.setSubtotal(subtotal);
		cashOut.setPending(pending);
		cashOut.setDiscount(discount);
		cashOut.setTotal(subtotal - discount);
		return cashOut;
	}

	public List<CashOut> getCashOutByDate(Timestamp date) {
		return cashOutRepository.findAllByCreateDate(date);
	}


}
