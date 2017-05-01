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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@SuppressWarnings("Duplicates")
@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/services/service-type", produces = MediaType.APPLICATION_JSON)
 @Api(value = "/services/service-type", tags = { "services" })
public class ServiceTypeService extends AbstractServiceMutations {

	final static Logger LOGGER = LoggerFactory.getLogger(ServiceTypeService.class);

	@Autowired
	ServicesSv servicesSv;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Service Type", response = ServiceTypeDTO.class)
	public Response saveServiceType(ServiceTypeDTO dto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.saveServiceType(dto), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Service Type", response = ServiceTypeDTO.class)
	public Response updateServiceType(ServiceTypeDTO dto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.updateServiceType(dto), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathVariable("id") int id) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(servicesSv.deleteItem(id), "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Service Type list", response = ServiceTypeDTO.class, responseContainer = "List")
	public Response getServiceTypeList(@QueryParam("idServiceType") int idServiceType) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.getServiceTypeList(idServiceType), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byId/{id}")
	@ApiOperation(value = "Get Service Type list", response = ServiceTypeDTO.class)
	public Response getServiceType(@PathVariable("id") int id) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.getServiceType(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byCalculator")
	@ApiOperation(value = "Get Service Type list", response = ServiceTypeDTO.class, responseContainer = "List")
	public Response getPublicServiceType() throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.getPublicServiceType(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addProducts/{idServiceType}")
	@ApiOperation(value = "Add Products to Service Type", response = ServiceDTO.class)
	public Response addProducts(@PathVariable("idServiceType") int idServiceType,  List<ProductTypeDTO> listDto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.addProducts(idServiceType, listDto), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addSpecs/{idServiceType}")
	@ApiOperation(value = "Add Specs to Service Type", response = ServiceDTO.class)
	public Response addSpecs(@PathVariable("idServiceType") int idServiceType,  List<SpecDTO> listDto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.addSpecs(idServiceType, listDto));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addTasks/{idServiceType}")
	@ApiOperation(value = "Add Tasks to Service Type", response = ServiceDTO.class)
	public Response addServiceTypeTask(@PathVariable("idServiceType") int idServiceType,  List<ServiceTypeTaskDTO> listDto) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(servicesSv.addServiceTypeTask(idServiceType, listDto));
	}

}
