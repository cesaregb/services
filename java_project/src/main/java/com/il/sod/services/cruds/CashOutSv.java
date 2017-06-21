package com.il.sod.services.cruds;

import com.il.sod.db.dao.impl.CashOutDAO;
import com.il.sod.db.dao.impl.OrdersDAO;
import com.il.sod.db.model.entities.CashOut;
import com.il.sod.db.model.entities.Order;
import com.il.sod.mapper.CashOutMapper;
import com.il.sod.rest.dto.db.CashOutDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cesaregb on 1/19/17.
 */
@SuppressWarnings("Duplicates")
@Service
public class CashOutSv extends EntityServicesBase{
	private final static Logger LOGGER = LoggerFactory.getLogger(CashOutSv.class);

	@Autowired
	CashOutDAO cashOutDAO;

	@Autowired
	OrdersDAO ordersDAO;

	public CashOutDTO createCashOut() {
		CashOut co = cashOutDAO.createCashOut(filterOrders(ordersDAO.findOrderNotCashedOut()));
		return CashOutMapper.INSTANCE.map(co);
	}

	public List<CashOutDTO> getCashOutByDate(Timestamp date) {
		List<CashOut> list = cashOutDAO.getCashOutByDate(date);
		List<CashOutDTO> result = list.stream().map(CashOutMapper.INSTANCE::map).collect(Collectors.toList());
		addOrders(result);
		return result;
	}

	public CashOutDTO nextCashOut() {
		CashOut co = cashOutDAO.peekCashOut(filterOrders(ordersDAO.findOrderNotCashedOut()));
		return CashOutMapper.INSTANCE.map(co);
	}

	public List<Order> filterOrders(List<Order> lOrder ){
		return lOrder.stream()
				.filter(Order::isPaymentStatus)
				.collect(Collectors.toList());
	}

	/**
	 * Alter existing cashout list
	 * @param result
	 */
	private void addOrders(List<CashOutDTO> result) {
		for(CashOutDTO itm : result){
			List<Integer> orders = ordersDAO.findByCashOut(itm.getIdCashOut())
					.stream()
					.map(Order::getId)
					.collect(Collectors.toList());
			itm.setOrders(orders);
		}
	}

}
