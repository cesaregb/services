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

import com.il.sod.db.model.entities.ServiceType;
import com.il.sod.db.model.repositories.ServiceTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ServiceMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ServiceTypeDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/service-type")
@Produces(MediaType.APPLICATION_JSON)
 @Api(value = "/service-type", tags = { "service" })
public class ServiceTypeService extends AbstractServiceMutations {
	@Autowired
	ServiceTypeRepository serviceTypeRepository;

	@POST
	@ApiOperation(value = "Create Service Type", response = ServiceTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveServiceType(ServiceTypeDTO dto) throws SODAPIException {
		ServiceType entity = ServiceMapper.INSTANCE.map(dto);
		this.saveEntity(serviceTypeRepository, entity);
		dto = converter.map(entity, ServiceTypeDTO.class);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update Service Type", response = ServiceTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateServiceType(ServiceTypeDTO dto) throws SODAPIException {
		ServiceType entity = ServiceMapper.INSTANCE.map(dto);
		this.updateEntity(serviceTypeRepository, entity);
		dto = converter.map(entity, ServiceTypeDTO.class);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = ServiceTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteServiceType(ServiceTypeDTO dto) throws SODAPIException {
		try {
			ServiceType entity = ServiceMapper.INSTANCE.map(dto);
			this.deleteEntity(serviceTypeRepository, entity.getIdServiceType());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = ServiceTypeDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getServiceTypeList() throws SODAPIException {
		List<ServiceType> rentityList = this.getEntityList(serviceTypeRepository);
		List<ServiceTypeDTO> list = rentityList.stream().map((i) -> {
			ServiceTypeDTO dto = ServiceMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
