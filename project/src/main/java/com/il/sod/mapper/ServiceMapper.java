package com.il.sod.mapper;

import com.il.sod.db.model.entities.Service;
import com.il.sod.db.model.entities.ServiceCategory;
import com.il.sod.db.model.entities.ServiceType;
import com.il.sod.db.model.entities.ServiceTypeSpec;
import com.il.sod.db.model.entities.ServiceTypeTask;
import com.il.sod.rest.dto.db.ServiceCategoryDTO;
import com.il.sod.rest.dto.db.ServiceDTO;
import com.il.sod.rest.dto.db.ServiceTypeDTO;
import com.il.sod.rest.dto.db.ServiceTypeSpecDTO;
import com.il.sod.rest.dto.db.ServiceTypeTaskDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.ConverterFactory;

public enum ServiceMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private ServiceMapper() {
		
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("serviceListConverter", new ServiceListConverter());
		converterFactory.registerConverter("serviceTypeSpecListConverter", new ServiceTypeSpecListConverter());
		converterFactory.registerConverter("serviceTypeTaskListConverter", new ServiceTypeTaskListConverter());
		converterFactory.registerConverter("serviceSpecListConverter", new ServiceSpecListConverter());
		converterFactory.registerConverter("serviceTaskListConverter", new ServiceTaskListConverter());
		converterFactory.registerConverter("orderTypeListConverter", new OrderTypeListConverter());
		converterFactory.registerConverter("serviceTypeListConverter", new ServiceTypeListConverter());
		converterFactory.registerConverter("specConverter", new SpecConverter());
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceDTO.class, Service.class)
			.fieldMap("serviceSpecs", "serviceSpecs").converter("serviceSpecListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceTasks", "serviceTasks").converter("serviceTaskListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.field("serviceType", "serviceType.idServiceType")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTypeDTO.class, ServiceType.class)
			.fieldMap("services", "services").converter("serviceListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceTypeSpecs", "serviceTypeSpecs").converter("serviceTypeSpecListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceTypeTasks", "serviceTypeTasks").converter("serviceTypeTaskListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("orderTypes", "orderTypes").converter("orderTypeListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTypeSpecDTO.class, ServiceTypeSpec.class)
			.fieldMap("spec", "spec").converter("specConverter").mapNulls(true).mapNullsInReverse(true).add()
			.field("serviceType", "serviceType.idServiceType")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTypeTaskDTO.class, ServiceTypeTask.class)
			.field("serviceType", "serviceType.idServiceType")
			.field("task", "task.idTask")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceCategoryDTO.class, ServiceCategory.class)
			.fieldMap("serviceTypes", "serviceTypes").converter("serviceTypeListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Service map(ServiceDTO dto) {
		return this.mapperFacade.map(dto, Service.class);
	}

	public ServiceDTO map(Service entity) {
		return this.mapperFacade.map(entity, ServiceDTO.class);
	}
	
	public ServiceType map(ServiceTypeDTO dto) {
		return this.mapperFacade.map(dto, ServiceType.class);
	}
	
	public ServiceTypeDTO map(ServiceType entity) {
		return this.mapperFacade.map(entity, ServiceTypeDTO.class);
	}
	
	public ServiceTypeSpec map(ServiceTypeSpecDTO dto) {
		return this.mapperFacade.map(dto, ServiceTypeSpec.class);
	}
	
	public ServiceTypeSpecDTO map(ServiceTypeSpec entity) {
		return this.mapperFacade.map(entity, ServiceTypeSpecDTO.class);
	}
	
	public ServiceTypeTask map(ServiceTypeTaskDTO dto) {
		return this.mapperFacade.map(dto, ServiceTypeTask.class);
	}
	
	public ServiceTypeTaskDTO map(ServiceTypeTask entity) {
		return this.mapperFacade.map(entity, ServiceTypeTaskDTO.class);
	}
	
	public ServiceCategory map(ServiceCategoryDTO dto) {
		return this.mapperFacade.map(dto, ServiceCategory.class);
	}
	
	public ServiceCategoryDTO map(ServiceCategory entity) {
		return this.mapperFacade.map(entity, ServiceCategoryDTO.class);
	}
}

