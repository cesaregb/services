package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.ServiceTypeSpec;
import com.il.sod.db.model.repositories.ServiceTypeSpecRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ServiceMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ServiceTypeSpecDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/service-type-specs")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/service-type-specs", tags = { "service" })
public class ServiceTypeSpecsService extends AbstractServiceMutations {
	
	@Autowired
	ServiceTypeSpecRepository serviceTypeSpecRepository;

	@POST
	@ApiOperation(value = "Create Service Type", response = ServiceTypeSpecDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveServiceTypeSpec(ServiceTypeSpecDTO dto) throws SODAPIException {
		try {
			ServiceTypeSpec entity = ServiceMapper.INSTANCE.map(dto);
			this.saveEntity(serviceTypeSpecRepository, entity);
			dto = ServiceMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Service Type", response = ServiceTypeSpecDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateServiceTypeSpec(ServiceTypeSpecDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(ServiceTypeSpecDTO dto) throws SODAPIException {
		try {
			ServiceTypeSpec entity = ServiceMapper.INSTANCE.map(dto);
			this.updateEntity(serviceTypeSpecRepository, entity);
			dto = ServiceMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}


	@Deprecated
	@DELETE
	@ApiOperation(value = "Create Service Type", response = ServiceTypeSpecDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteServiceTypeSpec(ServiceTypeSpecDTO dto) throws SODAPIException {
		try {
			ServiceTypeSpec entity = ServiceMapper.INSTANCE.map(dto);
			this.deleteEntity(serviceTypeSpecRepository, entity.getIdServiceTypeSpecs());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}
	
	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Client", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteItem(@PathParam("id") String clientId) throws SODAPIException {
		ServiceTypeSpec entity = serviceTypeSpecRepository.findOne(Integer.valueOf(clientId));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(serviceTypeSpecRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Item deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = ServiceTypeSpecDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getServiceTypeSpecList() throws SODAPIException {
		List<ServiceTypeSpec> rentityList = this.getEntityList(serviceTypeSpecRepository);
		List<ServiceTypeSpecDTO> list = rentityList.stream().map((i) -> {
			ServiceTypeSpecDTO dto = ServiceMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}
	
	@GET
	@Path("/byServiceType/{idServiceType}")
	@ApiOperation(value = "Get Service Type list", response = ServiceTypeSpecDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getServiceTypeSpecById(@PathParam("idServiceType") String idServiceType) throws SODAPIException {
		List<ServiceTypeSpec> rentityList = serviceTypeSpecRepository.findByServiceType(Integer.valueOf(idServiceType));
		
		List<ServiceTypeSpecDTO> list = rentityList.stream().map((i) -> {
			ServiceTypeSpecDTO dto = ServiceMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		
		return castEntityAsResponse(list);
	}

}
