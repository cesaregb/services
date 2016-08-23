package com.il.sod.mapper;

import com.il.sod.db.model.entities.Service;
import com.il.sod.rest.dto.specifics.UIServiceDTO;
import ma.glasnost.orika.MapperFacade;

public enum UIMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private UIMapper() {
		
		BaseMapper.MAPPER_FACTORY.classMap(UIServiceDTO.class, Service.class)
			.field("idServiceType", "serviceType.idServiceType")
			.byDefault()
			.register();
		

		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public UIServiceDTO map(Service inp) {
		return this.mapperFacade.map(inp, UIServiceDTO.class);
	}
}

