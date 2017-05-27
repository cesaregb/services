package com.il.sod.mapper;

import com.il.sod.db.model.entities.Supply;
import com.il.sod.db.model.entities.SupplyType;
import com.il.sod.rest.dto.db.SupplyDTO;
import com.il.sod.rest.dto.db.SupplyTypeDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;

import java.util.Set;
import java.util.stream.Collectors;

public enum SupplyMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private SupplyMapper() {
		
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("supplySetConverter", new SupplySetConverter());
		
		BaseMapper.MAPPER_FACTORY.classMap(SupplyDTO.class, Supply.class)
			.field("idSupplyType", "supplyType.idSupplyType")
			.field("supplyTypeName", "supplyType.name")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(SupplyTypeDTO.class, SupplyType.class)
			.fieldMap("supplies", "supplies").converter("supplySetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Supply map(SupplyDTO dto) {
		return this.mapperFacade.map(dto, Supply.class);
	}

	public SupplyDTO map(Supply entity) {
		return this.mapperFacade.map(entity, SupplyDTO.class);
	}
	
	public SupplyType map(SupplyTypeDTO dto) {
		return this.mapperFacade.map(dto, SupplyType.class);
	}
	
	public SupplyTypeDTO map(SupplyType entity) {
		return this.mapperFacade.map(entity, SupplyTypeDTO.class);
	}
}

class SupplySetConverter extends BidirectionalConverter<Set<SupplyDTO>, Set<Supply>> {
	@Override
	public Set<SupplyDTO> convertFrom(Set<Supply> source, Type<Set<SupplyDTO>> arg1) {
		return source.stream()
				.map(item -> SupplyMapper.INSTANCE.map(item))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<Supply> convertTo(Set<SupplyDTO> source, Type<Set<Supply>> arg1) {
		return source.stream()
				.map(item -> SupplyMapper.INSTANCE.map(item))
				.collect(Collectors.toSet());
	}
}
