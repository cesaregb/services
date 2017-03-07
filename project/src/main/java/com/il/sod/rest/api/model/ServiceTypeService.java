package com.il.sod.rest.api.model;

import com.il.sod.db.model.repositories.ProductTypeRepository;
import com.il.sod.db.model.repositories.SpecRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.*;
import com.il.sod.services.cruds.ServicesSv;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@SuppressWarnings("Duplicates")
@Component
@RolesAllowed("ADMIN")
@Path("/services/service-type")
@Produces(MediaType.APPLICATION_JSON)
 @Api(value = "/services/service-type", tags = { "services" })
public class ServiceTypeService extends AbstractServiceMutations {

	final static Logger LOGGER = LoggerFactory.getLogger(ServiceTypeService.class);

	@Autowired
	ServicesSv servicesSv;

	@Autowired
	ProductTypeRepository productTypeRepository;

	@Autowired
	SpecRepository specRepository;

	@POST
	@ApiOperation(value = "Create Service Type", response = ServiceTypeDTO.class)
	public Response saveServiceType(ServiceTypeDTO dto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.saveServiceType(dto), Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update Service Type", response = ServiceTypeDTO.class)
	public Response updateServiceType(ServiceTypeDTO dto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.updateServiceType(dto), Response.Status.OK);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") int id) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(servicesSv.deleteItem(id), "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = ServiceTypeDTO.class, responseContainer = "List")
	public Response getServiceTypeList(@QueryParam("idServiceType") int idServiceType) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.getServiceTypeList(idServiceType), Response.Status.OK);
	}

	@GET
	@Path("/byId/{id}")
	@ApiOperation(value = "Get Service Type list", response = ServiceTypeDTO.class)
	public Response getServiceType(@PathParam("id") int id) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.getServiceType(id));
	}

	@GET
	@Path("/byCalculator")
	@ApiOperation(value = "Get Service Type list", response = ServiceTypeDTO.class, responseContainer = "List")
	public Response getPublicServiceType() throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.getPublicServiceType(), Response.Status.OK);
	}

	@POST
	@Path("/addProducts/{idServiceType}")
	@ApiOperation(value = "Add Products to Service Type", response = ServiceDTO.class)
	public Response addProducts(@PathParam("idServiceType") int idServiceType,  List<ProductTypeDTO> listDto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.addProducts(idServiceType, listDto), Response.Status.OK);
	}

	@POST
	@Path("/addSpecs/{idServiceType}")
	@ApiOperation(value = "Add Specs to Service Type", response = ServiceDTO.class)
	public Response addSpecs(@PathParam("idServiceType") int idServiceType,  List<SpecDTO> listDto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.addSpecs(idServiceType, listDto));
	}

	@POST
	@Path("/addTasks/{idServiceType}")
	@ApiOperation(value = "Add Tasks to Service Type", response = ServiceDTO.class)
	public Response addServiceTypeTask(@PathParam("idServiceType") int idServiceType,  List<ServiceTypeTaskDTO> listDto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.addServiceTypeTask(idServiceType, listDto));
	}

}
