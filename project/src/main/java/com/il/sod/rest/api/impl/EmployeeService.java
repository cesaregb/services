package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.Employee;
import com.il.sod.db.model.repositories.EmployeeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.EmployeeMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.EmployeeDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/employee", tags = { "employee" })
public class EmployeeService extends AbstractServiceMutations {
	@Autowired
	EmployeeRepository employeeRepository;

	@POST
	@ApiOperation(value = "Create Employee", response = EmployeeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveEmployee(EmployeeDTO dto) throws SODAPIException {
		try {
			Employee entity = EmployeeMapper.INSTANCE.map(dto);
			this.saveEntity(employeeRepository, entity);
			dto = EmployeeMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Employee", response = EmployeeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateEmployee(EmployeeDTO dto) throws SODAPIException {
		try {
			Employee entity = EmployeeMapper.INSTANCE.map(dto);
			this.updateEntity(employeeRepository, entity);
			dto = EmployeeMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Employee", response = EmployeeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteEmployee(EmployeeDTO dto) throws SODAPIException {
		try {
			Employee entity = EmployeeMapper.INSTANCE.map(dto);
			this.deleteEntity(employeeRepository, entity.getIdEmployee());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Employee deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Employee list", response = EmployeeDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getEmployeeList() throws SODAPIException {
		List<Employee> entityList = this.getEntityList(employeeRepository);
		List<EmployeeDTO> list = entityList.stream().map((i) -> {
			EmployeeDTO dto = EmployeeMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}


}
