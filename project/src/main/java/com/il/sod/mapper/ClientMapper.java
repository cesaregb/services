package com.il.sod.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.Address;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.PhoneNumber;
import com.il.sod.rest.dto.db.AddressDTO;
import com.il.sod.rest.dto.db.ClientDTO;
import com.il.sod.rest.dto.db.PhoneNumberDTO;

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
		converterFactory.registerConverter("addressDTOConverter", new AddressDTOConverter());
		converterFactory.registerConverter("phoneNumberDTOConverter", new PhoneNumberDTOConverter());

		BaseMapper.MAPPER_FACTORY.classMap(ClientDTO.class, Client.class)
			.fieldMap("orders", "orders").converter("orderListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("addresses", "addresses").converter("addressDTOConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("phoneNumbers", "phoneNumbers").converter("phoneNumberDTOConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(AddressDTO.class, Address.class)
			.field("idClient", "client.idClient")
			.exclude("orderPickNdelivers")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(PhoneNumberDTO.class, PhoneNumber.class)
			.field("idClient", "client.idClient")
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
	
	public List<AddressDTO> map(List<Address> source) {
		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
	
	public PhoneNumber map(PhoneNumberDTO dto) {
		return this.mapperFacade.map(dto, PhoneNumber.class);
	}
	
	public PhoneNumberDTO map(PhoneNumber entity) {
		return this.mapperFacade.map(entity, PhoneNumberDTO.class);
	}
}

class AddressDTOConverter extends BidirectionalConverter<List<Address>, List<AddressDTO>> {
	@Override
	public List<Address> convertFrom(List<AddressDTO> source, Type<List<Address>> arg1) {
		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}

	@Override
	public List<AddressDTO> convertTo(List<Address> source, Type<List<AddressDTO>> arg1) {
		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
}

class PhoneNumberDTOConverter extends BidirectionalConverter<List<PhoneNumber>, List<PhoneNumberDTO>> {
	@Override
	public List<PhoneNumber> convertFrom(List<PhoneNumberDTO> source, Type<List<PhoneNumber>> arg1) {
		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}

	@Override
	public List<PhoneNumberDTO> convertTo(List<PhoneNumber> source, Type<List<PhoneNumberDTO>> arg1) {
		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
}