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

import com.il.sod.db.model.entities.ServiceTask;
import com.il.sod.db.model.repositories.ServiceTaskRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ServiceTaskDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/service-task")
@Produces(MediaType.APPLICATION_JSON)
// @Api(value = "/service-task", tags = { "service" })
public class ServiceTaskService extends AbstractServiceMutations {
	@Autowired
	ServiceTaskRepository serviceSpecRepository;

	@PUT
	@ApiOperation(value = "Create Service Type", response = ServiceTaskDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveServiceTask(ServiceTaskDTO dto) throws SODAPIException {
		try {
			ServiceTask entity = converter.map(dto, ServiceTask.class);
			this.saveEntity(serviceSpecRepository, entity);
			dto = converter.map(entity, ServiceTaskDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Service Type", response = ServiceTaskDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateServiceTask(ServiceTaskDTO dto) throws SODAPIException {
		try {
			ServiceTask entity = converter.map(dto, ServiceTask.class);
			this.updateEntity(serviceSpecRepository, entity);
			dto = converter.map(entity, ServiceTaskDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = ServiceTaskDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteServiceTask(ServiceTaskDTO dto) throws SODAPIException {
		try {
			ServiceTask entity = converter.map(dto, ServiceTask.class);
			this.deleteEntity(serviceSpecRepository, entity.getIdServiceTask());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = ServiceTaskDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getServiceTaskList() throws SODAPIException {
		List<ServiceTask> rentityList = this.getEntityList(serviceSpecRepository);
		List<ServiceTaskDTO> list = rentityList.stream().map((i) -> {
			ServiceTaskDTO dto = converter.map(i, ServiceTaskDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
