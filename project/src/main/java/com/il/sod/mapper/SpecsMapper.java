package com.il.sod.mapper;

import com.il.sod.db.model.entities.Spec;
import com.il.sod.rest.dto.db.SpecDTO;

import ma.glasnost.orika.MapperFacade;

public enum SpecsMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private SpecsMapper() {
		
		BaseMapper.MAPPER_FACTORY.classMap(SpecDTO.class, Spec.class)
			.field("productTypeId", "productType.idSpecType")
			.byDefault()
			.register();
		
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Spec map(SpecDTO dto) {
		return this.mapperFacade.map(dto, Spec.class);
	}

	public SpecDTO map(Spec entity) {
		return this.mapperFacade.map(entity, SpecDTO.class);
	}	
}