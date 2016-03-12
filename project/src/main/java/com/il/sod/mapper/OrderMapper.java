package com.il.sod.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.AssetTaskOrder;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.EmployeeTaskOrder;
import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.entities.OrderTask;
import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.entities.OrderTypeTask;
import com.il.sod.rest.dto.db.AssetTaskOrderDTO;
import com.il.sod.rest.dto.db.ClientDTO;
import com.il.sod.rest.dto.db.EmployeeTaskOrderDTO;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.db.OrderTaskDTO;
import com.il.sod.rest.dto.db.OrderTypeDTO;
import com.il.sod.rest.dto.db.OrderTypeTaskDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;

public enum OrderMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private OrderMapper() {
		
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("orderListConverter", new OrderListConverter());
		converterFactory.registerConverter("orderTaskListConverter", new OrderTaskListConverter());
		converterFactory.registerConverter("orderTypeTaskListConverter", new OrderTypeTaskListConverter());
		converterFactory.registerConverter("clientConverter", new ClientConverter());
		converterFactory.registerConverter("assetTaskOrderListConverter", new AssetTaskOrderListConverter());
		converterFactory.registerConverter("employeeTaskOrderListConverter", new EmployeeTaskOrderListConverter());
		
		BaseMapper.MAPPER_FACTORY.classMap(OrderTypeDTO.class, OrderType.class)
			.fieldMap("orders", "orders").converter("orderListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("orderTypeTasks", "orderTypeTasks").converter("orderTypeTaskListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(OrderTypeTaskDTO.class, OrderTypeTask.class)
			.field("orderType", "orderType.idOrderType")
			.field("task", "task.idSpecs")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(OrderDTO.class, Order.class)
			.fieldMap("client", "client").converter("clientConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("orderTasks", "orderTasks").converter("orderTaskListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.field("orderType", "orderType.idOrderType")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(OrderTaskDTO.class, OrderTask.class)
			.fieldMap("assetTaskOrders", "assetTaskOrders").converter("assetTaskOrderListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("employeeTaskOrders", "employeeTaskOrders").converter("employeeTaskOrderListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.field("order", "order.idOrder")
			.field("task", "task.idSpecs")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(AssetTaskOrderDTO.class, AssetTaskOrder.class)
			.field("asset", "asset.idAsset")
			.field("orderTask", "orderTask.idOrderTask")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(EmployeeTaskOrderDTO.class, EmployeeTaskOrder.class)
			.field("orderTask", "orderTask.idOrderTask")
			.field("employee", "employee.idEmployee")
			.byDefault()
			.register();
		
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}



	public Order map(OrderDTO dto) {
		return this.mapperFacade.map(dto, Order.class);
	}

	public OrderDTO map(Order entity) {
		return this.mapperFacade.map(entity, OrderDTO.class);
	}
	
	public OrderType map(OrderTypeDTO dto) {
		return this.mapperFacade.map(dto, OrderType.class);
	}
	
	public OrderTypeDTO map(OrderType entity) {
		return this.mapperFacade.map(entity, OrderTypeDTO.class);
	}
	
	public OrderTypeTask map(OrderTypeTaskDTO dto) {
		return this.mapperFacade.map(dto, OrderTypeTask.class);
	}
	
	public OrderTypeTaskDTO map(OrderTypeTask entity) {
		return this.mapperFacade.map(entity, OrderTypeTaskDTO.class);
	}
	
	public OrderTask map(OrderTaskDTO dto) {
		return this.mapperFacade.map(dto, OrderTask.class);
	}
	
	public OrderTaskDTO map(OrderTask entity) {
		return this.mapperFacade.map(entity, OrderTaskDTO.class);
	}
	
	public AssetTaskOrderDTO map(AssetTaskOrder entity) {
		return this.mapperFacade.map(entity, AssetTaskOrderDTO.class);
	}
	
	public AssetTaskOrder map(AssetTaskOrderDTO entity) {
		return this.mapperFacade.map(entity, AssetTaskOrder.class);
	}
	
	public EmployeeTaskOrderDTO map(EmployeeTaskOrder entity) {
		return this.mapperFacade.map(entity, EmployeeTaskOrderDTO.class);
	}
	
	public EmployeeTaskOrder map(EmployeeTaskOrderDTO entity) {
		return this.mapperFacade.map(entity, EmployeeTaskOrder.class);
	}
}

class OrderListConverter extends BidirectionalConverter<List<Order>, List<Integer>> {
	@Override
	public List<Order> convertFrom(List<Integer> source, Type<List<Order>> destT) {
		return source.stream().map(p -> (new Order()).setId(p)).collect(Collectors.toList());
	}

	@Override
	public List<Integer> convertTo(List<Order> source, Type<List<Integer>> destT) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toList());
	}
}

class OrderTaskListConverter extends BidirectionalConverter<List<OrderTask>, List<OrderTaskDTO>> {
	@Override
	public List<OrderTask> convertFrom(List<OrderTaskDTO> source, Type<List<OrderTask>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
	@Override
	public List<OrderTaskDTO> convertTo(List<OrderTask> source, Type<List<OrderTaskDTO>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
}

class OrderTypeTaskListConverter extends BidirectionalConverter<List<OrderTypeTask>, List<OrderTypeTaskDTO>> {
	@Override
	public List<OrderTypeTask> convertFrom(List<OrderTypeTaskDTO> source, Type<List<OrderTypeTask>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}

	@Override
	public List<OrderTypeTaskDTO> convertTo(List<OrderTypeTask> source, Type<List<OrderTypeTaskDTO>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
}

class ClientConverter extends BidirectionalConverter<Client, ClientDTO> {
	@Override
	public Client convertFrom(ClientDTO source, Type<Client> arg1) {
		return ClientMapper.INSTANCE.map(source);
	}
	@Override
	public ClientDTO convertTo(Client source, Type<ClientDTO> arg1) {
		return ClientMapper.INSTANCE.map(source);
	}
}

class AssetTaskOrderListConverter extends BidirectionalConverter<List<AssetTaskOrder>, List<AssetTaskOrderDTO>> {
	@Override
	public List<AssetTaskOrder> convertFrom(List<AssetTaskOrderDTO> source, Type<List<AssetTaskOrder>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
	@Override
	public List<AssetTaskOrderDTO> convertTo(List<AssetTaskOrder> source, Type<List<AssetTaskOrderDTO>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
}

class EmployeeTaskOrderListConverter extends BidirectionalConverter<List<EmployeeTaskOrder>, List<EmployeeTaskOrderDTO>> {
	@Override
	public List<EmployeeTaskOrder> convertFrom(List<EmployeeTaskOrderDTO> source, Type<List<EmployeeTaskOrder>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
	@Override
	public List<EmployeeTaskOrderDTO> convertTo(List<EmployeeTaskOrder> source, Type<List<EmployeeTaskOrderDTO>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
}


