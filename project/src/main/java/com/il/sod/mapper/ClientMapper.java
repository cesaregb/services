package com.il.sod.mapper;

import java.util.Set;
import java.util.Set;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.Address;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.ClientPaymentInfo;
import com.il.sod.db.model.entities.PhoneNumber;
import com.il.sod.rest.dto.db.AddressDTO;
import com.il.sod.rest.dto.db.ClientDTO;
import com.il.sod.rest.dto.db.ClientPaymentInfoDTO;
import com.il.sod.rest.dto.db.PhoneNumberDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.ConverterFactory;

public enum ClientMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private ClientMapper() {
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("orderSetConverter", new OrderSetConverter());
		converterFactory.registerConverter("addressDTOConverter", new AddressDTOConverter());
		converterFactory.registerConverter("phoneNumberDTOConverter", new PhoneNumberDTOConverter());

		BaseMapper.MAPPER_FACTORY.classMap(ClientDTO.class, Client.class)
			.fieldMap("orders", "orders").converter("orderSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("addresses", "addresses").converter("addressDTOConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("phoneNumbers", "phoneNumbers").converter("phoneNumberDTOConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(AddressDTO.class, Address.class)
			.field("client", "client.idClient")
			.exclude("orderPickNdelivers")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(PhoneNumberDTO.class, PhoneNumber.class)
			.field("client", "client.idClient")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ClientPaymentInfoDTO.class, ClientPaymentInfo.class)
			.field("client", "client.idClient")
			.byDefault()
			.register();
		
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Client map(ClientDTO dto) {
		return this.mapperFacade.map(dto, Client.class);
	}
	
	public Client map(ClientDTO dto, Client entity) {
		this.mapperFacade.map(dto, entity);
		return entity;
	}

	public ClientDTO map(Client entity) {
		return this.mapperFacade.map(entity, ClientDTO.class);
	}
	
	public Address map(AddressDTO dto) {
		return this.mapperFacade.map(dto, Address.class);
	}
	
	public AddressDTO map(Address entity) {
		return this.mapperFacade.map(entity, AddressDTO.class);
	}
	
	public Set<AddressDTO> map(Set<Address> source) {
		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
	
	public PhoneNumber map(PhoneNumberDTO dto) {
		return this.mapperFacade.map(dto, PhoneNumber.class);
	}
	
	public PhoneNumberDTO map(PhoneNumber entity) {
		return this.mapperFacade.map(entity, PhoneNumberDTO.class);
	}
	
	public ClientPaymentInfo map(ClientPaymentInfoDTO dto) {
		return this.mapperFacade.map(dto, ClientPaymentInfo.class);
	}
	
	public ClientPaymentInfoDTO map(ClientPaymentInfo entity) {
		return this.mapperFacade.map(entity, ClientPaymentInfoDTO.class);
	}
}



