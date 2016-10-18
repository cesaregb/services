package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.ServiceType;
import com.il.sod.db.model.repositories.ServiceTypeRepository;
import com.il.sod.db.model.repositories.ProductTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ServiceMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ServiceDTO;
import com.il.sod.rest.dto.db.ServiceTypeDTO;
import com.il.sod.rest.dto.db.ProductTypeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/service-type")
@Produces(MediaType.APPLICATION_JSON)
 @Api(value = "/service-type", tags = { "service" })
public class ServiceTypeService extends AbstractServiceMutations {

	final static Logger LOGGER = LoggerFactory.getLogger(ServiceTypeService.class);

	@Autowired
	ServiceTypeRepository serviceTypeRepository;

	@Autowired
	ProductTypeRepository productTypeRepository;

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
	@Deprecated
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

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Service Type", response = ServiceTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateServiceTypeById(@PathParam("id") String id, ServiceTypeDTO dto) throws SODAPIException {
		ServiceType entity = ServiceMapper.INSTANCE.map(dto);
		this.updateEntity(serviceTypeRepository, entity);
		dto = converter.map(entity, ServiceTypeDTO.class);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		ServiceType entity = serviceTypeRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(serviceTypeRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Item deleted"),
				Response.Status.OK);
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

	@GET
	@Path("/byId/{id}")
	@ApiOperation(value = "Get Service Type list", response = ServiceTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getServiceType(@PathParam("id") String id) throws SODAPIException {
		ServiceType st = serviceTypeRepository.findOne(Integer.valueOf(id));
		ServiceTypeDTO dto = ServiceMapper.INSTANCE.map(st);
		return castEntityAsResponse(dto);
	}

	@GET
	@Path("/byCalculator")
	@ApiOperation(value = "Get Service Type list", response = ServiceTypeDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getPublicServiceType() throws SODAPIException {
		List<ServiceType> listEntities = serviceTypeRepository.findAllPublic();
		List<ServiceTypeDTO> listResult = listEntities.stream().map(i -> ServiceMapper.INSTANCE.map(i)).collect(Collectors.toList());
		return castEntityAsResponse(listResult);
	}

	@POST
	@Path("/addProducts/{idServiceType}")
	@ApiOperation(value = "Add Products to Service Type", response = ServiceDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveService(@PathParam("idServiceType") Integer idServiceType,  List<ProductTypeDTO> listDto) throws SODAPIException {
		ServiceType serviceType = serviceTypeRepository.findOne(idServiceType);
		// clean list!!
		serviceType.setProductTypes(new HashSet<>());
		for (ProductTypeDTO sptd :
				listDto) {
			serviceType.addProductType(productTypeRepository.findOne(sptd.getIdProductType()));
		}
		this.saveEntity(serviceTypeRepository, serviceType);

		ServiceTypeDTO dto = ServiceMapper.INSTANCE.map( serviceType );
		return castEntityAsResponse(dto);
	}

}
