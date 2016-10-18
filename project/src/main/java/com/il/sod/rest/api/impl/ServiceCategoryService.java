package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.ServiceCategory;
import com.il.sod.db.model.repositories.ServiceCategoryRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ServiceMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ServiceCategoryDTO;
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
@Path("/service-category")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/service-category", tags = { "service" })
public class ServiceCategoryService extends AbstractServiceMutations {

	@Autowired
	ServiceCategoryRepository serviceCategoryRepository;

	@POST
	@ApiOperation(value = "Create Supply Type", response = ServiceCategoryDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveServiceCategory(ServiceCategoryDTO dto) throws SODAPIException {
		try {
			ServiceCategory entity = ServiceMapper.INSTANCE.map(dto);
			this.saveEntity(serviceCategoryRepository, entity);
			dto = ServiceMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Supply Type", response = ServiceCategoryDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateServiceCategory(ServiceCategoryDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(ServiceCategoryDTO dto) throws SODAPIException {
		try {
			ServiceCategory entity = ServiceMapper.INSTANCE.map(dto);
			this.updateEntity(serviceCategoryRepository, entity);
			dto = ServiceMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Supply Type", response = ServiceCategoryDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateServiceCategoryById(@PathParam("id") String id, ServiceCategoryDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}
	
	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Task", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		ServiceCategory entity = serviceCategoryRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(serviceCategoryRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Item deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Supply Type list", response = ServiceCategoryDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getServiceCategoryList() throws SODAPIException {
		List<ServiceCategory> rentityList = this.getEntityList(serviceCategoryRepository);
		List<ServiceCategoryDTO> list = rentityList.stream().map((i) -> {
			ServiceCategoryDTO dto = ServiceMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
