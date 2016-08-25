package com.il.sod.mapper;

import com.il.sod.db.model.entities.AssetTaskService;
import com.il.sod.db.model.entities.EmployeeTaskService;
import com.il.sod.db.model.entities.Service;
import com.il.sod.db.model.entities.ServiceCategory;
import com.il.sod.db.model.entities.ServiceComment;
import com.il.sod.db.model.entities.ServiceTask;
import com.il.sod.db.model.entities.ServiceType;
import com.il.sod.db.model.entities.ServiceTypeSpec;
import com.il.sod.db.model.entities.ServiceTypeTask;
import com.il.sod.rest.dto.db.AssetTaskServiceDTO;
import com.il.sod.rest.dto.db.EmployeeTaskServiceDTO;
import com.il.sod.rest.dto.db.ServiceCategoryDTO;
import com.il.sod.rest.dto.db.ServiceCommentDTO;
import com.il.sod.rest.dto.db.ServiceDTO;
import com.il.sod.rest.dto.db.ServiceTaskDTO;
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
		converterFactory.registerConverter("serviceSetConverter", new ServiceSet2IntConverter());
		converterFactory.registerConverter("serviceTypeSpecSetConverter", new ServiceTypeSpecSetConverter());
		converterFactory.registerConverter("serviceTypeTaskSetConverter", new ServiceTypeTaskSetConverter());
		converterFactory.registerConverter("serviceSpecSetConverter", new ServiceSpecSetConverter());
		converterFactory.registerConverter("serviceTaskSetConverter", new ServiceTaskSetConverter());
		converterFactory.registerConverter("orderTypeSetConverter", new OrderTypeSetConverter());
		converterFactory.registerConverter("serviceTypeSetConverter", new ServiceTypeSetConverter());
		converterFactory.registerConverter("specConverter", new SpecConverter());
		converterFactory.registerConverter("assetTaskServiceSetConverter", new AssetTaskServiceSetConverter());
		converterFactory.registerConverter("employeeTaskServiceSetConverter", new EmployeeTaskServiceSetConverter());
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceDTO.class, Service.class)
			.fieldMap("serviceSpecs", "serviceSpecs").converter("serviceSpecSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceTasks", "serviceTasks").converter("serviceTaskSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.field("idServiceCategory", "serviceType.serviceCategory.idServiceCategory")
			.field("idServiceType", "serviceType.idServiceType")
			.field("idOrder", "order.idOrder")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTypeDTO.class, ServiceType.class)
			.fieldMap("services", "services").converter("serviceSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceTypeSpecs", "serviceTypeSpecs").converter("serviceTypeSpecSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceTypeTasks", "serviceTypeTasks").converter("serviceTypeTaskSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.field("serviceTypeCategoryName", "serviceCategory.name")
			.field("idServiceCategory", "serviceCategory.idServiceCategory")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTypeSpecDTO.class, ServiceTypeSpec.class)
			.fieldMap("spec", "spec").converter("specConverter").mapNulls(true).mapNullsInReverse(true).add()
			.field("idServiceType", "serviceType.idServiceType")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTypeTaskDTO.class, ServiceTypeTask.class)
			.field("idServiceType", "serviceType.idServiceType")
			.field("serviceTypeName", "serviceType.name")
			.field("taskTypeName", "task.taskType.name")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceCategoryDTO.class, ServiceCategory.class)
			.fieldMap("serviceTypes", "serviceTypes").converter("serviceTypeSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTaskDTO.class, ServiceTask.class)
			.fieldMap("assetTaskServices", "assetTaskServices").converter("assetTaskServiceSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("employeeTaskServices", "employeeTaskServices").converter("employeeTaskServiceSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.field("idService", "service.idService")
			.field("idTask", "task.idTask")
			.byDefault().register();
		
		BaseMapper.MAPPER_FACTORY.classMap(AssetTaskServiceDTO.class, AssetTaskService.class)
			.field("idAsset", "asset.idAsset")
			.field("idServiceTask", "serviceTask.idServiceTask")
			.byDefault().register();

		BaseMapper.MAPPER_FACTORY.classMap(EmployeeTaskServiceDTO.class, EmployeeTaskService.class)
			.field("idServiceTask", "serviceTask.idServiceTask")
			.field("idEmployee", "employee.idEmployee")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceCommentDTO.class, ServiceComment.class)
			.field("idService", "service.idService")
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
	
	public ServiceTask map(ServiceTaskDTO dto) {
		return this.mapperFacade.map(dto, ServiceTask.class);
	}

	public ServiceTaskDTO map(ServiceTask entity) {
		return this.mapperFacade.map(entity, ServiceTaskDTO.class);
	}
	
	public AssetTaskServiceDTO map(AssetTaskService dto) {
		return this.mapperFacade.map(dto, AssetTaskServiceDTO.class);
	}
	public AssetTaskService map(AssetTaskServiceDTO dto) {
		return this.mapperFacade.map(dto, AssetTaskService.class);
	}
	public EmployeeTaskServiceDTO map(EmployeeTaskService dto) {
		return this.mapperFacade.map(dto, EmployeeTaskServiceDTO.class);
	}
	public EmployeeTaskService map(EmployeeTaskServiceDTO dto) {
		return this.mapperFacade.map(dto, EmployeeTaskService.class);
	}
	
	public ServiceCommentDTO map(ServiceComment dto) {
		return this.mapperFacade.map(dto, ServiceCommentDTO.class);
	}
	
	public ServiceComment map(ServiceCommentDTO dto) {
		return this.mapperFacade.map(dto, ServiceComment.class);
	}
}

