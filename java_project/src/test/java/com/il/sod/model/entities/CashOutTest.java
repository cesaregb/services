package com.il.sod.model.entities;

import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.db.dao.impl.CashOutDAO;
import com.il.sod.db.dao.impl.OrdersDAO;
import com.il.sod.db.model.entities.CashOut;
import com.il.sod.db.model.entities.Order;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by cesaregb on 6/5/17.
 */
public class CashOutTest extends SpringTestConfiguration {
  private final static Logger LOGGER = getLogger(CashOutTest.class);

  @Autowired
  OrdersDAO ordersDAO;

  @Autowired
  CashOutDAO cashOutDAO;

  @Test
  public void testCreateCashOut() {
    List<Order> result = ordersDAO.findOrderNotCashedOut();
    int size = result.size();
    LOGGER.info("Number of existing orders: {}", size);
    cashOutDAO.createCashOut(ordersDAO.findOrderNotCashedOut());
    result = ordersDAO.findOrderNotCashedOut();
    LOGGER.info("Number of existing orders: {} After cashout", size);
    Assert.assertThat("Some value!!!! ", result.size(), Matchers.is(0));
  }

  @Test
  public void testGetCashOutByDate() {
    LocalDate date = LocalDate.now();
    System.out.println(toTimestamp(date));
    List<CashOut> list = cashOutDAO.getCashOutByDate(toTimestamp(date));
    LOGGER.info("Today's cashouts: {}", list.size());
  }

  private static Timestamp toTimestamp(LocalDate localDate) {
    Date date = Date.from(localDate.atStartOfDay()
            .atZone(ZoneId.systemDefault()).toInstant());
    return new Timestamp(date.getTime());
  }

}
