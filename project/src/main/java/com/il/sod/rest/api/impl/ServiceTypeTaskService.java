package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.ServiceTypeTask;
import com.il.sod.db.model.repositories.ServiceTypeTaskRepository;
import com.il.sod.services.utils.ConvertUtils;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ServiceMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ServiceTypeTaskDTO;
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
@Path("/services/service-type/service-type-task")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/services/service-type/service-type-task", tags = {"services"})
public class ServiceTypeTaskService extends AbstractServiceMutations {

	@Autowired
	ServiceTypeTaskRepository serviceTypeSpecRepository;

	@POST
	@ApiOperation(value = "Create Service Type", response = ServiceTypeTaskDTO.class)
	public Response saveServiceTypeTask(ServiceTypeTaskDTO dto) throws SODAPIException {
		ServiceTypeTask entity = ServiceMapper.INSTANCE.map(dto);
		this.saveEntity(serviceTypeSpecRepository, entity);
		dto = ServiceMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update Service Type", response = ServiceTypeTaskDTO.class)
	public Response updateServiceTypeTask(ServiceTypeTaskDTO dto) throws SODAPIException {
		ServiceTypeTask entity = ServiceMapper.INSTANCE.map(dto);
		this.updateEntity(serviceTypeSpecRepository, entity);
		dto = ServiceMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, Response.Status.OK);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Service Type", response = ServiceTypeTaskDTO.class)
	public Response deleteServiceTypeTask(@PathParam("id") Integer id) throws SODAPIException {
		if (serviceTypeSpecRepository.findOne(id) == null) {
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found: " + id + " ");
		}
		this.deleteEntity(serviceTypeSpecRepository, id);
		return ConvertUtils.castEntityAsResponse(
				new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);

	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = ServiceTypeTaskDTO.class, responseContainer = "List")
	public Response getServiceTypeTaskList() throws SODAPIException {
		List<ServiceTypeTask> rentityList = this.getEntityList(serviceTypeSpecRepository);
		List<ServiceTypeTaskDTO> list = rentityList.stream().map((i) -> {
			ServiceTypeTaskDTO dto = ServiceMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}

}
