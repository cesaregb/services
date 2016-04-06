package com.il.sod.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.AssetTaskOrder;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.EmployeeTaskOrder;
import com.il.sod.db.model.entities.Order;
import com.il.sod.db.model.entities.OrderPickNDeliver;
import com.il.sod.db.model.entities.OrderTask;
import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.entities.OrderTypeTask;
import com.il.sod.db.model.entities.PaymentInfo;
import com.il.sod.db.model.entities.Service;
import com.il.sod.db.model.entities.ServiceSpec;
import com.il.sod.db.model.entities.ServiceTask;
import com.il.sod.db.model.entities.ServiceType;
import com.il.sod.db.model.entities.ServiceTypeSpec;
import com.il.sod.db.model.entities.ServiceTypeTask;
import com.il.sod.db.model.entities.Spec;
import com.il.sod.rest.dto.db.AssetTaskOrderDTO;
import com.il.sod.rest.dto.db.ClientDTO;
import com.il.sod.rest.dto.db.EmployeeTaskOrderDTO;
import com.il.sod.rest.dto.db.OrderPickNDeliverDTO;
import com.il.sod.rest.dto.db.OrderTaskDTO;
import com.il.sod.rest.dto.db.OrderTypeTaskDTO;
import com.il.sod.rest.dto.db.PaymentInfoDTO;
import com.il.sod.rest.dto.db.ServiceTypeDTO;
import com.il.sod.rest.dto.db.ServiceTypeSpecDTO;
import com.il.sod.rest.dto.db.SpecDTO;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class BidirectionalConverters {}

// Orders Mappers
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

class EmployeeTaskOrderListConverter
		extends BidirectionalConverter<List<EmployeeTaskOrder>, List<EmployeeTaskOrderDTO>> {
	@Override
	public List<EmployeeTaskOrder> convertFrom(List<EmployeeTaskOrderDTO> source, Type<List<EmployeeTaskOrder>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}

	@Override
	public List<EmployeeTaskOrderDTO> convertTo(List<EmployeeTaskOrder> source, Type<List<EmployeeTaskOrderDTO>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
}

class OrderPickNDeliverListConverter
		extends BidirectionalConverter<List<OrderPickNDeliver>, List<OrderPickNDeliverDTO>> {
	@Override
	public List<OrderPickNDeliver> convertFrom(List<OrderPickNDeliverDTO> source, Type<List<OrderPickNDeliver>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}

	@Override
	public List<OrderPickNDeliverDTO> convertTo(List<OrderPickNDeliver> source, Type<List<OrderPickNDeliverDTO>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
}

class PaymentInfoListConverter extends BidirectionalConverter<List<PaymentInfo>, List<PaymentInfoDTO>> {
	@Override
	public List<PaymentInfo> convertFrom(List<PaymentInfoDTO> source, Type<List<PaymentInfo>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}

	@Override
	public List<PaymentInfoDTO> convertTo(List<PaymentInfo> source, Type<List<PaymentInfoDTO>> arg1) {
		return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
}

// Service Mapper
class ServiceListConverter extends BidirectionalConverter<List<Service>, List<Integer>> {
	@Override
	public List<Service> convertFrom(List<Integer> source, Type<List<Service>> arg1) {
		return source.stream().map(p -> (new Service()).setId(p)).collect(Collectors.toList());
	}

	@Override
	public List<Integer> convertTo(List<Service> source, Type<List<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toList());
	}
}

class ServiceTypeSpecListConverter extends BidirectionalConverter<List<ServiceTypeSpec>, List<ServiceTypeSpecDTO>> {
	@Override
	public List<ServiceTypeSpec> convertFrom(List<ServiceTypeSpecDTO> source, Type<List<ServiceTypeSpec>> arg1) {
		return source.stream().map(item -> ServiceMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
	@Override
	public List<ServiceTypeSpecDTO> convertTo(List<ServiceTypeSpec> source, Type<List<ServiceTypeSpecDTO>> arg1) {
		return source.stream().map(item -> ServiceMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
}

class ServiceTypeTaskListConverter extends BidirectionalConverter<List<ServiceTypeTask>, List<Integer>> {
	@Override
	public List<ServiceTypeTask> convertFrom(List<Integer> source, Type<List<ServiceTypeTask>> arg1) {
		return source.stream().map(p -> (new ServiceTypeTask()).setId(p)).collect(Collectors.toList());
	}
	
	@Override
	public List<Integer> convertTo(List<ServiceTypeTask> source, Type<List<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toList());
	}
}

class ServiceSpecListConverter extends BidirectionalConverter<List<ServiceSpec>, List<Integer>> {
	@Override
	public List<ServiceSpec> convertFrom(List<Integer> source, Type<List<ServiceSpec>> arg1) {
		return source.stream().map(p -> (new ServiceSpec()).setId(p)).collect(Collectors.toList());
	}
	
	@Override
	public List<Integer> convertTo(List<ServiceSpec> source, Type<List<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toList());
	}
}

class ServiceTaskListConverter extends BidirectionalConverter<List<ServiceTask>, List<Integer>> {
	@Override
	public List<ServiceTask> convertFrom(List<Integer> source, Type<List<ServiceTask>> arg1) {
		return source.stream().map(p -> (new ServiceTask()).setId(p)).collect(Collectors.toList());
	}
	
	@Override
	public List<Integer> convertTo(List<ServiceTask> source, Type<List<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toList());
	}
}

class OrderTypeListConverter extends BidirectionalConverter<List<OrderType>, List<Integer>> {
	@Override
	public List<OrderType> convertFrom(List<Integer> source, Type<List<OrderType>> arg1) {
		return source.stream().map(p -> (new OrderType()).setId(p)).collect(Collectors.toList());
	}

	@Override
	public List<Integer> convertTo(List<OrderType> source, Type<List<Integer>> arg1) {
		return source.stream().map(p -> p.getId()).collect(Collectors.toList());
	}
}

class ServiceTypeListConverter extends BidirectionalConverter<List<ServiceType>, List<ServiceTypeDTO>> {
	@Override
	public List<ServiceType> convertFrom(List<ServiceTypeDTO> source, Type<List<ServiceType>> arg1) {
		return source.stream().map(item -> ServiceMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}

	@Override
	public List<ServiceTypeDTO> convertTo(List<ServiceType> source, Type<List<ServiceTypeDTO>> arg1) {
		return source.stream().map(item -> ServiceMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
}

class SpecConverter extends BidirectionalConverter<Spec, SpecDTO> {
	@Override
	public Spec convertFrom(SpecDTO source, Type<Spec> arg1) {
		return SpecsMapper.INSTANCE.map(source);
	}

	@Override
	public SpecDTO convertTo(Spec source, Type<SpecDTO> arg1) {
		return SpecsMapper.INSTANCE.map(source);
	}
}