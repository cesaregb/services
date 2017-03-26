package com.il.sod.mapper;

import com.il.sod.config.Constants;
import com.il.sod.db.model.entities.*;
import com.il.sod.rest.dto.db.*;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.ConverterFactory;

public enum OrderMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private OrderMapper() {

		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("orderSetConverter", new OrderSetConverter());
		converterFactory.registerConverter("orderTaskSetConverter", new OrderTaskSetConverter());
		converterFactory.registerConverter("orderTypeTaskSetConverter", new OrderTypeTasketConverter());
		converterFactory.registerConverter("clientConverter", new ClientConverter());
//		converterFactory.registerConverter("assetTaskOrderSetConverter", new AssetTaskOrderSetConverter());
//		converterFactory.registerConverter("employeeTaskOrderSetConverter", new EmployeeTaskOrderSetConverter());
		converterFactory.registerConverter("serviceTypeSetConverter", new ServiceTypeSetConverter());
		converterFactory.registerConverter("serviceSet2IntConverter", new ServiceSet2IntConverter());
		converterFactory.registerConverter("serviceSetConverter", new ServiceSetConverter());

		BaseMapper.MAPPER_FACTORY.classMap(OrderTypeDTO.class, OrderType.class)
				.fieldMap("orderTypeTask", "orderTypeTask").converter("orderTypeTaskSetConverter").mapNulls(true).mapNullsInReverse(true).add()
				.byDefault()
				.register();

		BaseMapper.MAPPER_FACTORY.classMap(OrderTypeTaskDTO.class, OrderTypeTask.class)
				.field("idOrderType", "orderType.idOrderType")
				.field("taskTypeName", "task.taskType.name")
				.byDefault().register();

		BaseMapper.MAPPER_FACTORY.classMap(OrderDTO.class, Order.class)
				.exclude("services")
				.fieldMap("client", "client").converter("clientConverter").mapNulls(true).mapNullsInReverse(true).add()
				.fieldMap("orderTasks", "orderTasks").converter("orderTaskSetConverter").mapNulls(true).mapNullsInReverse(true).add()
//				.fieldMap("services", "services").converter("serviceSetConverter").mapNulls(true).mapNullsInReverse(true).add()
				.field("idOrderType", "orderType.idOrderType").mapNulls(false)
				.byDefault().register();

		// TODO enable asset and employees once is used.
//		.fieldMap("assetTaskOrders", "assetTaskOrders").converter("assetTaskOrderSetConverter").mapNulls(true).mapNullsInReverse(true).add()
//	    .fieldMap("employeeTaskOrders", "employeeTaskOrders").converter("employeeTaskOrderSetConverter").mapNulls(true).mapNullsInReverse(true).add()
		BaseMapper.MAPPER_FACTORY.classMap(OrderTaskDTO.class, OrderTask.class)
				.exclude("assetTaskOrders")
				.exclude("employeeTaskOrders")
				.field("idOrder", "order.idOrder")
				.field("idTask", "task.idTask")
				.field("taskName", "task.name")
				.byDefault()
				.customize(new CustomMapper<OrderTaskDTO, OrderTask>() {
					@Override
					public void mapBtoA(OrderTask entity, OrderTaskDTO dto, MappingContext context) {
						dto.setTask(TaskMapper.INSTANCE.map(entity.getTask()));
						dto.getTask().setTypeTask(Constants.TypeTaskOps.Order.getValue());
						dto.getTask().setIdParent(entity.getOrder().getId());
					}
				})
				.register();


		BaseMapper.MAPPER_FACTORY.classMap(AssetTaskOrderDTO.class, AssetTaskOrder.class)
				.field("idAsset", "asset.idAsset")
				.field("idOrderTask", "orderTask.idOrderTask")
				.byDefault().register();

		BaseMapper.MAPPER_FACTORY.classMap(EmployeeTaskOrderDTO.class, EmployeeTaskOrder.class)
				.field("idOrderTask", "orderTask.idOrderTask")
				.field("idEmployee", "employee.idEmployee")
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