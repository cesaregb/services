package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.EmployeeType;
import com.il.sod.db.model.repositories.EmployeeTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.EmployeeMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.EmployeeTypeDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/employees/employee-type", produces = MediaType.APPLICATION_JSON)
@Api(value = "/employees/employee-type", tags = {"employees"})
public class EmployeeTypeService extends AbstractServiceMutations {
	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Employee Type", response = EmployeeTypeDTO.class)
	public ResponseEntity<EmployeeTypeDTO> saveEmployeeType(EmployeeTypeDTO dto) throws SODAPIException {
		EmployeeType entity = EmployeeMapper.INSTANCE.map(dto);
		this.saveEntity(employeeTypeRepository, entity);
		dto = EmployeeMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Employee Type", response = EmployeeTypeDTO.class)
	public ResponseEntity<EmployeeTypeDTO> updateEmployeeType(EmployeeTypeDTO dto) throws SODAPIException {
		EmployeeType entity = EmployeeMapper.INSTANCE.map(dto);
		this.updateEntity(employeeTypeRepository, entity);
		dto = EmployeeMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Task Type", response = GeneralResponseMessage.class)
	public ResponseEntity<GeneralResponseMessage> deleteItem(@PathVariable("id") String id) throws SODAPIException {
		EmployeeType entity = employeeTypeRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Item not found");
		}
		this.softDeleteEntity(employeeTypeRepository, entity.getId());
		return new ResponseEntity<>(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Employee Type list", response = EmployeeTypeDTO.class, responseContainer = "List")
	public ResponseEntity<List<EmployeeTypeDTO>> getEmployeeTypeList() throws SODAPIException {
		List<EmployeeType> rentityList = this.getEntityList(employeeTypeRepository);
		List<EmployeeTypeDTO> list = rentityList.stream().map(EmployeeMapper.INSTANCE::map)
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
