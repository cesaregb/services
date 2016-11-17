package com.il.sod.mapper;

import com.il.sod.db.model.entities.*;
import com.il.sod.rest.dto.db.*;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.ConverterFactory;

import java.util.Set;
import java.util.stream.Collectors;

public enum ClientMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private ClientMapper() {
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("orderSetConverter", new OrderSetConverter());
		converterFactory.registerConverter("addressDTOConverter", new AddressDTOConverter());
		converterFactory.registerConverter("clientPaymentInfoSetConverter", new ClientPaymentInfoSetConverter());
		converterFactory.registerConverter("clientDTOConverter", new ClientDTOConverter());
		converterFactory.registerConverter("clientBagSetConverter", new ClientBagSetConverter());

		BaseMapper.MAPPER_FACTORY.classMap(ClientDTO.class, Client.class)
			.fieldMap("orders", "orders").converter("orderSetConverter").mapNulls(false).mapNullsInReverse(true).add()
			.fieldMap("addresses", "addresses").converter("addressDTOConverter").mapNulls(false).mapNullsInReverse(true).add()
			.fieldMap("clientPaymentInfos", "clientPaymentInfos").converter("clientPaymentInfoSetConverter").mapNulls(false).mapNullsInReverse(true).add()
			.fieldMap("clientBags", "clientBags").converter("clientBagSetConverter").mapNulls(false).mapNullsInReverse(true).add()
			.field("idClientType","clientType.idClientType")
			.byDefault()
			.customize(new CustomMapper<ClientDTO, Client>() { // holder..
				@Override
				public void mapBtoA(Client client, ClientDTO clientDTO, MappingContext context) {
//					clientDTO.setIdClientType(client.getClientType().getIdClientType());
				}
			})
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(AddressDTO.class, Address.class)
			.field("idClient", "client.idClient")
			.byDefault()
			.register();

		BaseMapper.MAPPER_FACTORY.classMap(ClientTypeDTO.class, ClientType.class)
			.fieldMap("clients", "clients").converter("clientDTOConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ClientPaymentInfoDTO.class, ClientPaymentInfo.class)
			.field("idClient", "client.idClient")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(BagTypeDTO.class, BagType.class)
			.byDefault()
			.register();

		BaseMapper.MAPPER_FACTORY.classMap(ClientBagDTO.class, ClientBag.class)
			.field("idBagtype", "bagType.idBagType")
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
		ClientDTO result = this.mapperFacade.map(entity, ClientDTO.class);
		try{
			AddressDTO defAddress = null;
			defAddress = result.getAddresses().stream().filter(pn->pn.isPrefered()).collect(Collectors.toList()).get(0);
			result.setDefaultAddress(defAddress.toString());
		}catch(Exception e){ /* do nothing just assign null*/ }
		
		try{
			ClientPaymentInfoDTO defPayment = null;
			defPayment = result.getClientPaymentInfos().stream().filter(pn->pn.isPrefered()).collect(Collectors.toList()).get(0);
			result.setDefaultPayment(defPayment.toString());
		}catch(Exception e){ /* do nothing just assign null*/ }
		
		return result;
	}
	
	public Address map(AddressDTO dto) {
		return this.mapperFacade.map(dto, Address.class);
	}
	
	public Address map(AddressDTO dto, Address entity) {
		this.mapperFacade.map(dto, entity);
		return entity;
	}
	
	public AddressDTO map(Address entity) {
		return this.mapperFacade.map(entity, AddressDTO.class);
	}
	
	public Set<AddressDTO> map(Set<Address> source) {
		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
	
	public ClientPaymentInfo map(ClientPaymentInfoDTO input) {
		return this.mapperFacade.map(input, ClientPaymentInfo.class);
	}
	
	public ClientPaymentInfoDTO map(ClientPaymentInfo input) {
		return this.mapperFacade.map(input, ClientPaymentInfoDTO.class);
	}

	public BagType map(BagTypeDTO input) {
		return this.mapperFacade.map(input, BagType.class);
	}

	public BagTypeDTO map(BagType input) {
		return this.mapperFacade.map(input, BagTypeDTO.class);
	}

	public ClientBag map(ClientBagDTO input) {
		return this.mapperFacade.map(input, ClientBag.class);
	}

	public ClientBagDTO map(ClientBag input) {
		return this.mapperFacade.map(input, ClientBagDTO.class);
	}

	public ClientType map(ClientTypeDTO input) {
		return this.mapperFacade.map(input, ClientType.class);
	}

	public ClientTypeDTO map(ClientType input) {
		return this.mapperFacade.map(input, ClientTypeDTO.class);
	}

}



