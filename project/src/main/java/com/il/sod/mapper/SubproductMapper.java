package com.il.sod.mapper;

import com.il.sod.db.model.entities.ServiceSubproduct;
import com.il.sod.db.model.entities.Subproduct;
import com.il.sod.db.model.entities.SubproductType;
import com.il.sod.rest.dto.db.ServiceSubproductDTO;
import com.il.sod.rest.dto.db.SubproductDTO;
import com.il.sod.rest.dto.db.SubproductTypeDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.ConverterFactory;

public enum SubproductMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private SubproductMapper() {

		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("subproductSetConverter", new SubproductSetConverter());
		converterFactory.registerConverter("serviceTypeSet2IntConverter", new ServiceTypeSet2IntConverter());

		BaseMapper.MAPPER_FACTORY.classMap(SubproductDTO.class, Subproduct.class)
			.field("idSubproductType", "subproductType.idSubproductType")
			.field("typeName", "subproductType.name")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(SubproductTypeDTO.class, SubproductType.class)
			.fieldMap("subproducts", "subproducts").converter("subproductSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceTypes", "serviceTypes").converter("serviceTypeSet2IntConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();

		BaseMapper.MAPPER_FACTORY.classMap(ServiceSubproductDTO.class, ServiceSubproduct.class)
			.field("idService", "service.idService")
			.byDefault()
			.register();


		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Subproduct map(SubproductDTO dto) {
		return this.mapperFacade.map(dto, Subproduct.class);
	}
	
	public SubproductDTO map(Subproduct dto) {
		return this.mapperFacade.map(dto, SubproductDTO.class);
	}

	public SubproductType map(SubproductTypeDTO dto) {
		return this.mapperFacade.map(dto, SubproductType.class);
	}

	public SubproductTypeDTO map(SubproductType dto) {
		return this.mapperFacade.map(dto, SubproductTypeDTO.class);
	}

	public ServiceSubproduct map(ServiceSubproductDTO dto) {
		return this.mapperFacade.map(dto, ServiceSubproduct.class);
	}

	public ServiceSubproductDTO map(ServiceSubproduct dto) {
		return this.mapperFacade.map(dto, ServiceSubproductDTO.class);
	}

}



