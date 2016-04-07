package com.il.sod.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.Task;
import com.il.sod.db.model.entities.TaskType;
import com.il.sod.rest.dto.db.TaskDTO;
import com.il.sod.rest.dto.db.TaskTypeDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;

public enum TaskMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private TaskMapper() {
		
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("taskSetConverter", new TaskSetConverter());
		
		BaseMapper.MAPPER_FACTORY.classMap(TaskDTO.class, Task.class)
			.field("taskType", "taskType.idTaskType")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(TaskTypeDTO.class, TaskType.class)
			.fieldMap("tasks", "tasks").converter("taskSetConverter").mapNulls(true).mapNullsInReverse(true).add()
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
