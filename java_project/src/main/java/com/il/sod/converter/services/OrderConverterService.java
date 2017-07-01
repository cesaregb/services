package com.il.sod.converter.services;

import com.il.sod.db.dao.impl.OrdersDAO;
import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.repositories.ServiceRepository;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.db.ServiceDTO;
import com.il.sod.rest.dto.parse.UIOrderDTO;
import com.il.sod.rest.dto.parse.UIServiceDTO;
import com.il.sod.rest.dto.parse.UITransportDTO;
import com.il.sod.rest.dto.serve.WPaymentInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderConverterService {

  @Autowired
  private ServiceConverterService serviceConverterService;

  @Autowired
  private OrdersDAO ordersDAO;

  @Autowired
  private ServiceRepository serviceRepository;

  public OrderDTO convert(Order entity) {
    OrderDTO result = OrderMapper.INSTANCE.map(entity);
    // add flattering...
    result.setClientName(result.getClient().getName() + " " + result.getClient().getLastName());
    result.setOrderTypeName(entity.getOrderType().getName());

    // override services to use the custom converter
    List<com.il.sod.db.model.entities.Service> services = serviceRepository.findByOrder(result.getIdOrder());

    Set<ServiceDTO> setService = services
            .stream().map(s -> serviceConverterService.convert(s))
            .collect(Collectors.toSet());
    result.setServices(setService);
    result.setCompleted(ordersDAO.getCompletedPercent(entity));
    return result;
  }

  public UIOrderDTO convert2UI(Order entity) {
    if (entity != null) {
      UIOrderDTO result = new UIOrderDTO();
      result.setIdOrder(entity.getId());
      result.setIdClient(entity.getClient().getId());
      result.setComments(entity.getComments());

      // TODO Validate transport for NPE
      List<UITransportDTO> lTransport = new ArrayList<>();
      lTransport.add(new UITransportDTO(entity.getIdAddressPickup(), entity.getPickUpDate(), 0, 0));
      lTransport.add(new UITransportDTO(entity.getIdAddressDeliver(), entity.getDeliverDate(), 1, 0));
      result.setTransport(lTransport);

      result.setTotal(entity.getTotal());
      result.setTotalServices(entity.getTotalServices());
      WPaymentInfoDTO paymentInfo = new WPaymentInfoDTO();
      if (entity.getPaymentInfo() != null) {
        paymentInfo.setType(entity.getPaymentInfo().getType());
      }
      if (entity.getPaymentInfo() != null) {
        paymentInfo.setTransactionInfo(entity.getPaymentInfo().getTransactionInfo());
      }

      result.setPaymentInfo(paymentInfo);
      List<UIServiceDTO> services = entity.getServices().stream().map(s -> {
        return serviceConverterService.convert2UI(s);
      }).collect(Collectors.toList());
      result.setServices(services);
      return result;
    } else {
      return null;
    }

  }

//	public OrderTypeDTO convert(OrderType input){
//		OrderTypeDTO restult = OrderMapper.INSTANCE.map(input);
//		return null;
//	}

}

