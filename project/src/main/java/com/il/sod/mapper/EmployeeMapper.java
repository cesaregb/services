package com.il.sod.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.Employee;
import com.il.sod.db.model.entities.EmployeeType;
import com.il.sod.rest.dto.db.EmployeeDTO;
import com.il.sod.rest.dto.db.EmployeeTypeDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;

public enum EmployeeMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private EmployeeMapper() {
		
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("employeeListConverter", new EmployeeListConverter());
		
		BaseMapper.MAPPER_FACTORY.classMap(EmployeeDTO.class, Employee.class)
			.field("employeeType", "employeeType.idEmployeeType")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(EmployeeTypeDTO.class, EmployeeType.class)
			.fieldMap("employees", "employees").converter("employeeListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Employee map(EmployeeDTO dto) {
		return this.mapperFacade.map(dto, Employee.class);
	}

	public EmployeeDTO map(Employee entity) {
		return this.mapperFacade.map(entity, EmployeeDTO.class);
	}
	
	public EmployeeType map(EmployeeTypeDTO dto) {
		return this.mapperFacade.map(dto, EmployeeType.class);
	}
	
	public EmployeeTypeDTO map(EmployeeType entity) {
		return this.mapperFacade.map(entity, EmployeeTypeDTO.class);
	}
}

class EmployeeListConverter extends BidirectionalConverter<List<EmployeeDTO>, List<Employee>> {
	@Override
	public List<EmployeeDTO> convertFrom(List<Employee> source, Type<List<EmployeeDTO>> arg1) {
		return source.stream()
				.map(item -> EmployeeMapper.INSTANCE.map(item))
				.collect(Collectors.toList());
	}

	@Override
	public List<Employee> convertTo(List<EmployeeDTO> source, Type<List<Employee>> arg1) {
		return source.stream()
				.map(item -> EmployeeMapper.INSTANCE.map(item))
				.collect(Collectors.toList());
	}
}
