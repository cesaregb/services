package com.il.sod.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.Order;
import com.il.sod.rest.dto.db.ClientDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;

public enum ClientMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private ClientMapper() {
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("orderListConverter", new OrderListConverter());

		BaseMapper.MAPPER_FACTORY.classMap(ClientDTO.class, Client.class)
			.fieldMap("orders", "orders").converter("orderListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Client map(ClientDTO dto) {
		return this.mapperFacade.map(dto, Client.class);
	}

	public ClientDTO map(Client entity) {
		return this.mapperFacade.map(entity, ClientDTO.class);
	}
}

class OrderListConverter extends BidirectionalConverter<List<Order>, List<Integer>> {
	@Override
	public List<Order> convertFrom(List<Integer> source, Type<List<Order>> destT) {
		return source.stream().map(p -> (new Order()).setIdOrder(p)).collect(Collectors.toList());
	}

	@Override
	public List<Integer> convertTo(List<Order> source, Type<List<Integer>> destT) {
		return source.stream().map(p -> p.getIdOrder()).collect(Collectors.toList());
	}
}