package com.il.sod.mapper;

import com.il.sod.db.model.entities.ClientPaymentInfo;
import com.il.sod.db.model.entities.PaymentInfo;
import com.il.sod.rest.dto.db.ClientPaymentInfoDTO;
import com.il.sod.rest.dto.db.PaymentInfoDTO;

import ma.glasnost.orika.MapperFacade;

public enum PaymentMapper {

	INSTANCE;
	
	private final MapperFacade mapperFacade;

	private PaymentMapper() {
		
		BaseMapper.MAPPER_FACTORY.classMap(PaymentInfoDTO.class, PaymentInfo.class)
			.field("idOrder", "order.idOrder")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ClientPaymentInfoDTO.class, ClientPaymentInfo.class)
			.field("idClient", "client.idClient")
			.byDefault()
			.register();
		
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public PaymentInfo map(PaymentInfoDTO dto) {
		return this.mapperFacade.map(dto, PaymentInfo.class);
	}

	public PaymentInfoDTO map(PaymentInfo entity) {
		return this.mapperFacade.map(entity, PaymentInfoDTO.class);
	}
	
	public ClientPaymentInfo map(ClientPaymentInfoDTO dto) {
		return this.mapperFacade.map(dto, ClientPaymentInfo.class);
	}
	
	public ClientPaymentInfoDTO map(ClientPaymentInfo entity) {
		return this.mapperFacade.map(entity, ClientPaymentInfoDTO.class);
	}
	
}

