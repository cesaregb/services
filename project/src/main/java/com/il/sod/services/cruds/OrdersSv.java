package com.il.sod.services.cruds;

import com.il.sod.converter.services.OrderConverterService;
import com.il.sod.db.dao.impl.OrdersDAO;
import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.repositories.OrderRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.mapper.ServiceMapper;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.db.OrderTaskDTO;
import com.il.sod.rest.dto.db.ServiceTaskDTO;
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

	public OrderTasksInfoDTO getOrderTaskInfo(int orderId) throws SODAPIException {
		Order order = this.getEntity(orderRepository, orderId);
		OrderTasksInfoDTO result = new OrderTasksInfoDTO();
		result.setIdOrder(orderId);
		result.setIdClient(order.getClient().getId());
		result.setClientName(order.getClient().getFullName());
		result.setOrderTypeName(order.getOrderType().getName());

		Set<OrderTaskDTO> orderTasks = order.getOrderTasks().stream()
				.map(OrderMapper.INSTANCE::map)
				.collect(Collectors.toSet());
		result.setOrderTasks(orderTasks);

		Set<ServiceTasksInfoDTO> services = order.getServices()
				.stream()
				.map(this::getServiceTasksInfoDTO)
				.collect(Collectors.toSet());

		result.setServices(services);

		return result;
	}

	private ServiceTasksInfoDTO getServiceTasksInfoDTO(com.il.sod.db.model.entities.Service service) {
		ServiceTasksInfoDTO serviceTI = new ServiceTasksInfoDTO();
		serviceTI.setName(service.getName());
		serviceTI.setIdService(service.getIdService());
		Set<ServiceTaskDTO> serviceTaskDTOS = service.getServiceTasks().stream().map(ServiceMapper.INSTANCE::map).collect(Collectors.toSet());
		serviceTI.setServiceTasks(serviceTaskDTOS);
		return serviceTI;
	}

	public UIOrderDTO getOrder4Edit(String orderId) throws SODAPIException {
		return orderConverterService.convert2UI(this.getEntity(orderRepository, Integer.valueOf(orderId)));
	}

}
