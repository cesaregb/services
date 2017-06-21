package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.Service;
import com.il.sod.db.model.repositories.ServiceRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ServiceMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ServiceDTO;
import com.il.sod.services.utils.ConvertUtils;
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
@Path("/services")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/services", tags = { "services" })
public class ServiceService extends AbstractServiceMutations {
	@Autowired
	ServiceRepository serviceRepository;

	@POST
	@ApiOperation(value = "Create Service Type", response = ServiceDTO.class)
	public Response saveService(ServiceDTO dto) throws SODAPIException {

			Service entity = ServiceMapper.INSTANCE.map(dto);
			this.saveEntity(serviceRepository, entity);
			dto = ServiceMapper.INSTANCE.map(entity);
			return ConvertUtils.castEntityAsResponse(dto, Response.Status.CREATED);

	}

	@PUT
	@ApiOperation(value = "Update Service Type", response = ServiceDTO.class)
	public Response updateService(ServiceDTO dto) throws SODAPIException {

			Service entity = ServiceMapper.INSTANCE.map(dto);
			this.updateEntity(serviceRepository, entity);
			dto = ServiceMapper.INSTANCE.map(entity);
			return ConvertUtils.castEntityAsResponse(dto, Response.Status.OK);

	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = ServiceDTO.class)
	public Response deleteService(ServiceDTO dto) throws SODAPIException {

			Service entity = ServiceMapper.INSTANCE.map(dto);
			this.deleteEntity(serviceRepository, entity.getIdService());
			return ConvertUtils.castEntityAsResponse(
					new GeneralResponseMessage(true, "Entity deleted"),
					Response.Status.OK);

	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = ServiceDTO.class, responseContainer = "List")
	public Response getServiceList() throws SODAPIException {
		List<Service> entityList = this.getEntityList(serviceRepository);
		List<ServiceDTO> list = entityList.stream().map((i) -> {
			ServiceDTO dto = ServiceMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}

}
