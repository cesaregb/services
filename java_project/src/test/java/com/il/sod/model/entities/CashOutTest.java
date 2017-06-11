package com.il.sod.model.entities;

import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.converter.services.OrderConverterService;
import com.il.sod.db.dao.impl.CashOutDAO;
import com.il.sod.db.dao.impl.OrdersDAO;
import com.il.sod.rest.dto.db.OrderDTO;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by cesaregb on 6/5/17.
 */
public class CashOutTest extends SpringTestConfiguration {
	private final static Logger LOGGER = getLogger(CashOutTest.class);

	@Autowired
	OrdersDAO ordersDAO;

	@Autowired
	OrderConverterService orderConverterService;

	@Autowired
	CashOutDAO cashOutDAO;

	@Test
	public void testListOfPubs() {
		List<OrderDTO> result = ordersDAO.findOrderNotCashedOut()
				.stream()
				.map(orderConverterService::convert)
				.collect(Collectors.toList());

		int size = result.size();
		LOGGER.info("Number of existing orders: {}", size );

		cashOutDAO.createCashOut();

		result = ordersDAO.findOrderNotCashedOut()
				.stream()
				.map(orderConverterService::convert)
				.collect(Collectors.toList());


		Assert.assertThat("Some value!!!! ", result.size(), Matchers.is(0));

	}

	@Test
	public void testCreateCashOut() {
	}
}
