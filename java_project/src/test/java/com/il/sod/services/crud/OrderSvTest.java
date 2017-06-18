package com.il.sod.services.crud;

import com.il.sod.db.model.repositories.OrderRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.services.cruds.OrdersSv;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cesaregb on 6/18/17.
 */
public class OrderSvTest {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrdersSv ordersSv;

	@Test
	public void testUpdatePublication(){
		OrderDTO dto = new OrderDTO();
		dto.idOrder(2).paymentStatus(1);

		try {
			OrderDTO response = ordersSv.updateOrder(dto);
		} catch (SODAPIException e) {
			e.printStackTrace();
		}
	}
}
