package com.il.sod.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.entities.OrderTask;
import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.entities.OrderTypeTask;
import com.il.sod.rest.dto.db.OrderDTO;
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
		converterFactory.registerConverter("orderTypeTaskListConverter", new OrderTypeTaskListConverter());
		
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
			.fieldMap("orderSpecs", "orderSpecs").converter("orderSpecListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("orderTasks", "orderTasks").converter("orderTaskListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.field("orderType", "orderType.idOrderType")
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

class OrderTaskListConverter extends BidirectionalConverter<List<OrderTask>, List<Integer>> {
	@Override
	public List<OrderTask> convertFrom(List<Integer> source, Type<List<OrderTask>> arg1) {
		return source.stream().map(p -> (new OrderTask()).setId(p)).collect(Collectors.toList());
	}
	
	@Override
	public List<Integer> convertTo(List<OrderTask> source, Type<List<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toList());
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


