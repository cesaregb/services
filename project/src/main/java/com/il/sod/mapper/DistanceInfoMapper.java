package com.il.sod.mapper;

import com.il.sod.db.model.entities.DistanceInfo;
import com.il.sod.rest.dto.db.DistanceInfoDTO;

import ma.glasnost.orika.MapperFacade;

public enum DistanceInfoMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private DistanceInfoMapper() {
		BaseMapper.MAPPER_FACTORY.classMap(DistanceInfoDTO.class, DistanceInfo.class)
			.byDefault()
			.register();
		
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public DistanceInfo map(DistanceInfoDTO dto) {
		return this.mapperFacade.map(dto, DistanceInfo.class);
	}

	public DistanceInfoDTO map(DistanceInfo entity) {
		return this.mapperFacade.map(entity, DistanceInfoDTO.class);
	}
}
