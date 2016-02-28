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

import com.il.sod.db.model.entities.EmployeeTaskService;
import com.il.sod.db.model.repositories.EmployeeTaskServiceRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.EmployeeTaskServiceDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/employee-task-services")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/employee-task-services", tags = { "service" })
public class EmployeeTaskServicesService extends AbstractServiceMutations {
	@Autowired
	EmployeeTaskServiceRepository employeeTaskServiceRepository;

	@PUT
	@ApiOperation(value = "Create Service Type", response = EmployeeTaskServiceDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveEmployeeTaskService(EmployeeTaskServiceDTO dto) throws SODAPIException {
		try {
			EmployeeTaskService entity = converter.map(dto, EmployeeTaskService.class);
			this.saveEntity(employeeTaskServiceRepository, entity);
			dto = converter.map(entity, EmployeeTaskServiceDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Service Type", response = EmployeeTaskServiceDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateEmployeeTaskService(EmployeeTaskServiceDTO dto) throws SODAPIException {
		try {
			EmployeeTaskService entity = converter.map(dto, EmployeeTaskService.class);
			this.updateEntity(employeeTaskServiceRepository, entity);
			dto = converter.map(entity, EmployeeTaskServiceDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = EmployeeTaskServiceDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteEmployeeTaskService(EmployeeTaskServiceDTO dto) throws SODAPIException {
		try {
			EmployeeTaskService entity = converter.map(dto, EmployeeTaskService.class);
			this.deleteEntity(employeeTaskServiceRepository, entity.getIdEmployeeTaskService());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = EmployeeTaskServiceDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getEmployeeTaskServiceList() throws SODAPIException {
		List<EmployeeTaskService> rentityList = this.getEntityList(employeeTaskServiceRepository);
		List<EmployeeTaskServiceDTO> list = rentityList.stream().map((i) -> {
			EmployeeTaskServiceDTO dto = converter.map(i, EmployeeTaskServiceDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
