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

import com.il.sod.db.model.entities.EmployeeType;
import com.il.sod.db.model.repositories.EmployeeTypeRepository;
import com.il.sod.exception.SODAPIException;
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
@Api(value = "/employee-type", tags = { "generic" })
public class EmployeeTypeService extends AbstractServiceMutations {
	@Autowired
	EmployeeTypeRepository employeeTypeRepository;

	@PUT
	@ApiOperation(value = "Create Employee Type", response = EmployeeTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveEmployeeType(EmployeeTypeDTO dto) throws SODAPIException {
		try {
			EmployeeType entity = converter.map(dto, EmployeeType.class);
			this.saveEntity(employeeTypeRepository, entity);
			dto = converter.map(entity, EmployeeTypeDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Employee Type", response = EmployeeTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateEmployeeType(EmployeeTypeDTO dto) throws SODAPIException {
		try {
			EmployeeType entity = converter.map(dto, EmployeeType.class);
			this.updateEntity(employeeTypeRepository, entity);
			dto = converter.map(entity, EmployeeTypeDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Employee Type", response = EmployeeTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteEmployeeType(EmployeeTypeDTO dto) throws SODAPIException {
		try {
			EmployeeType entity = converter.map(dto, EmployeeType.class);
			this.deleteEntity(employeeTypeRepository, entity.getIdEmployeeType());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Employee deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Employee Type list", response = EmployeeTypeDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getEmployeeTypeList() throws SODAPIException {
		List<EmployeeType> rentityList = this.getEntityList(employeeTypeRepository);
		List<EmployeeTypeDTO> list = rentityList.stream().map((i) -> {
			EmployeeTypeDTO dto = converter.map(i, EmployeeTypeDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
