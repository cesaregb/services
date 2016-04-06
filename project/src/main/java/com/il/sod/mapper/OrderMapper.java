package com.il.sod.mapper;

import com.il.sod.db.model.entities.AssetTaskOrder;
import com.il.sod.db.model.entities.EmployeeTaskOrder;
import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.entities.OrderPickNDeliver;
import com.il.sod.db.model.entities.OrderTask;
import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.entities.OrderTypeTask;
import com.il.sod.db.model.entities.PaymentInfo;
import com.il.sod.rest.dto.db.AssetTaskOrderDTO;
import com.il.sod.rest.dto.db.EmployeeTaskOrderDTO;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.db.OrderPickNDeliverDTO;
import com.il.sod.rest.dto.db.OrderTaskDTO;
import com.il.sod.rest.dto.db.OrderTypeDTO;
import com.il.sod.rest.dto.db.OrderTypeTaskDTO;
import com.il.sod.rest.dto.db.PaymentInfoDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.ConverterFactory;

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
		converterFactory.registerConverter("orderPickNDeliverListConverter", new OrderPickNDeliverListConverter());
		converterFactory.registerConverter("paymentInfoListConverter", new PaymentInfoListConverter());
		converterFactory.registerConverter("serviceTypeListConverter", new ServiceTypeListConverter());

		BaseMapper.MAPPER_FACTORY.classMap(OrderTypeDTO.class, OrderType.class).fieldMap("orders", "orders")
				.converter("orderListConverter").mapNulls(true).mapNullsInReverse(true).add()
				.fieldMap("orderTypeTasks", "orderTypeTasks").converter("orderTypeTaskListConverter").mapNulls(true)
				.mapNullsInReverse(true).add().fieldMap("serviceTypes", "serviceTypes")
				.converter("serviceTypeListConverter").mapNulls(true).mapNullsInReverse(true).add().byDefault()
				.register();

		BaseMapper.MAPPER_FACTORY.classMap(OrderTypeTaskDTO.class, OrderTypeTask.class)
				.field("orderType", "orderType.idOrderType").field("task", "task.idTask").byDefault().register();

		BaseMapper.MAPPER_FACTORY.classMap(OrderDTO.class, Order.class).fieldMap("client", "client")
				.converter("clientConverter").mapNulls(true).mapNullsInReverse(true).add()
				.fieldMap("orderTasks", "orderTasks").converter("orderTaskListConverter").mapNulls(true)
				.mapNullsInReverse(true).add().fieldMap("orderPickNdelivers", "orderPickNdelivers")
				.converter("orderPickNDeliverListConverter").mapNulls(true).mapNullsInReverse(true).add()
				.fieldMap("paymentInfos", "paymentInfos").converter("paymentInfoListConverter").mapNulls(true)
				.mapNullsInReverse(true).add().field("orderType", "orderType.idOrderType").byDefault().register();

		BaseMapper.MAPPER_FACTORY.classMap(OrderTaskDTO.class, OrderTask.class)
				.fieldMap("assetTaskOrders", "assetTaskOrders").converter("assetTaskOrderListConverter").mapNulls(true)
				.mapNullsInReverse(true).add().fieldMap("employeeTaskOrders", "employeeTaskOrders")
				.converter("employeeTaskOrderListConverter").mapNulls(true).mapNullsInReverse(true).add()
				.field("order", "order.idOrder").field("task", "task.idTask").byDefault().register();

		BaseMapper.MAPPER_FACTORY.classMap(AssetTaskOrderDTO.class, AssetTaskOrder.class)
				.field("asset", "asset.idAsset").field("orderTask", "orderTask.idOrderTask").byDefault().register();

		BaseMapper.MAPPER_FACTORY.classMap(EmployeeTaskOrderDTO.class, EmployeeTaskOrder.class)
				.field("orderTask", "orderTask.idOrderTask").field("employee", "employee.idEmployee").byDefault()
				.register();

		BaseMapper.MAPPER_FACTORY.classMap(OrderPickNDeliverDTO.class, OrderPickNDeliver.class)
				.field("order", "order.idOrder").field("address", "address.idAddress").byDefault().register();

		BaseMapper.MAPPER_FACTORY.classMap(PaymentInfoDTO.class, PaymentInfo.class).field("order", "order.idOrder")
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

	public PaymentInfo map(PaymentInfoDTO dto) {
		return this.mapperFacade.map(dto, PaymentInfo.class);
	}

	public PaymentInfoDTO map(PaymentInfo entity) {
		return this.mapperFacade.map(entity, PaymentInfoDTO.class);
	}

}