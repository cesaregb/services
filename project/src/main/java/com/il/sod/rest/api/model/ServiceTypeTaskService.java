package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.ServiceTypeTask;
import com.il.sod.db.model.repositories.ServiceTypeTaskRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ServiceMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ServiceTypeTaskDTO;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/services/service-type/service-type-task", produces = MediaType.APPLICATION_JSON)
@Api(value = "/services/service-type/service-type-task", tags = {"services"})
public class ServiceTypeTaskService extends AbstractServiceMutations {

	@Autowired
	ServiceTypeTaskRepository serviceTypeSpecRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Service Type", response = ServiceTypeTaskDTO.class)
	public Response saveServiceTypeTask(ServiceTypeTaskDTO dto) throws SODAPIException {
		ServiceTypeTask entity = ServiceMapper.INSTANCE.map(dto);
		this.saveEntity(serviceTypeSpecRepository, entity);
		dto = ServiceMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Service Type", response = ServiceTypeTaskDTO.class)
	public Response updateServiceTypeTask(ServiceTypeTaskDTO dto) throws SODAPIException {
		ServiceTypeTask entity = ServiceMapper.INSTANCE.map(dto);
		this.updateEntity(serviceTypeSpecRepository, entity);
		dto = ServiceMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Service Type", response = ServiceTypeTaskDTO.class)
	public Response deleteServiceTypeTask(@PathVariable("id") Integer id) throws SODAPIException {
		if (serviceTypeSpecRepository.findOne(id) == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Item not found: " + id + " ");
		}
		this.deleteEntity(serviceTypeSpecRepository, id);
		return ConvertUtils.castEntityAsResponse(
				new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
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
