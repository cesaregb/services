package com.il.sod.mapper;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.il.sod.db.model.entities.Client;
import com.il.sod.rest.dto.db.ClientDTO;

public enum ClientMapper {
	
	INSTANCE;
	
	ModelMapper mm = new ModelMapper();

	private ClientMapper(){
		setDTOMapper();
	}
	
	public void setDTOMapper(){
		PropertyMap<Client, ClientDTO> map2DtoMapper = new PropertyMap<Client, ClientDTO>() {
			protected void configure() {
//				if (source.getOrders()!=null)
					map().setOrders(source.getOrders().stream().map(p -> p.getIdOrder())
							.collect(Collectors.toList()));
					
				if (source.getAddresses() != null && source.getAddresses().size() > 0)
					map().setPhoneNumber(source.getAddresses().get(0).toString());
				
				map().setPhoneNumber("this is the phone number -- yeyyyy!!! ");
			}
		};
		mm.addMappings(map2DtoMapper);
	}
	
	public ModelMapper getMapper(){
		return mm;
	}
	
	/* 
	 * example of simple usage
	 * 
	 * ModelMapper modelMapper = new ModelMapper();
	 * OrderDTO dto = modelMapper.map(order, OrderDTO.class);
	 * 
	 * */ 
}
