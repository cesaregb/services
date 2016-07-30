package com.il.sod.mapper;

import com.il.sod.db.model.entities.AssetTaskOrder;
import com.il.sod.db.model.entities.EmployeeTaskOrder;
import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.entities.OrderPickNDeliver;
import com.il.sod.db.model.entities.OrderTask;
import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.entities.OrderTypeTask;
import com.il.sod.rest.dto.db.AssetTaskOrderDTO;
import com.il.sod.rest.dto.db.EmployeeTaskOrderDTO;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.db.OrderPickNDeliverDTO;
import com.il.sod.rest.dto.db.OrderTaskDTO;
import com.il.sod.rest.dto.db.OrderTypeDTO;
import com.il.sod.rest.dto.db.OrderTypeTaskDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.ConverterFactory;

public enum OrderMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private OrderMapper() {

		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("orderSetConverter", new OrderSetConverter());
		converterFactory.registerConverter("orderTaskSetConverter", new OrderTaskSetConverter());
		converterFactory.registerConverter("orderTypeTaskSetConverter", new OrderTypeTaskSetConverter());
		converterFactory.registerConverter("clientConverter", new ClientConverter());
		converterFactory.registerConverter("assetTaskOrderSetConverter", new AssetTaskOrderSetConverter());
		converterFactory.registerConverter("employeeTaskOrderSetConverter", new EmployeeTaskOrderSetConverter());
		converterFactory.registerConverter("orderPickNDeliverSetConverter", new OrderPickNDeliverSetConverter());
		converterFactory.registerConverter("paymentInfoSetConverter", new PaymentInfoSetConverter());
		converterFactory.registerConverter("serviceTypeSetConverter", new ServiceTypeSetConverter());
		converterFactory.registerConverter("serviceSet2IntConverter", new ServiceSet2IntConverter());
		converterFactory.registerConverter("serviceSetConverter", new ServiceSetConverter());

		BaseMapper.MAPPER_FACTORY.classMap(OrderTypeDTO.class, OrderType.class)
				.fieldMap("orders", "orders").converter("orderSetConverter").mapNulls(true).mapNullsInReverse(true).add()
				.fieldMap("orderTypeTasks", "orderTypeTasks").converter("orderTypeTaskSetConverter").mapNulls(true).mapNullsInReverse(true).add()
				.byDefault()
				.register();

		BaseMapper.MAPPER_FACTORY.classMap(OrderTypeTaskDTO.class, OrderTypeTask.class)
				.field("idOrderType", "orderType.idOrderType")
				.field("taskTypeName", "task.taskType.name")
				.byDefault().register();

		BaseMapper.MAPPER_FACTORY.classMap(OrderDTO.class, Order.class)
				.fieldMap("client", "client").converter("clientConverter").mapNulls(true).mapNullsInReverse(true).add()
				.fieldMap("orderTasks", "orderTasks").converter("orderTaskSetConverter").mapNulls(true).mapNullsInReverse(true).add()
				.fieldMap("orderPickNdelivers", "orderPickNdelivers").converter("orderPickNDeliverSetConverter").mapNulls(true).mapNullsInReverse(true).add()
				.fieldMap("paymentInfos", "paymentInfos").converter("paymentInfoSetConverter").mapNulls(true).mapNullsInReverse(true).add()
				.fieldMap("services", "services").converter("serviceSetConverter").mapNulls(true).mapNullsInReverse(true).add()
				.field("idOrderType", "orderType.idOrderType")
				.byDefault().register();

		BaseMapper.MAPPER_FACTORY.classMap(OrderTaskDTO.class, OrderTask.class)
				.fieldMap("assetTaskOrders", "assetTaskOrders").converter("assetTaskOrderSetConverter").mapNulls(true).mapNullsInReverse(true).add()
				.fieldMap("employeeTaskOrders", "employeeTaskOrders").converter("employeeTaskOrderSetConverter").mapNulls(true).mapNullsInReverse(true).add()
				.field("idOrder", "order.idOrder")
				.field("idTask", "task.idTask").byDefault().register();

		BaseMapper.MAPPER_FACTORY.classMap(AssetTaskOrderDTO.class, AssetTaskOrder.class)
				.field("idAsset", "asset.idAsset")
				.field("idOrderTask", "orderTask.idOrderTask")
				.byDefault().register();

		BaseMapper.MAPPER_FACTORY.classMap(EmployeeTaskOrderDTO.class, EmployeeTaskOrder.class)
				.field("idOrderTask", "orderTask.idOrderTask")
				.field("idEmployee", "employee.idEmployee")
				.byDefault()
				.register();

		BaseMapper.MAPPER_FACTORY.classMap(OrderPickNDeliverDTO.class, OrderPickNDeliver.class)
				.field("idOrder", "order.idOrder")
				.field("idAddress", "address.idAddress")
				.byDefault().register();

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

	public OrderPickNDeliver map(OrderPickNDeliverDTO dto) {
		return this.mapperFacade.map(dto, OrderPickNDeliver.class);
	}

	public OrderPickNDeliverDTO map(OrderPickNDeliver entity) {
		return this.mapperFacade.map(entity, OrderPickNDeliverDTO.class);
	}
}