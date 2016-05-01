package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT; import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.EmployeeType;
import com.il.sod.db.model.repositories.EmployeeTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.EmployeeMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.EmployeeTypeDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/employee-type")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/employee-type", tags = { "employee" })
public class EmployeeTypeService extends AbstractServiceMutations {
	@Autowired
	EmployeeTypeRepository employeeTypeRepository;

	@POST
	@ApiOperation(value = "Create Employee Type", response = EmployeeTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveEmployeeType(EmployeeTypeDTO dto) throws SODAPIException {
		try {
			EmployeeType entity = EmployeeMapper.INSTANCE.map(dto);
			this.saveEntity(employeeTypeRepository, entity);
			dto = EmployeeMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Employee Type", response = EmployeeTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateEmployeeType(EmployeeTypeDTO dto) throws SODAPIException {
		EmployeeType entity = EmployeeMapper.INSTANCE.map(dto);
		this.updateEntity(employeeTypeRepository, entity);
		dto = EmployeeMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Employee Type", response = EmployeeTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateEmployeeTypeById(@PathParam("id") String id, EmployeeTypeDTO dto) throws SODAPIException {
		EmployeeType entity = EmployeeMapper.INSTANCE.map(dto);
		this.updateEntity(employeeTypeRepository, entity);
		dto = EmployeeMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@DELETE
	@ApiOperation(value = "Create Employee Type", response = EmployeeTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteEmployeeType(EmployeeTypeDTO dto) throws SODAPIException {
		EmployeeType entity = EmployeeMapper.INSTANCE.map(dto);
		this.deleteEntity(employeeTypeRepository, entity.getIdEmployeeType());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Employee deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Employee Type list", response = EmployeeTypeDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getEmployeeTypeList() throws SODAPIException {
		List<EmployeeType> rentityList = this.getEntityList(employeeTypeRepository);
		List<EmployeeTypeDTO> list = rentityList.stream().map((i) -> {
			EmployeeTypeDTO dto = EmployeeMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
