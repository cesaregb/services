package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.EmployeeType;
import com.il.sod.db.model.repositories.EmployeeTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.EmployeeMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.EmployeeTypeDTO;
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
@Path("/employees/employee-type")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/employees/employee-type", tags = { "employees" })
public class EmployeeTypeService extends AbstractServiceMutations {
	@Autowired
	EmployeeTypeRepository employeeTypeRepository;

	@POST
	@ApiOperation(value = "Create Employee Type", response = EmployeeTypeDTO.class)
	public Response saveEmployeeType(EmployeeTypeDTO dto) throws SODAPIException {

			EmployeeType entity = EmployeeMapper.INSTANCE.map(dto);
			this.saveEntity(employeeTypeRepository, entity);
			dto = EmployeeMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);

	}

	@PUT
	@ApiOperation(value = "Update Employee Type", response = EmployeeTypeDTO.class)
	public Response updateEmployeeType(EmployeeTypeDTO dto) throws SODAPIException {
		EmployeeType entity = EmployeeMapper.INSTANCE.map(dto);
		this.updateEntity(employeeTypeRepository, entity);
		dto = EmployeeMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.OK);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Task Type", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		EmployeeType entity = employeeTypeRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(employeeTypeRepository, entity.getId());
		return castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Employee Type list", response = EmployeeTypeDTO.class, responseContainer = "List")
	public Response getEmployeeTypeList() throws SODAPIException {
		List<EmployeeType> rentityList = this.getEntityList(employeeTypeRepository);
		List<EmployeeTypeDTO> list = rentityList.stream().map((i) -> {
			EmployeeTypeDTO dto = EmployeeMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
