package com.il.sod.services.cruds;

import com.il.sod.db.dao.impl.CashOutDAO;
import com.il.sod.db.dao.impl.OrdersDAO;
import com.il.sod.db.model.entities.CashOut;
import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.repositories.CashOutRepository;
import com.il.sod.mapper.CashOutMapper;
import com.il.sod.rest.dto.db.CashOutDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cesaregb on 1/19/17.
 */
@SuppressWarnings("Duplicates")
@Service
public class CashOutSv extends EntityServicesBase {
  private final static Logger LOGGER = LoggerFactory.getLogger(CashOutSv.class);

  @Autowired
  CashOutDAO cashOutDAO;

  @Autowired
  OrdersDAO ordersDAO;

  @Autowired
  CashOutRepository cashOutRepository;

  public CashOutDTO createCashOut() {
    CashOut co = cashOutDAO.createCashOut(filterOrdersByPaymentStatus(ordersDAO.findOrderNotCashedOut()));
    return CashOutMapper.INSTANCE.map(co);
  }

  public List<CashOutDTO> getCashOuts() {
    List<CashOutDTO> result = cashOutRepository.findAll().stream()
            .map(CashOutMapper.INSTANCE::map)
            .collect(Collectors.toList());
    addOrders(result);
    return result;
  }

  public List<CashOutDTO> getCashOutByDate(Date date) {
    List<CashOut> list = cashOutDAO.getCashOutByDate(date);
    List<CashOutDTO> result = list.stream().map(CashOutMapper.INSTANCE::map).collect(Collectors.toList());
    addOrders(result);
    return result;
  }

  public List<CashOutDTO> getCashOutBetweenDates(Date initDate, Date endDate) {
    List<CashOut> list = cashOutDAO.getCashOutBetweenDates(initDate, endDate);
    List<CashOutDTO> result = list.stream()
            .map(CashOutMapper.INSTANCE::map)
            .collect(Collectors.toList());
    addOrders(result);
    return result;
  }

  public CashOutDTO nextCashOut() {
    CashOut co = cashOutDAO.peekCashOut(filterOrdersByPaymentStatus(ordersDAO.findOrderNotCashedOut()));
    return CashOutMapper.INSTANCE.map(co);
  }

  List<Order> filterOrdersByPaymentStatus(List<Order> lOrder) {
    return lOrder.stream()
            .filter(Order::isPaymentStatus)
            .collect(Collectors.toList());
  }

  /**
   * Alter existing cashout list
   *
   * @param result
   */
  private void addOrders(List<CashOutDTO> result) {
    for (CashOutDTO itm : result) {
      List<Integer> orders = ordersDAO.findByCashOut(itm.getIdCashOut())
              .stream()
              .map(Order::getId)
              .collect(Collectors.toList());
      itm.setOrders(orders);
    }
  }

}
