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
@Path("/services/service-category")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/services/service-category", tags = { "services" })
public class ServiceCategoryService extends AbstractServiceMutations {

	@Autowired
	ServiceCategoryRepository serviceCategoryRepository;

	@POST
	@ApiOperation(value = "Create Supply Type", response = ServiceCategoryDTO.class)
	public Response saveServiceCategory(ServiceCategoryDTO dto) throws SODAPIException {

			ServiceCategory entity = ServiceMapper.INSTANCE.map(dto);
			this.saveEntity(serviceCategoryRepository, entity);
			dto = ServiceMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);

	}

	@PUT
	@ApiOperation(value = "Update Supply Type", response = ServiceCategoryDTO.class)
	public Response updateServiceCategory(ServiceCategoryDTO dto) throws SODAPIException {

			if (dto.getIdServiceCategory() == 0
					|| serviceCategoryRepository.findOne(dto.getIdServiceCategory()) == null){
				throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found, please indicate a valida ID");
			}
			ServiceCategory entity = ServiceMapper.INSTANCE.map(dto);
			this.updateEntity(serviceCategoryRepository, entity);
			dto = ServiceMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.OK);

	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Task", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		ServiceCategory entity = serviceCategoryRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(serviceCategoryRepository, entity.getId());
		return castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Supply Type list", response = ServiceCategoryDTO.class, responseContainer = "List")
	public Response getServiceCategoryList() throws SODAPIException {
		List<ServiceCategory> rentityList = this.getEntityList(serviceCategoryRepository);
		List<ServiceCategoryDTO> list = rentityList.stream().map((i) -> {
			ServiceCategoryDTO dto = ServiceMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
