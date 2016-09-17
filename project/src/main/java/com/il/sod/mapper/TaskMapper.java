package com.il.sod.mapper;

import com.il.sod.db.model.entities.*;
import com.il.sod.rest.dto.db.TaskDTO;
import com.il.sod.rest.dto.db.TaskTypeDTO;
import com.il.sod.rest.dto.specifics.TaskInfoDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;

import java.util.Set;
import java.util.stream.Collectors;

public enum TaskMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private TaskMapper() {
			
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("taskSetConverter", new TaskSetConverter());
		
		BaseMapper.MAPPER_FACTORY.classMap(TaskDTO.class, Task.class)
			.field("idTaskType", "taskType.idTaskType")
			.field("taskTypeName", "taskType.name")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(TaskTypeDTO.class, TaskType.class)
			.fieldMap("tasks", "tasks").converter("taskSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(OrderTypeTask.class, OrderTask.class)
			.exclude("id")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ServiceTypeTask.class, ServiceTask.class)
			.exclude("id")
			.byDefault()
			.register();

		BaseMapper.MAPPER_FACTORY.classMap(TaskInfoDTO.class, ServiceTask.class)
			.field("idParent", "service.idService")
			.byDefault()
			.register();

		BaseMapper.MAPPER_FACTORY.classMap(TaskInfoDTO.class, OrderTask.class)
			.field("idParent", "order.idOrder")
			.byDefault()
			.register();

		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Task map(TaskDTO dto) {
		return this.mapperFacade.map(dto, Task.class);
	}

	public TaskDTO map(Task entity) {
		return this.mapperFacade.map(entity, TaskDTO.class);
	}
	
	public TaskType map(TaskTypeDTO dto) {
		return this.mapperFacade.map(dto, TaskType.class);
	}
	
	public TaskTypeDTO map(TaskType entity) {
		return this.mapperFacade.map(entity, TaskTypeDTO.class);
	}
	
	public OrderTask map(OrderTypeTask entity){
		return this.mapperFacade.map(entity, OrderTask.class);
	}
	
	public ServiceTask map(ServiceTypeTask entity){
		return this.mapperFacade.map(entity, ServiceTask.class);
	}

	public TaskInfoDTO map(ServiceTask input){
		return this.mapperFacade.map(input, TaskInfoDTO.class);
	}

	public TaskInfoDTO map(OrderTask input){
		return this.mapperFacade.map(input, TaskInfoDTO.class);
	}

}

class TaskSetConverter extends BidirectionalConverter<Set<TaskDTO>, Set<Task>> {
	@Override
	public Set<TaskDTO> convertFrom(Set<Task> source, Type<Set<TaskDTO>> arg1) {
		return source.stream()
				.map(item -> TaskMapper.INSTANCE.map(item))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<Task> convertTo(Set<TaskDTO> source, Type<Set<Task>> arg1) {
		return source.stream()
				.map(item -> TaskMapper.INSTANCE.map(item))
				.collect(Collectors.toSet());
	}
}
