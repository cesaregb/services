package com.il.sod.rest.api.impl;

import com.il.sod.db.dao.IEmployeeDAO;
import com.il.sod.db.model.entities.Employee;
import com.il.sod.db.model.repositories.EmployeeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.EmployeeMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.EmployeeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
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
@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/employees", tags = { "employees" })
public class EmployeeService extends AbstractServiceMutations {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private IEmployeeDAO employeeDAO;

	@POST
	@ApiOperation(value = "Create Employee", response = EmployeeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveEmployee(EmployeeDTO dto) throws SODAPIException {
		try {
			Employee entity = EmployeeMapper.INSTANCE.map(dto);
			this.saveEntity(employeeRepository, entity);
			dto = EmployeeMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Employee", response = EmployeeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateEmployee(EmployeeDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(EmployeeDTO dto) throws SODAPIException {
		try {
			Employee entity = EmployeeMapper.INSTANCE.map(dto);
			this.updateEntity(employeeRepository, entity);
			dto = EmployeeMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Employee", response = EmployeeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateEmployeeById(@PathParam("id") String id, EmployeeDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Item", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		Employee entity = employeeRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(employeeRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Item deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Employee list", response = EmployeeDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getEmployeeList(@QueryParam("email") String email) throws SODAPIException {
		List<Employee> entityList = null;
		if (!StringUtils.isEmpty(email)){
			entityList = employeeDAO.findByEmail(email);
		}else{
			entityList = this.getEntityList(employeeRepository);
		}
		List<EmployeeDTO> list = entityList.stream().map(EmployeeMapper.INSTANCE::map).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

	@GET
	@Path("/byId/{idEmployee}")
	@ApiOperation(value = "Get Employee list", response = EmployeeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getEmployeeById(@PathParam("idEmployee") String idEmployee) throws SODAPIException {
		Employee employee = this.getEntity(employeeRepository, Integer.valueOf(idEmployee));
		if (employee == null ) {
			throw new SODAPIException(Response.Status.NO_CONTENT, "No employee found");
		}
		EmployeeDTO dto = EmployeeMapper.INSTANCE.map(employee);
		return castEntityAsResponse(dto);
	}
}
