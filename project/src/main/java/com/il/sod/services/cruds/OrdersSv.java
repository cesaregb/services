package com.il.sod.services.cruds;

import com.il.sod.converter.services.OrderConverterService;
import com.il.sod.db.dao.impl.OrdersDAO;
import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.repositories.OrderRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.parse.UIOrderDTO;
import com.il.sod.rest.dto.specifics.OrderTasksInfoDTO;
import com.il.sod.rest.dto.specifics.ServiceTasksInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cesaregb on 1/19/17.
 */
@SuppressWarnings("Duplicates")
@Service
public class OrdersSv extends EntityServicesBase {
	
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrdersDAO ordersDAO;

	@Autowired
	OrderConverterService orderConverterService;

	public OrderDTO saveOrder(OrderDTO dto) throws SODAPIException {
		Order entity = OrderMapper.INSTANCE.map(dto);
		this.saveEntity(orderRepository, entity);
		return OrderMapper.INSTANCE.map(entity);
	}

	public OrderDTO updateOrder(OrderDTO dto) throws SODAPIException {
		Order entity = OrderMapper.INSTANCE.map(dto);
		this.updateEntity(orderRepository, entity);
		return OrderMapper.INSTANCE.map(entity);
	}

	public boolean deleteOrder(OrderDTO dto) throws SODAPIException {
		Order entity = OrderMapper.INSTANCE.map(dto);
		this.deleteEntity(orderRepository, entity.getIdOrder());
		return true;
	}

	public List<OrderDTO> getOrderList() throws SODAPIException {
		List<Order> rentityList = this.getEntityList(orderRepository);
		List<OrderDTO> list = rentityList.stream().map((i) -> {
			OrderDTO dto = orderConverterService.convert(i);
			return dto;
		}).collect(Collectors.toList());
		return list;
	}

	public List<OrderDTO> getOrdersByStatus(int status) throws SODAPIException {
		List<OrderDTO> dto = ordersDAO.findByStatus(status)
				.stream()
				.map(o -> orderConverterService.convert(o))
				.collect(Collectors.toList());
		return dto;
	}

	public OrderDTO getOrderById(String orderId) throws SODAPIException {
		return OrderMapper.INSTANCE.map(this.getEntity(orderRepository, Integer.valueOf(orderId)));
	}

	public OrderTasksInfoDTO getOrderTaskInfo(String orderId) throws SODAPIException {
		OrderDTO dto = OrderMapper.INSTANCE.map(this.getEntity(orderRepository, Integer.valueOf(orderId)));
		OrderTasksInfoDTO result = new OrderTasksInfoDTO();
		result.setOrderTasks(dto.getOrderTasks());
		Set<ServiceTasksInfoDTO> services = dto.getServices().stream().map(i -> {
			ServiceTasksInfoDTO r = new ServiceTasksInfoDTO();
			r.setIdService(i.getIdService());
			r.setServiceTasks(i.getServiceTasks());
			return r;
		}).collect(Collectors.toSet());
		result.setServices(services);
		return result;
	}

	public UIOrderDTO getOrder4Edit(String orderId) throws SODAPIException {
		return orderConverterService.convert2UI(this.getEntity(orderRepository, Integer.valueOf(orderId)));
	}

}
