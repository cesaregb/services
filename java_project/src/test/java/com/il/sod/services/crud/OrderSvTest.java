package com.il.sod.services.crud;

import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.services.cruds.OrdersSv;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cesaregb on 6/18/17.
 */
public class OrderSvTest extends SpringTestConfiguration {

	@Autowired
	OrdersSv ordersSv;

	@Test
	public void testPayOrder() throws SODAPIException {
		OrderDTO dto = new OrderDTO();
		dto.setIdOrder(2);
		dto.setPaymentStatus(true);
		OrderDTO response = ordersSv.updateOrder(dto);
		Assert.assertThat("Some value!!!! ", response.getPaymentStatus(), Matchers.is(true));
	}


}
