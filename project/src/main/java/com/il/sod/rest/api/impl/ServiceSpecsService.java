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

import com.il.sod.db.model.entities.ServiceSpec;
import com.il.sod.db.model.repositories.ServiceSpecRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ServiceSpecDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/service-specs")
@Produces(MediaType.APPLICATION_JSON)
// @Api(value = "/service-specs", tags = { "service" })
public class ServiceSpecsService extends AbstractServiceMutations {
	@Autowired
	ServiceSpecRepository serviceSpecRepository;

	@PUT
	@ApiOperation(value = "Create Service Type", response = ServiceSpecDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveServiceSpec(ServiceSpecDTO dto) throws SODAPIException {
		try {
			ServiceSpec entity = converter.map(dto, ServiceSpec.class);
			this.saveEntity(serviceSpecRepository, entity);
			dto = converter.map(entity, ServiceSpecDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Service Type", response = ServiceSpecDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateServiceSpec(ServiceSpecDTO dto) throws SODAPIException {
		try {
			ServiceSpec entity = converter.map(dto, ServiceSpec.class);
			this.updateEntity(serviceSpecRepository, entity);
			dto = converter.map(entity, ServiceSpecDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = ServiceSpecDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteServiceSpec(ServiceSpecDTO dto) throws SODAPIException {
		try {
			ServiceSpec entity = converter.map(dto, ServiceSpec.class);
			this.deleteEntity(serviceSpecRepository, entity.getIdServiceSpecs());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = ServiceSpecDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getServiceSpecList() throws SODAPIException {
		List<ServiceSpec> rentityList = this.getEntityList(serviceSpecRepository);
		List<ServiceSpecDTO> list = rentityList.stream().map((i) -> {
			ServiceSpecDTO dto = converter.map(i, ServiceSpecDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
